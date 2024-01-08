package monolith.subplugins;

import org.bukkit.command.CommandExecutor;

import monolith.command.misc.DonateCommandHandler;
import monolith.plugin.SubPlugin;

public class MiscUtilsPlugin extends SubPlugin 
{
	// contains donate command
	
	private CommandExecutor donateHandler;
	
	public void onEnable() {
		this.donateHandler = new DonateCommandHandler(this.getServer(), this.getConfig().getStringList("donate"));
		this.addCommandHandler("donate", this.donateHandler);
	}
}
