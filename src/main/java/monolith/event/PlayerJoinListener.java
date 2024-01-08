package monolith.event;

import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

import monolith.core.player.PlayerIdRepo;
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
    	Player player = event.getPlayer();
    	PlayerIdRepo.putPlayer(player);
    	
    	if (player.getStatistic(Statistic.PLAY_ONE_MINUTE) < 5) { 
    		// means player is new
	    	this.server.sendMessage(Component.text("Welcome " + player.getName() + "!"));
	    	player.sendMessage(Component.text("To get started, use command /kit start"));
    	}
    	else {
    		player.sendMessage(Component.text("Welcome back " + player.getName() + "!"));
    		player.sendMessage(Component.text("If you like this server, consider donating. \nEvery cent is important to us. [/donate]"));
    	}
    }
}
