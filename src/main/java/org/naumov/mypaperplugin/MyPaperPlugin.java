package org.naumov.mypaperplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.naumov.mypaperplugin.comhandler.*;
import org.naumov.mypaperplugin.evthandler.*;

import java.lang.Runnable;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Dmitriy Naumov
 */
public class MyPaperPlugin extends JavaPlugin
{
	// this may backfire later
	private static MyPaperPlugin instance;
	
	public static MyPaperPlugin instance() {
		return instance;
	}
	
	private List<Runnable> onDisableCallbacks;
	
	private PlayerJoinListener pjListener;
	private PlayerEntityInteractListener peiListener;
	private PlayerRespawnListener prListener;
	
	private StatsCommandHandler scHandler;
	private BookCommandHandler bookHandler;
	private GambleCommandHandler gambleHandler;
	private KitCommandHandler kitHandler;
	private TpaCommandHandler tpaHandler; 
	private BackCommandHandler backHandler;
	private DeathCommandHandler deathHandler;
	private HomesCommandHandler homesHandler;
	private HomeCommandHandler homeHandler;
	private SetHomeCommandHandler sethomeHandler;
	private UnsetHomeCommandHandler unsethomeHandler;
	private HomeInfoCommandHandler homeinfoHandler;
	private ShareHomeCommandHandler sharehomeHandler;
	private UnshareHomeCommandHandler unsharehomeHandler;
	private AfkCommandHandler afkHandler;
	private BusyCommandHandler busyHandler;
	private DonateCommandHandler donateHandler;
	
	public MyPaperPlugin () 
	{
		instance = this;
		this.onDisableCallbacks = new ArrayList<>();
	}
	
    @Override
    public void onEnable () 
    {
    	this.pjListener = new PlayerJoinListener(this.getServer());
    	this.peiListener = new PlayerEntityInteractListener(this.getServer());
    	this.prListener = new PlayerRespawnListener(this.getServer());
    	
    	this.scHandler = new StatsCommandHandler(this.getServer());
    	this.bookHandler = new BookCommandHandler(this.getServer());
    	this.gambleHandler = new GambleCommandHandler(this.getServer());
    	this.kitHandler = new KitCommandHandler(this.getServer());
    	this.tpaHandler = new TpaCommandHandler(this.getServer());
    	this.backHandler = new BackCommandHandler(this.getServer());
    	this.deathHandler = new DeathCommandHandler(this.getServer());
    	this.homesHandler = new HomesCommandHandler(this.getServer());
    	this.homeHandler = new HomeCommandHandler(this.getServer());
    	this.homeinfoHandler = new HomeInfoCommandHandler(this.getServer());
    	this.sethomeHandler = new SetHomeCommandHandler(this.getServer());
    	this.unsethomeHandler = new UnsetHomeCommandHandler(this.getServer());
    	this.sharehomeHandler = new ShareHomeCommandHandler(this.getServer());
    	this.unsharehomeHandler = new UnshareHomeCommandHandler(this.getServer());
    	this.afkHandler = new AfkCommandHandler(this.getServer());
    	this.busyHandler = new BusyCommandHandler(this.getServer());
    	this.donateHandler = new DonateCommandHandler(this.getServer());
    	
    	this.getServer().getPluginManager().registerEvents(this.pjListener, this);
    	this.getServer().getPluginManager().registerEvents(this.peiListener, this);
    	this.getServer().getPluginManager().registerEvents(this.prListener, this);
    	
    	this.getCommand("stats").setExecutor(this.scHandler);
    	this.getCommand("book").setExecutor(this.bookHandler);
    	this.getCommand("gamble").setExecutor(this.gambleHandler);
    	this.getCommand("kit").setExecutor(this.kitHandler);
    	this.getCommand("kits").setExecutor(this.kitHandler);
    	this.getCommand("tpa").setExecutor(this.tpaHandler);
    	this.getCommand("back").setExecutor(this.backHandler);
    	this.getCommand("death").setExecutor(this.deathHandler);
    	this.getCommand("homes").setExecutor(this.homesHandler);
    	this.getCommand("home").setExecutor(this.homeHandler);
    	this.getCommand("homeinfo").setExecutor(this.homeinfoHandler);
    	this.getCommand("sethome").setExecutor(this.sethomeHandler);
    	this.getCommand("unsethome").setExecutor(this.unsethomeHandler);
    	this.getCommand("sharehome").setExecutor(this.sharehomeHandler);
    	this.getCommand("unsharehome").setExecutor(this.unsharehomeHandler);
    	this.getCommand("afk").setExecutor(this.afkHandler);
    	this.getCommand("busy").setExecutor(this.busyHandler);
    	this.getCommand("donate").setExecutor(this.donateHandler);
    	
        this.saveDefaultConfig();
    }
    
    @Override
    public void onDisable ()
    {
    	for (Runnable cb : this.onDisableCallbacks) {
    		cb.run();
    	}
    }
    
    
    public void doOnDisable (Runnable c) 
    {
    	this.onDisableCallbacks.add(c);
    }
}
