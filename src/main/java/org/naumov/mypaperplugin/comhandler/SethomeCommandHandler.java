package org.naumov.mypaperplugin.comhandler;

import org.bukkit.Server;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.naumov.mypaperplugin.home.Home;
import org.naumov.mypaperplugin.tpa.Teleport;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class SethomeCommandHandler implements CommandExecutor
{
	final TextColor red = TextColor.color(0xfe3333);
	
	private Server server;
	private Home home;
	
	public SethomeCommandHandler (Server server)
	{
		this.server = server;
		this.home = new Home(server, new Teleport(server));
	}
	
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args)
	{
		String username = sender.getName();
		Player player = this.server.getPlayer(username);
		
		if (player == null || args.length < 1) {
			return false;
		}
		
		String homeName = args[0].toLowerCase();
		
		if (this.home.setHome(player, homeName)) {
			player.sendMessage(Component.text("Home " + homeName + " set to current location."));
		}
		else {
			player.sendMessage(Component.text("Sorry, you can not set home, most likely because you exceeded your home limit.").color(red));
		}
		
		return true;
	}
}
