package monolith.core.enchant;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.changeme.nbtapi.*;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;

public class Enchanter 
{
	public final static long minCooldown = 1000; // min. 1 second between preparation and confirm
	public final static long maxCooldown = 60000; // max. 60 seconds between preparation and confirm
	
	// when player triggers enchant one or more times
	public static EnchantResult handleInteract (ItemStack source, ItemStack target, Player owner) {
		Enchanter ench = new Enchanter(source, target, owner);
		if (ench.isPrepared) return ench.handleClick2();
		else return ench.handleClick1();
	}
	
	// when player does /enchant confirm.
	// enchant prepare is not a thing because it requires 
	// interaction with item frame.
	public static EnchantResult confirmEnchant (ItemStack target, Player owner) {
		return null;
	}

	private Player owner;
	private ItemStack source, target;
	private NBTItem sourceNbt, targetNbt;
	private NBTCompoundList sourceEnchants, targetEnchants, futureEnchants;
	private long preparedAt, now;
	private boolean isPrepared;
	
	private Enchanter (ItemStack source, ItemStack target, Player owner) {
		this.owner = owner;
		this.now = System.currentTimeMillis();
		if (source != null && !source.isEmpty()) {
			this.source = source;
			this.sourceNbt = new NBTItem(source);
			this.sourceEnchants = sourceNbt.getCompoundList(isBook(source) ? "StoredEnchantments" : "Enchantments");
		}
		if (target != null && !target.isEmpty()) {
			this.target = target;
			this.targetNbt = new NBTItem(target);
			this.preparedAt = targetNbt.getLong("PreparedAt");
			this.isPrepared = this.preparedAt > 0L;
			if (!targetNbt.hasTag("Enchantments")) targetNbt.addCompound("Enchantments");
			this.targetEnchants = targetNbt.getCompoundList("Enchantments");
			if (!targetNbt.hasTag("FutureEnchantments")) targetNbt.addCompound("FutureEnchantments");
			this.futureEnchants = targetNbt.getCompoundList("FutureEnchantments");
		}
	}
	
	EnchantResult handleClick1 () {
		EnchantResult result = new EnchantResult();
		if (!isValidSource()) {
			result.ignore = true;
			return result;
		}
		result.info = true;
		result.price = getEnchantmentCost(this.sourceEnchants, this.targetEnchants);
		for (ReadWriteNBT skv : this.sourceEnchants) {
			String id = skv.getString("id");
			int level = skv.getInteger("lvl");
			result.addEnchant(id, level);
		}
		if (target == null) {
			result.targetEmpty = true;
			return result;
		}
		if (!isValidTarget()) {
			result.targetNotSupported = true;
			return result;
		}
		if (!isCompatible()) {
			result.targetTypeMismatch = true;
			return result;
		}
		if (result.price >= owner.getLevel()) {
			result.tooExpensive = true;
			return result;
		}
		if (result.price <= 0) {
			return result;
		}
		for (ReadWriteNBT skv : this.sourceEnchants) {
			String id = skv.getString("id");
			int level = skv.getInteger("lvl");
			NBTListCompound ench = this.futureEnchants.addCompound();
			ench.setString("id", id);
			ench.setInteger("lvl", level);
		}
		targetNbt.setLong("PreparedAt", now);
		targetNbt.applyNBT(target);
		result.prepared = true;
		return result;
	}
	
	EnchantResult handleClick2 () {
		EnchantResult result = new EnchantResult();
		long dt = now - preparedAt;
		if (dt < minCooldown) {
			result.ignore = true;
			return result;
		}
		if (dt > maxCooldown) {
			result.cancelled = true;
			removePreparedEnchants();
			return result;
		}
		if (! equalEnchantments(this.sourceEnchants, this.futureEnchants)) {
			result.cancelled = true;
			removePreparedEnchants();
			return result;
		}
		int price = this.getEnchantmentCost(this.sourceEnchants, this.targetEnchants);
		if (price >= owner.getLevel()) {
			result.tooExpensive = true;
			removePreparedEnchants();
			return result;
		}
		
		result.confirmed = true;
		confirmPreparedEnchants(this.sourceEnchants, this.targetEnchants);
		owner.setLevel(owner.getLevel() - price);
		return result;
	}
	
	void removePreparedEnchants () {
		if (targetNbt == null) return; 
		targetNbt.removeKey("FutureEnchantments");
		targetNbt.removeKey("PreparedAt");
		targetNbt.applyNBT(target);
	}
	
