package org.naumov.mypaperplugin.comhandler;

import org.bukkit.Server;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.naumov.mypaperplugin.home.Home;
import org.naumov.mypaperplugin.tpa.Teleport;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class HomeCommandHandler implements CommandExecutor
{
	final TextColor red = TextColor.color(0xfe3333);
	final TextColor yellow = TextColor.color(0xfec922);
	
	private Server server;
	private Home home;
	
	public HomeCommandHandler (Server server)
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
		
		if (this.home.tpHome(player, homeName)) {
			player.sendMessage(Component.text("Teleporting to home " + homeName + ".").color(yellow));
		}
		else {
			player.sendMessage(Component.text("Sorry, that home does not exist.").color(red));
		}
		
		return true;
	}
}