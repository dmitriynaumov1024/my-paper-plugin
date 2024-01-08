package monolith.core.gamble;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffectType;

public class Tier2 
{
	static int color = 0xf06411;
	
	// Tier III, Epic
	public static Prize[] prizes = new Prize[] {
			
		new Prize(Material.NETHERITE_SWORD)
			.name("Tier III Sword", color)
			.enchant(Enchantment.DAMAGE_ALL, 5)
			.enchant(Enchantment.FIRE_ASPECT, 2)
			.enchant(Enchantment.SWEEPING_EDGE, 2)
			.enchant(Enchantment.KNOCKBACK, 1)
			.enchant(Enchantment.DURABILITY, 5)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.NETHERITE_SWORD)
			.name("Cartesian Sword", TierSpecial.color)
			.enchant(Enchantment.DAMAGE_ALL, 5)
			.enchant(Enchantment.FIRE_ASPECT, 2)
			.enchant(Enchantment.SWEEPING_EDGE, 2)
			.enchant(Enchantment.KNOCKBACK, 2)
			.enchant(Enchantment.DURABILITY, 5)
			.enchant(Enchantment.LOOT_BONUS_MOBS, 2)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.NETHERITE_SWORD)
			.name("Progressive Knife", TierSpecial.color)
			.lore("Cut angel's throat!")
			.enchant(Enchantment.DAMAGE_ALL, 4)
			.enchant(Enchantment.SWEEPING_EDGE, 2)
			.enchant(Enchantment.KNOCKBACK, 1)
			.enchant(Enchantment.DURABILITY, 3),
		
		new Prize(Material.NETHERITE_PICKAXE)
			.name("Tier III.S Pickaxe", color)
			.enchant(Enchantment.DIG_SPEED, 5)
			.enchant(Enchantment.DURABILITY, 5)
			.enchant(Enchantment.SILK_TOUCH, 1)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.NETHERITE_PICKAXE)
			.name("Tier III.F Pickaxe", color)
			.enchant(Enchantment.DIG_SPEED, 5)
			.enchant(Enchantment.DURABILITY, 5)
			.enchant(Enchantment.LOOT_BONUS_BLOCKS, 5)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.NETHERITE_AXE)
			.name("Tier III Axe", color)
			.enchant(Enchantment.DIG_SPEED, 5)
			.enchant(Enchantment.DURABILITY, 5)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.NETHERITE_SHOVEL)
			.name("Tier III Shovel", color)
			.enchant(Enchantment.DIG_SPEED, 4)
			.enchant(Enchantment.DURABILITY, 4)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.BOW)
			.name("Tier III Bow", color)
			.enchant(Enchantment.ARROW_DAMAGE, 5)
			.enchant(Enchantment.DURABILITY, 5)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.FISHING_ROD)
			.name("Tier III Fishing Rod", color)
			.enchant(Enchantment.LUCK, 5)
			.enchant(Enchantment.LURE, 5)
			.enchant(Enchantment.DURABILITY, 3)
			.enchant(Enchantment.MENDING, 1),
			
		new Prize(Material.DIAMOND).amount(40),
		
		new Prize(Material.NETHERITE_INGOT).amount(16),
		
		new Prize(Material.EMERALD_BLOCK).amount(8),
		
		new Prize(Material.GOLD_BLOCK).amount(8),
		
		new Prize(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE).amount(2),
		
		new Prize(Material.EXPERIENCE_BOTTLE).amount(32),
		
		new Prize(Material.SPAWNER).amount(2),
		
		new Prize(Material.SKELETON_SPAWN_EGG).amount(3),
		
		new Prize(Material.ZOMBIE_SPAWN_EGG).amount(4),
		
		new Prize(Material.SHULKER_SHELL).amount(8),
		
		new Prize(Material.ENCHANTED_GOLDEN_APPLE).amount(8),
		
		new Prize(Material.POTION)
			.name("Potion of Health Boost III")
			.effect(PotionEffectType.HEALTH_BOOST, 3, 90)
			.amount(3),
			
		new Prize(Material.POTION)
			.name("Potion of Haste II")
			.effect(PotionEffectType.FAST_DIGGING, 2, 960)
			.amount(2),
			
		new Prize(Material.POTION)
			.name("Maximum Force", TierSpecial.color)
			.effect(PotionEffectType.INCREASE_DAMAGE, 5, 90)
			.amount(1),
			
		new Prize(Material.POTION)
			.name("Maximum Speed", TierSpecial.color)
			.effect(PotionEffectType.SPEED, 3, 90)
			.amount(1),
			
		new Prize(Material.POTION)
			.name("Maximum Protection", TierSpecial.color)
			.effect(PotionEffectType.DAMAGE_RESISTANCE, 5, 90)
			.amount(1)
	};
}
