package monolith.subplugins;

import org.bukkit.event.Listener;

import monolith.event.PlayerJoinListener;
import monolith.plugin.SubPlugin;

public class WelcomePlugin extends SubPlugin 
{
	// contains event listener for player join event
	
	private Listener playerJoinListener;
	
	public WelcomePlugin() {
		
	}
	
	public void onEnable() {
		this.playerJoinListener = new PlayerJoinListener(this.getServer());
		this.addEventHandler(playerJoinListener);
	}
}
