package org.naumov.mypaperplugin.comhandler;

import org.bukkit.Server;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.naumov.mypaperplugin.home.Home;
import org.naumov.mypaperplugin.tpa.Teleport;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class UnsethomeCommandHandler implements CommandExecutor
{
	final TextColor red = TextColor.color(0xfe3333);
	final TextColor yellow = TextColor.color(0xfec922);
	
	private Server server;
	private Home home;
	
	public UnsethomeCommandHandler (Server server)
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
		
		if (this.home.unsetHome(player, homeName)) {
			player.sendMessage(Component.text("Home " + homeName + " was deleted even if it did not exist.").color(yellow));
		}
		else {
			player.sendMessage(Component.text("Sorry, you can not delete home most likely because of internal error.").color(red));
		}
		
		return true;
	}
}
