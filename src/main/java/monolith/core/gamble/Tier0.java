package monolith.core.gamble;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class Tier0 
{
	// Tier I, common
	public static Prize[] prizes = new Prize[] {
			
		new Prize(Material.NETHERITE_SWORD)
			.name("Tier I Sword")
			.enchant(Enchantment.DAMAGE_ALL, 1)
			.enchant(Enchantment.DURABILITY, 1),
			
		new Prize(Material.NETHERITE_PICKAXE)
			.name("Tier I Pickaxe")
			.enchant(Enchantment.DIG_SPEED, 1)
			.enchant(Enchantment.DURABILITY, 1),
			
		new Prize(Material.NETHERITE_AXE)
			.name("Tier I Axe")
			.enchant(Enchantment.DIG_SPEED, 1)
			.enchant(Enchantment.DURABILITY, 1),
			
		new Prize(Material.NETHERITE_SHOVEL)
			.name("Tier I Shovel")
			.enchant(Enchantment.DIG_SPEED, 1)
			.enchant(Enchantment.DURABILITY, 1),
			
		new Prize(Material.BOW)
			.name("Tier I Bow")
			.enchant(Enchantment.ARROW_DAMAGE, 1)
			.enchant(Enchantment.DURABILITY, 1),
			
		new Prize(Material.FISHING_ROD)
			.name("Tier I Fishing Rod")
			.enchant(Enchantment.LUCK, 1)
			.enchant(Enchantment.LURE, 1),
			
		new Prize(Material.DIAMOND).amount(12),
		
		new Prize(Material.NETHERITE_INGOT).amount(2),
		
		new Prize(Material.EMERALD).amount(8),
		
		new Prize(Material.GOLD_INGOT).amount(16),
		
		new Prize(Material.IRON_INGOT).amount(32),
		
		new Prize(Material.OAK_LOG).amount(32),
		
		new Prize(Material.PAPER).amount(63),
		
		new Prize(Material.MELON).amount(8),
		
		new Prize(Material.COOKED_BEEF).amount(32),
		
		new Prize(Material.GOLDEN_APPLE).amount(4)
	};
}
