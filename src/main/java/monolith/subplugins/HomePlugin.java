package monolith.subplugins;

import org.bukkit.command.CommandExecutor;
import monolith.command.home.*;
import monolith.plugin.SubPlugin;

public class HomePlugin extends SubPlugin
{
	// includes commands to get list of own homes, to get info about home,
	// to set/unset home, to share/unshare home, to teleport to home
	// teleport to home is ambiguous, it may be in either tpa plugin
	// or home plugin
	
	private CommandExecutor 
	homeHandler, 
	homesHandler, 
	homeInfoHandler, 
	setHomeHandler, 
	unsetHomeHandler, 
	shareHomeHandler, 
	unshareHomeHandler;
	
	public HomePlugin() {
    	
	}
	
	@Override
	public void onEnable() {
		
		this.homesHandler = new HomesCommandHandler(this.getServer());
    	this.homeHandler = new HomeCommandHandler(this.getServer());
    	this.homeInfoHandler = new HomeInfoCommandHandler(this.getServer());
    	this.setHomeHandler = new SetHomeCommandHandler(this.getServer());
    	this.unsetHomeHandler = new UnsetHomeCommandHandler(this.getServer());
    	this.shareHomeHandler = new ShareHomeCommandHandler(this.getServer());
    	this.unshareHomeHandler = new UnshareHomeCommandHandler(this.getServer());
		
    	this.addCommandHandler("homes", this.homesHandler);
    	this.addCommandHandler("home", this.homeHandler);
    	this.addCommandHandler("homeinfo", this.homeInfoHandler);
    	this.addCommandHandler("sethome", this.setHomeHandler);
    	this.addCommandHandler("unsethome", this.unsetHomeHandler);
    	this.addCommandHandler("sharehome", this.shareHomeHandler);
    	this.addCommandHandler("unsharehome", this.unshareHomeHandler);
	}
	
}
