package monolith.subplugins;

import monolith.plugin.SubPlugin;
import org.bukkit.event.Listener;
import monolith.event.PlayerEntityInteractListener;

public class EnchantPlugin extends SubPlugin 
{
	// handles player interact entity event to perform 
	// interactive enchantment of items with help of item frames
	
	private Listener peiListener;
	
	public EnchantPlugin() {
		
	}
	
	public void onEnable() {
		this.peiListener = new PlayerEntityInteractListener(this.getServer());
		this.addEventHandler(peiListener);
	}
}
