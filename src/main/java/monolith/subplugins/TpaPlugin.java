package monolith.subplugins;

import org.bukkit.command.CommandExecutor;
import monolith.command.tpa.*;
import monolith.plugin.SubPlugin;

public class TpaPlugin extends SubPlugin
{
	// includes commands to teleport to player and to go back
	
	private CommandExecutor 
	tpaHandler, 
	backHandler;
	
	public TpaPlugin() {
		
	}
	
	@Override
	public void onEnable () {
		
		this.tpaHandler = new TpaCommandHandler(this.getServer());
		this.backHandler = new BackCommandHandler(this.getServer());
		
		this.addCommandHandler("tpa", this.tpaHandler);
		this.addCommandHandler("back", this.backHandler);
	}
	
}
