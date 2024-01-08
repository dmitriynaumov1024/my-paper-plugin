package monolith.core.gamble;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffectType;

public class Tier3 
{
	static int color = 0xf03933;
	
	// tier IV, legendary
	public static Prize[] prizes = new Prize[] {
			
		new Prize(Material.NETHERITE_SWORD)
			.name("Tier IV Sword", color)
			.enchant(Enchantment.DAMAGE_ALL, 6)
			.enchant(Enchantment.FIRE_ASPECT, 2)
			.enchant(Enchantment.SWEEPING_EDGE, 3)
			.enchant(Enchantment.KNOCKBACK, 2)
			.enchant(Enchantment.DURABILITY, 6)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.NETHERITE_SWORD)
			.name("Progressive Knife", TierSpecial.color)
			.lore("Cut angel's throat!")
			.enchant(Enchantment.DAMAGE_ALL, 4)
			.enchant(Enchantment.SWEEPING_EDGE, 2)
			.enchant(Enchantment.KNOCKBACK, 1)
			.enchant(Enchantment.DURABILITY, 3),
			
		new Prize(Material.NETHERITE_SWORD)
			.name("Cartesian Sword II", TierSpecial.color)
			.lore("Come on baby, swing it", "like a baseball bat!")
			.enchant(Enchantment.DAMAGE_ALL, 8)
			.enchant(Enchantment.FIRE_ASPECT, 2)
			.enchant(Enchantment.SWEEPING_EDGE, 5)
			.enchant(Enchantment.KNOCKBACK, 3)
			.enchant(Enchantment.DURABILITY, 7)
			.enchant(Enchantment.DAMAGE_UNDEAD, 4)
			.enchant(Enchantment.DAMAGE_ARTHROPODS, 4)
			.enchant(Enchantment.LOOT_BONUS_MOBS, 4)
			.enchant(Enchantment.MENDING, 1),
		
		new Prize(Material.NETHERITE_PICKAXE)
			.name("Tier IV.S Pickaxe", color)
			.enchant(Enchantment.DIG_SPEED, 7)
			.enchant(Enchantment.DURABILITY, 6)
			.enchant(Enchantment.SILK_TOUCH, 1)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.NETHERITE_PICKAXE)
			.name("Tier IV.F Pickaxe", color)
			.enchant(Enchantment.DIG_SPEED, 7)
			.enchant(Enchantment.DURABILITY, 6)
			.enchant(Enchantment.LOOT_BONUS_BLOCKS, 6)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.NETHERITE_AXE)
			.name("Tier IV Axe", color)
			.enchant(Enchantment.DIG_SPEED, 7)
			.enchant(Enchantment.DURABILITY, 6)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.NETHERITE_AXE)
			.name("Progressive Axe", TierSpecial.color)
			.enchant(Enchantment.DIG_SPEED, 5)
			.enchant(Enchantment.DAMAGE_ALL, 6)
			.enchant(Enchantment.DURABILITY, 6)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.NETHERITE_SHOVEL)
			.name("Tier IV Shovel", color)
			.enchant(Enchantment.DIG_SPEED, 5)
			.enchant(Enchantment.DURABILITY, 5)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.BOW)
			.name("Tier IV Bow", color)
			.enchant(Enchantment.ARROW_DAMAGE, 6)
			.enchant(Enchantment.DURABILITY, 6)
			.enchant(Enchantment.ARROW_INFINITE, 1)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.BOW)
			.name("Progressive Bow", TierSpecial.color)
			.lore("Burn the village down!")
			.enchant(Enchantment.ARROW_DAMAGE, 8)
			.enchant(Enchantment.DURABILITY, 7)
			.enchant(Enchantment.ARROW_INFINITE, 1)
			.enchant(Enchantment.ARROW_FIRE, 1)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.CROSSBOW)
			.name("Gaussian Arbalette", TierSpecial.color)
			.lore("3x Carbon Bullets")
			.enchant(Enchantment.QUICK_CHARGE, 4)
			.enchant(Enchantment.ARROW_DAMAGE, 5)
			.enchant(Enchantment.MULTISHOT, 1)
			.enchant(Enchantment.DURABILITY, 7)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.FISHING_ROD)
			.name("Tier IV Fishing Rod", color)
			.enchant(Enchantment.LUCK, 6)
			.enchant(Enchantment.LURE, 6)
			.enchant(Enchantment.DURABILITY, 5)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.DIAMOND_BLOCK).amount(8),
		
		new Prize(Material.NETHERITE_INGOT).amount(32),
		
		new Prize(Material.EMERALD_BLOCK).amount(24),
		
		new Prize(Material.GOLD_BLOCK).amount(16),
		
		new Prize(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE).amount(4),
		
		new Prize(Material.EXPERIENCE_BOTTLE).amount(64),
		
		new Prize(Material.SPAWNER).amount(4),
		
		new Prize(Material.SKELETON_SPAWN_EGG).amount(6),
		
		new Prize(Material.ZOMBIE_SPAWN_EGG).amount(6),
		
		new Prize(Material.VINDICATOR_SPAWN_EGG).amount(3),
		
		new Prize(Material.PILLAGER_SPAWN_EGG).amount(3),
		
		new Prize(Material.BLAZE_SPAWN_EGG).amount(2),
		
		new Prize(Material.SHULKER_SHELL).amount(16),
		
		new Prize(Material.ENCHANTED_GOLDEN_APPLE).amount(16),
		
		new Prize(Material.POTION)
			.name("Potion of Health Boost IV")
			.effect(PotionEffectType.HEALTH_BOOST, 4, 120)
			.amount(3),
			
		new Prize(Material.POTION)
			.name("Potion of Haste II")
			.effect(PotionEffectType.FAST_DIGGING, 2, 960)
			.amount(4),
		
		new Prize(Material.POTION)
			.name("Maximum Force", TierSpecial.color)
			.effect(PotionEffectType.INCREASE_DAMAGE, 5, 90)
			.amount(2),
			
		new Prize(Material.POTION)
			.name("Maximum Speed", TierSpecial.color)
			.effect(PotionEffectType.SPEED, 3, 90)
			.amount(2),
			
		new Prize(Material.POTION)
			.name("Maximum Protection", TierSpecial.color)
			.effect(PotionEffectType.DAMAGE_RESISTANCE, 5, 90)
			.amount(2)
	};
}
