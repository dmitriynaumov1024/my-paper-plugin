package org.naumov.mypaperplugin.gamble;

import java.util.*;

import org.bukkit.potion.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import de.tr7zw.changeme.nbtapi.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.*;

public class Prize 
{
	private ItemStack item;
	
	public Prize (Material mat) {
		this.item = new ItemStack(mat);
	}
	
	public Prize amount (int count) {
		this.item.setAmount(count);
		return this;
	}
	
	public Prize enchant (Enchantment ench, int level) {
		this.item.addUnsafeEnchantment(ench, level);
		return this;
	}
	
	public Prize effect (PotionEffectType eff, int level, int duration) {
		PotionMeta meta = (PotionMeta)this.item.getItemMeta();
		meta.addCustomEffect(new PotionEffect(eff, duration*20, level-1), true);
		this.item.setItemMeta(meta);
		return this;
	}
	
	public Prize name (String name) {
		ItemMeta meta = this.item.getItemMeta();
		meta.displayName(Component.text(name).decoration(TextDecoration.ITALIC, false));
		this.item.setItemMeta(meta);
		return this;
	}
	
	public Prize name (String name, int rgb) {
		ItemMeta meta = this.item.getItemMeta();
		meta.displayName(Component.text(name).color(TextColor.color(rgb)).decoration(TextDecoration.ITALIC, false));
		this.item.setItemMeta(meta);
		return this;
	}
	
	public Prize lore (String ... text) {
		List<Component> lore = new ArrayList<>();
		for (String line : text) lore.add(Component.text(line));
		this.item.lore(lore);
		return this;
	}
	
	public ItemStack getItem () {
		return this.item.clone();
	}
}
