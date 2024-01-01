package org.naumov.mypaperplugin.book;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.changeme.nbtapi.*;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;

public class BookModifier 
{
	static final int MAX_ENCHANT_LEVEL = 10;
	
	public static BookModifyResult tryUpgradeBook (ItemStack item, Player owner) 
	{
		if (!canUpgrade(item)) {
			return BookModifyResult.TARGET_NOT_SUPPORTED;
		}
		NBTItem nitem = new NBTItem(item);
		NBTCompoundList enchantments = nitem.getCompoundList("StoredEnchantments");
		int cost = item.getAmount() * getEnchantmentCost(enchantments, BookModifyAction.UPGRADE);
		if (cost > owner.getLevel()) {
			return BookModifyResult.TOO_EXPENSIVE;
		}
		
		for (ReadWriteNBT kv : enchantments) {
			String name = kv.getString("id").toLowerCase();
			int lvl = kv.getInteger("lvl");
			boolean ignore = name.contains("mending") || name.contains("infinity") || 
					         name.contains("silk") || name.contains("multishot") || 
					         name.contains("channeling") || name.contains("flame") ||
					         name.contains("charge") && lvl >= 3 ||
					         name.contains("knockback") && lvl >= 2 ||
					         name.contains("punch") && lvl >= 2 ||
					         name.contains("curse") ||
					         lvl >= 10;
			if (ignore) continue;
			kv.setInteger("lvl", lvl + 1);
		}
		nitem.applyNBT(item);
		owner.setLevel(owner.getLevel() - cost);
		return BookModifyResult.SUCCESS;
	}
	
	public static BookModifyResult tryDupeBook (ItemStack item, Player owner)
	{
		if (!canDupe(item)) {
			return BookModifyResult.TARGET_NOT_SUPPORTED;
		}
		NBTItem nitem = new NBTItem(item);
		int cost = getEnchantmentCost(nitem.getCompoundList("StoredEnchantments"), BookModifyAction.DUPE);
		item.setAmount(item.getAmount() + 1);
		owner.setLevel(owner.getLevel() - cost);
		return BookModifyResult.SUCCESS;
	}
	
	public static BookModifyResult tryDisenchantBook (ItemStack item, Player owner)
	{
		if (!canDisenchant(item)) {
			return BookModifyResult.TARGET_NOT_SUPPORTED;
		}
		NBTItem nitem = new NBTItem(item);
		int cost = item.getAmount() * getEnchantmentCost(nitem.getCompoundList("StoredEnchantments"), BookModifyAction.DISENCHANT);
		nitem.removeKey("StoredEnchantments");
		nitem.applyNBT(item);
		item.setType(Material.BOOK);
		owner.setLevel(owner.getLevel() + cost);
		return BookModifyResult.SUCCESS;
	}
	
	public static boolean isBook (ItemStack item)
	{
		return item.getType() == Material.ENCHANTED_BOOK;
	}
	
	public static boolean canUpgrade (ItemStack item)
	{
		if(!isBook(item)) {
			return false;
		}
		NBTItem nitem = new NBTItem(item);
		NBTCompoundList enchantments = nitem.getCompoundList("StoredEnchantments");
		for (ReadWriteNBT kv : enchantments) {
			if (kv.getInteger("lvl") >= 10) return false;
		}
		return true;
	}
	
	public static boolean canDupe (ItemStack item)
	{
		return isBook(item) && item.getAmount() < 64;
	}
	
	public static boolean canDisenchant (ItemStack item)
	{
		return isBook(item);
	}
	
	public static int getEnchantmentCost (NBTCompoundList enchantments, BookModifyAction action)
	{
		int result = 0;
		for (ReadWriteNBT kv : enchantments) {
			int lvl = kv.getInteger("lvl");
			double pow = (action == BookModifyAction.UPGRADE) ? 2.5 : 
					     (action == BookModifyAction.DUPE) ? 1.5 :
					     (action == BookModifyAction.DISENCHANT) ? 1 : 0;
			result += (int)(Math.pow(lvl, pow) + 1);
		}
		return result;
	}
}
