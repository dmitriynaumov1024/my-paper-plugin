package monolith.subplugins;

import org.bukkit.command.CommandExecutor;

import monolith.command.pstatus.*;
import monolith.plugin.SubPlugin;

public class PStatusPlugin extends SubPlugin 
{
	// contains commands to get/set afk status, 
	// to get/set busy status, to get statistics
	
	private CommandExecutor
	statsHandler,
	afkHandler,
	busyHandler;
	
	public PStatusPlugin() {
		
	}
	
	public void onEnable() {
		
		this.statsHandler = new StatsCommandHandler(this.getServer());
		this.afkHandler = new AfkCommandHandler(this.getServer());
		this.busyHandler = new BusyCommandHandler(this.getServer());
		
		this.addCommandHandler("stats", this.statsHandler);
		this.addCommandHandler("afk", this.afkHandler);
		this.addCommandHandler("busy", this.busyHandler);
	}
}
