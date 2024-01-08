package monolith.subplugins;

import org.bukkit.command.CommandExecutor;

import monolith.command.kit.*;
import monolith.plugin.SubPlugin;

public class KitPlugin extends SubPlugin 
{
	// contains kit command
	
	private CommandExecutor kitHandler;
	
	public KitPlugin() {
		
	}
	
	public void onEnable() {
		this.kitHandler = new KitCommandHandler(this.getServer());
		this.addCommandHandler("kit", this.kitHandler);
		this.addCommandHandler("kits", this.kitHandler);
	}
}
