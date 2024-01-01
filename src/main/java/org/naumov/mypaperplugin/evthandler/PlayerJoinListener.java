package org.naumov.mypaperplugin.evthandler;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;

import net.kyori.adventure.text.Component;

import org.naumov.mypaperplugin.kit.*;

public class PlayerJoinListener implements Listener 
{
	private Server server;	
	private Kit kitStart;
	
	public PlayerJoinListener(Server server)
	{
		this.server = server;
		this.kitStart = new KitStart();
	}
	
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event)
    {
    	Player p = event.getPlayer();
    	
    	if (p.getStatistic(Statistic.PLAY_ONE_MINUTE) < 5) { 
    		// means player is new
	    	this.server.sendMessage(Component.text("Welcome " + p.getName() + "!"));
	    	p.sendMessage(Component.text("To get started, use command /kit start"));
    	}
    	else {
    		p.sendMessage(Component.text("Welcome back " + p.getName() + "!"));
    		p.sendMessage(Component.text("If you like this server, consider donating. \nEvery cent is important to us. [/donate]"));
    	}
    }
}