	void confirmPreparedEnchants (NBTCompoundList sourceEnch, NBTCompoundList targetEnch) {
		if (targetNbt == null) return;
		for (ReadWriteNBT skv : sourceEnch) {
			String id = skv.getString("id");
			int slevel = skv.getInteger("lvl");
			boolean upgrade = false;
			for (ReadWriteNBT tkv : targetEnch) {
				if (tkv.getString("id").equals(id)) {
					tkv.setInteger("lvl", Math.max(slevel, tkv.getInteger("lvl")));
					upgrade = true;
					break;
				}
			}
			if (!upgrade) {
				NBTListCompound ench = targetEnch.addCompound();
				ench.setString("id", id);
				ench.setInteger("lvl", slevel);
			}
		}
		targetNbt.removeKey("FutureEnchantments");
		targetNbt.removeKey("PreparedAt");
		targetNbt.applyNBT(target);
	}
	
	int getEnchantmentCost (NBTCompoundList sourceEnch, NBTCompoundList targetEnch)
	{
		int result = 0;
		for (ReadWriteNBT skv : sourceEnch) {
			String id = skv.getString("id");
			int slevel = skv.getInteger("lvl");
			int tlevel = 0;
			if (targetEnch != null) for (ReadWriteNBT tkv : targetEnch) {
				if (tkv.getString("id").equals(id)) {
					tlevel = tkv.getInteger("lvl");
				}
			}
			int multiplier = (id.contains("mending") || id.contains("curse") || id.contains("infinity"))? 3 : 1;
			if (slevel > tlevel) result += (slevel - tlevel) * multiplier; 
		}
		return result;
	}
	
	boolean isValidSource () {
		return (this.sourceEnchants != null) && this.sourceEnchants.size() > 0;
	}
	
	boolean isValidTarget () {
		if (this.target == null) return false;
		String id = getId(target);
		return isToolId(id) || isWeaponId(id) || isArmorId(id);
	}
	
	boolean isCompatible () {
		if (this.source == null || this.target == null) return false;
		if (isBook(this.source)) return true;
		String sourceId = source.getType().getKey().asString(),
			   targetId = target.getType().getKey().asString();
		return isEqualCategoryId(sourceId, targetId);
	}
	
	static NBTCompoundList getEnchantments (NBTItem source) {
		NBTCompoundList result = source.getCompoundList("Enchantments");
		if (result == null) result = source.getCompoundList("StoredEnchantments");
		return result;
	}
	
	static NBTCompoundList getFutureEnchantments (NBTItem source) {
		return source.getCompoundList("FutureEnchantments");
	}
	
	static boolean equalEnchantments (NBTCompoundList ench1, NBTCompoundList ench2) {
		if (ench1 == null && ench2 == null) return true;
		if (ench1 == null || ench2 == null) return false;
		
		int size1 = ench1.size(), size2 = ench2.size();
		if (size1 != size2) return false;
		
		for (int i=0; i<size1; i+=1) {
			NBTListCompound item1 = ench1.get(i), item2 = ench2.get(i);
			if (!item1.getString("id").equals(item2.getString("id"))) return false;
			if (!item1.getInteger("lvl").equals(item2.getInteger("lvl"))) return false;
		}
		return true;
	}
	
	static String getId (ItemStack item)
	{
		if (item == null || item.isEmpty()) return "empty";
		return item.getType().getKey().toString();
	}
	
	static boolean isToolId (String id)
	{
		return id.endsWith("_shovel") || id.endsWith("_hoe") || id.endsWith("_axe") || id.endsWith("_pickaxe");
	}
	
	static boolean isWeaponId (String id)
	{
		return id.endsWith("_sword") || id.endsWith("trident") || id.endsWith("bow") || id.endsWith("crossbow") || id.endsWith("_axe");
	}
	
	static boolean isArmorId (String id)
	{
		return id.endsWith("_chestplate") || id.endsWith("_leggings") || id.endsWith("_boots") || id.endsWith("_helmet") || id.endsWith("shield") || id.endsWith("elytra");
	}
	
	static boolean isEqualCategoryId (String id1, String id2)
	{
		return isToolId(id1) && isToolId(id2) 
			|| isWeaponId(id1) && isWeaponId(id2)
			|| isArmorId(id1) && isArmorId(id2);
	}
	
	static boolean isBook (ItemStack item)
	{
		return item.getType() == Material.ENCHANTED_BOOK;
	}
}
