package monolith.core.kit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import monolith.core.gamble.Prize;

public abstract class Kit 
{
	protected abstract Prize[] getPrizes ();
	
	// cooldown in SECONDS
	public abstract long getCooldown ();
	
	// name of this kit
	public abstract String getName ();
	
	public ItemStack[] getItems () {
		Prize[] items = this.getPrizes();
		ItemStack[] result = new ItemStack[items.length];
		for (int i=0; i<items.length; i++) {
			result[i] = items[i].getItem();
		}
		return result;
	}
	
	public boolean canGiveTo (Player p) {
		long lastTimeGiven = PlayerKitRepo.getGivenAt(p, this);
		long now = System.currentTimeMillis();
		System.out.printf("Kit %s to %s: \nlast time: %d, now: %d, cooldown: %d\n", this.getName(), p.getName(), lastTimeGiven, now, this.getCooldown() * 1000);
		return (now - lastTimeGiven) >= this.getCooldown() * 1000;
	}
	
	public boolean giveTo (Player p) {
		p.getUniqueId();
		ItemStack[] items = this.getItems();
		PlayerInventory inv = p.getInventory();
		if (inv.firstEmpty() < 0) return false;
		for (ItemStack item : items) {
			if (inv.addItem(item).isEmpty() == false) {
				p.getWorld().dropItem(p.getLocation(), item);
			}
		}
		long now = System.currentTimeMillis();
		PlayerKitRepo.updateGivenAt(p, this, now);
		return true;
	}
}
