package org.naumov.mypaperplugin.enchant;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.changeme.nbtapi.*;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;

public class Enchanter 
{
	public static boolean isEnchantProvider (ItemStack item)
	{
		if (item == null || item.isEmpty()) return false;
		return hasEnchantments(item);
	}
	
	public static boolean hasEnchantments (ItemStack item)
	{
		if (item == null || item.isEmpty()) return false;
		
		NBTItem nitem = new NBTItem(item);
		return nitem.getCompoundList(isBook(item) ? "StoredEnchantments" : "Enchantments").size() > 0;
	}
	
	public static boolean isEnchantTarget (ItemStack item)
	{
		if (item == null || item.isEmpty()) return false;
		String id = getId(item);
		return isToolId(id) || isWeaponId(id) || isArmorId(id);
	}
	
	public static boolean isEnchantReady (ItemStack item)
	{
		if (item == null || item.isEmpty()) return false;
		NBTItem nitem = new NBTItem(item);
		return nitem.getBoolean("EnchantmentCopierReady");
	}
	
	public static int getEnchantmentCost (NBTCompoundList enchantments)
	{
		int result = 0;
		for (ReadWriteNBT kv : enchantments) {
			result += kv.getInteger("lvl");
		}
		return result;
	}
	
	public static boolean trySetEnchantReady (ItemStack item)
	{
		if (! isEnchantTarget(item)) return false;
		NBTItem nitem = new NBTItem(item);
		nitem.setBoolean("EnchantmentCopierReady", true);
		nitem.mergeNBT(item);
		return true;
	}
	
	public static EnchantResult tryCopyEnchant (ItemStack source, ItemStack target, Player targetOwner)
	{
		if (! isEnchantTarget(target)) return EnchantResult.TARGET_NOT_SUPPORTED;
		if (hasEnchantments(target)) return EnchantResult.TARGET_ALREADY_ENCHANTED;
		if (! isEnchantReady(target)) return EnchantResult.TARGET_NOT_READY;
		
		String sourceId = getId(source),
			   targetId = getId(target);
		
		if (!(isBook(source) || isEqualCategoryId(sourceId, targetId))) return EnchantResult.SOURCE_TARGET_MISMATCH;
		
		NBTItem nsource = new NBTItem(source);
		NBTItem ntarget = new NBTItem(target);
		
		NBTCompoundList sourceEnch = nsource.getCompoundList(isBook(source) ? "StoredEnchantments" : "Enchantments");
		
		int enchCost = getEnchantmentCost(sourceEnch);
		if (enchCost >= targetOwner.getLevel()) return EnchantResult.TOO_EXPENSIVE;
		
		ntarget.getOrCreateCompound("Enchantments");
		NBTCompoundList targetEnch = ntarget.getCompoundList("Enchantments");
		for (ReadWriteNBT ench : sourceEnch) {
			targetEnch.addCompound(ench);
		}
		ntarget.removeKey("EnchantmentCopierReady");
		ntarget.applyNBT(target);
		targetOwner.setLevel(targetOwner.getLevel() - enchCost);
		return EnchantResult.SUCCESS;
	}
	
	public static String getId (ItemStack item)
	{
		if (item == null || item.isEmpty()) return "empty";
		return item.getType().getKey().toString();
	}
	
	public static boolean isToolId (String id)
	{
		return id.endsWith("_shovel") || id.endsWith("_hoe") || id.endsWith("_axe") || id.endsWith("_pickaxe");
	}
	
	public static boolean isWeaponId (String id)
	{
		return id.endsWith("_sword") || id.endsWith("trident") || id.endsWith("bow") || id.endsWith("crossbow");
	}
	
	public static boolean isArmorId (String id)
	{
		return id.endsWith("_chestplate") || id.endsWith("_leggings") || id.endsWith("_boots") || id.endsWith("_helmet");
	}
	
	public static boolean isEqualCategoryId (String id1, String id2)
	{
		return isToolId(id1) == isToolId(id2) 
			&& isWeaponId(id1) == isWeaponId(id2)
			&& isArmorId(id1) == isArmorId(id2);
	}
	
	public static boolean isBook (ItemStack item)
	{
		return item.getType() == Material.ENCHANTED_BOOK;
	}
}