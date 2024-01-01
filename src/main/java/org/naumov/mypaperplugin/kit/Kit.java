package org.naumov.mypaperplugin.kit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.naumov.mypaperplugin.gamble.Prize;

public abstract class Kit 
{
	protected abstract Prize[] getPrizes ();
	
	public ItemStack[] getItems () {
		Prize[] items = this.getPrizes();
		ItemStack[] result = new ItemStack[items.length];
		for (int i=0; i<items.length; i++) {
			result[i] = items[i].getItem();
		}
		return result;
	}
	
	public boolean giveTo (Player p) {
		ItemStack[] items = this.getItems();
		PlayerInventory inv = p.getInventory();
		if (inv.firstEmpty() < 0) return false;
		for (ItemStack item : items) {
			if (inv.addItem(item).isEmpty() == false) {
				p.getWorld().dropItem(p.getLocation(), item);
			}
		}
		return true;
	}
}
