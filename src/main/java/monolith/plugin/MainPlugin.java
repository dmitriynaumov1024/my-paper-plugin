package monolith.plugin;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import monolith.subplugins.*;

/**
 * @author Dmitriy Naumov
 */
public class MainPlugin extends JavaPlugin
{	
	private Server server;
	private SubPlugin[] subplugins;
	
	public MainPlugin () 
	{
		this.server = this.getServer();
		this.subplugins = new SubPlugin[] {
			new DbConnectionPlugin(),
			new TpaPlugin().useServer(this.server).useMainPlugin(this),
			new HomePlugin().useServer(this.server).useMainPlugin(this),
			new DeathBackPlugin().useServer(this.server).useMainPlugin(this),
			new EnchantPlugin().useServer(this.server).useMainPlugin(this),
			new BookPlugin().useServer(this.server).useMainPlugin(this),
			new GamblePlugin().useServer(this.server).useMainPlugin(this),
			new KitPlugin().useServer(this.server).useMainPlugin(this),
			new PStatusPlugin().useServer(this.server).useMainPlugin(this),
			new WelcomePlugin().useServer(this.server).useMainPlugin(this),
			new MiscUtilsPlugin().useServer(this.server).useMainPlugin(this)
		};
	}
	
    @Override
    public void onEnable () 
    {
    	this.saveDefaultConfig();
    	for (SubPlugin plugin : this.subplugins) {
    		plugin.onEnable();
    	}
    }
    
    @Override
    public void onDisable ()
    {
    	for (SubPlugin plugin : this.subplugins) {
    		plugin.onDisable();
    	}
    }
}
