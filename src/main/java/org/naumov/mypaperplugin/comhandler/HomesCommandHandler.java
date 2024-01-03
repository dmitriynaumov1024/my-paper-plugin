package org.naumov.mypaperplugin.comhandler;

import java.util.List;

import org.bukkit.Server;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.naumov.mypaperplugin.home.Home;
import org.naumov.mypaperplugin.tpa.Teleport;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class HomesCommandHandler implements CommandExecutor 
{
	private Server server;
	private Home home;
	
	public HomesCommandHandler (Server server)
	{
		this.server = server;
		this.home = new Home(server, new Teleport(server));
	}
	
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args)
	{
		String username = sender.getName();
		Player player = this.server.getPlayer(username);
		
		if (player == null) {
			return false;
		}
		
		List<String> homes = this.home.listHomes(player);
		
		if (homes.size() == 0) {
			player.sendMessage(Component.text("You have no homes so far..."));
		}
		else {
			player.sendMessage(Component.text("Your homes: \n" + String.join(", ", homes)));
			player.sendMessage(Component.text(String.format("Used: %d / %d home slots", homes.size(), this.home.getHomeLimit(player))));
		}
		
		return true;
	}
}
