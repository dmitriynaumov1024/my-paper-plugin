package org.naumov.mypaperplugin;

import io.papermc.lib.PaperLib;
import net.kyori.adventure.text.Component;

import org.bukkit.plugin.java.JavaPlugin;
import org.naumov.mypaperplugin.comhandler.*;
import org.naumov.mypaperplugin.evthandler.*;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;

/**
 * @author Dmitriy Naumov
 */
public class MyPaperPlugin extends JavaPlugin
{
	private PlayerJoinListener pjListener;
	private PlayerEntityInteractListener peiListener;
	private StatsCommandHandler scHandler;
	private BookCommandHandler bookHandler;
	private GambleCommandHandler gambleHandler;
	private KitCommandHandler kitHandler;
	private TpaCommandHandler tpaHandler; 
	private DonateCommandHandler donateHandler;
	
	public MyPaperPlugin () 
	{
		// do nothing
	}
	
    @Override
    public void onEnable () 
    {
    	this.pjListener = new PlayerJoinListener(this.getServer());
    	this.peiListener = new PlayerEntityInteractListener(this.getServer());
    	this.scHandler = new StatsCommandHandler(this.getServer());
    	this.bookHandler = new BookCommandHandler(this.getServer());
    	this.gambleHandler = new GambleCommandHandler(this.getServer());
    	this.kitHandler = new KitCommandHandler(this.getServer());
    	this.tpaHandler = new TpaCommandHandler(this.getServer());
    	this.donateHandler = new DonateCommandHandler(this.getServer());
    	
    	this.getServer().getPluginManager().registerEvents(this.pjListener, this);
    	this.getServer().getPluginManager().registerEvents(this.peiListener, this);
    	this.getCommand("stats").setExecutor(this.scHandler);
    	this.getCommand("book").setExecutor(this.bookHandler);
    	this.getCommand("gamble").setExecutor(this.gambleHandler);
    	this.getCommand("kit").setExecutor(this.kitHandler);
    	this.getCommand("tpa").setExecutor(this.tpaHandler);
    	this.getCommand("donate").setExecutor(this.donateHandler);
        this.saveDefaultConfig();
    }
    

    
}
