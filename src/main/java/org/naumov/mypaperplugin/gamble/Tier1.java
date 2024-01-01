package org.naumov.mypaperplugin.gamble;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffectType;

public class Tier1 
{
	static int color = 0xfe9822;
	
	// Tier II, Rare
	public static Prize[] prizes = new Prize[] {
			
		new Prize(Material.NETHERITE_SWORD)
			.name("Tier II Sword", color)
			.enchant(Enchantment.DAMAGE_ALL, 3)
			.enchant(Enchantment.FIRE_ASPECT, 1)
			.enchant(Enchantment.KNOCKBACK, 1)
			.enchant(Enchantment.LOOT_BONUS_MOBS, 1)
			.enchant(Enchantment.DURABILITY, 3),
			
		new Prize(Material.NETHERITE_SWORD)
			.name("Progressive Knife", TierSpecial.color)
			.lore("Cut angel's throat!")
			.enchant(Enchantment.DAMAGE_ALL, 4)
			.enchant(Enchantment.SWEEPING_EDGE, 2)
			.enchant(Enchantment.KNOCKBACK, 1)
			.enchant(Enchantment.DURABILITY, 3),
			
		new Prize(Material.NETHERITE_PICKAXE)
			.name("Tier II.S Pickaxe", color)
			.enchant(Enchantment.DIG_SPEED, 3)
			.enchant(Enchantment.DURABILITY, 3)
			.enchant(Enchantment.SILK_TOUCH, 1),
			
		new Prize(Material.NETHERITE_PICKAXE)
			.name("Tier II.F Pickaxe", color)
			.enchant(Enchantment.DIG_SPEED, 3)
			.enchant(Enchantment.DURABILITY, 3)
			.enchant(Enchantment.LOOT_BONUS_BLOCKS, 3),
			
		new Prize(Material.NETHERITE_AXE)
			.name("Tier II Axe", color)
			.enchant(Enchantment.DIG_SPEED, 3)
			.enchant(Enchantment.DURABILITY, 3),
			
		new Prize(Material.NETHERITE_SHOVEL)
			.name("Tier II Shovel", color)
			.enchant(Enchantment.DIG_SPEED, 3)
			.enchant(Enchantment.DURABILITY, 3),
			
		new Prize(Material.BOW)
			.name("Tier II Bow", color)
			.enchant(Enchantment.ARROW_DAMAGE, 3)
			.enchant(Enchantment.DURABILITY, 3),
			
		new Prize(Material.FISHING_ROD)
			.name("Tier II Fishing Rod", color)
			.enchant(Enchantment.LUCK, 3)
			.enchant(Enchantment.LURE, 3),
		
		new Prize(Material.DIAMOND).amount(24),
		
		new Prize(Material.NETHERITE_INGOT).amount(8),
		
		new Prize(Material.EMERALD).amount(24),
		
		new Prize(Material.GOLD_INGOT).amount(16),
		
		new Prize(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE).amount(1),
		
		new Prize(Material.EXPERIENCE_BOTTLE).amount(16),
		
		new Prize(Material.SPAWNER).amount(1),
		
		new Prize(Material.ZOMBIE_SPAWN_EGG).amount(2),
		
		new Prize(Material.COOKED_BEEF).amount(32),
		
		new Prize(Material.ENCHANTED_GOLDEN_APPLE).amount(4),
		
		new Prize(Material.POTION)
			.name("Potion of Health Boost II")
			.effect(PotionEffectType.HEALTH_BOOST, 2, 90)
			.amount(2),
			
		new Prize(Material.POTION)
			.name("Potion of Haste I")
			.effect(PotionEffectType.FAST_DIGGING, 1, 480)
			.amount(2)
	};
}
