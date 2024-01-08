package monolith.event;

import org.bukkit.Server;
import org.bukkit.event.*;
import org.bukkit.event.inventory.PrepareAnvilEvent;


public class AnvilPrepareListener implements Listener 
{
	private Server server;
	
	public AnvilPrepareListener(Server server)
	{
		this.server = server;
	}
	
	@EventHandler
	public void onAnvilPrepare (PrepareAnvilEvent event)
	{
//		AnvilInventory inventory = event.getInventory();
//		inventory.setMaximumRepairCost(65535);
//		if (inventory.getResult() instanceof ItemStack result) {
//			if (result.getAmount() != 1) return;
//		}
	}
}
