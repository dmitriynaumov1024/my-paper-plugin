package monolith.subplugins;

import org.bukkit.command.CommandExecutor;

import monolith.command.gamble.GambleCommandHandler;
import monolith.plugin.SubPlugin;

public class GamblePlugin extends SubPlugin 
{
	// Contains gamble command to participate in lossless gambling and get some interesting prizes.
	// The higher player level - the better prizes they can get.
	
	private CommandExecutor gambleHandler;
	
	public GamblePlugin() {
		
	}
	
	public void onEnable() {
		this.gambleHandler = new GambleCommandHandler(this.getServer());
		this.addCommandHandler("gamble", this.gambleHandler);
	}
}
