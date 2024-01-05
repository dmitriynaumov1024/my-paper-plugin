package org.naumov.mypaperplugin.comhandler;

import org.bukkit.Server;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.naumov.mypaperplugin.home.Home;
import org.naumov.mypaperplugin.player.PlayerIdRepo;
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
		
		// home <home name>
		if (args.length == 1) {
			String homeName = args[0];
			if (this.home.tpHome(player, player, homeName)) {
				player.sendMessage(Component.text("Teleporting to home " + homeName + ".").color(yellow));
			}
			else {
				player.sendMessage(Component.text("Sorry, that home does not exist.").color(red));
			}
		}
		
		// home <owner name> <home name> 
		if (args.length >= 2) {
			String homeName = args[1];
			Player owner = this.server.getPlayer(args[0]);
			if (owner == null) {
				owner = PlayerIdRepo.getByName(args[0]);
			}
			if (owner == null) {
				player.sendMessage(Component.text("Player " + args[0] + " not found.").color(red));
				return true;
			}
			if (this.home.tpHome(player, owner, homeName)) {
				player.sendMessage(Component.text("Teleporting to home " + homeName + ".").color(yellow));
			}
			else {
				player.sendMessage(Component.text("Sorry, that home is private or does not exist.").color(red));
			}
		}
		
		return true;
	}
}