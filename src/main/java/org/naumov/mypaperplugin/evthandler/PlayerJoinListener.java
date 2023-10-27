package org.naumov.mypaperplugin.evthandler;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;

import net.kyori.adventure.text.Component;

public class PlayerJoinListener implements Listener 
{
	private Server server;
	
	public PlayerJoinListener(Server server)
	{
		this.server = server;
	}
	
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event)
    {
    	Player p = event.getPlayer();
    	
    	if (p.getStatistic(Statistic.PLAY_ONE_MINUTE) < 10) { 
    		// means player is new
	    	p.getInventory().addItem(new ItemStack(Material.DIAMOND, 32));
	    	this.server.sendMessage(Component.text("Welcomme " + p.getName() + "!"));
	    	p.sendMessage(Component.text("Here is your welcomme gift."));
    	}
    	else {
    		p.sendMessage(Component.text("Welcome back " + p.getName() + "!"));
    	}
    }
}
