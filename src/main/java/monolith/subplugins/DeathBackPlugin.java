package monolith.subplugins;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

import monolith.event.PlayerRespawnListener;
import monolith.command.tpa.DeathCommandHandler;
import monolith.plugin.SubPlugin;

public class DeathBackPlugin extends SubPlugin 
{
	// includes respawn event listener
	// and command to go to last death location
		
	private Listener respawnListener;
	private CommandExecutor deathHandler;

	public DeathBackPlugin() {
		
	}
		
	@Override
	public void onEnable () {
		
		this.respawnListener = new PlayerRespawnListener(this.getServer());
		this.addEventHandler(this.respawnListener);
		
		this.deathHandler = new DeathCommandHandler(this.getServer());
		this.addCommandHandler("death", this.deathHandler);
	}
}
