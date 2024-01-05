package org.naumov.mypaperplugin.comhandler;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.naumov.mypaperplugin.pstatus.PlayerStatusRepo;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class AfkCommandHandler implements CommandExecutor
{
	final TextColor red = TextColor.color(0xfe3333);
	final TextColor yellow = TextColor.color(0xfec922);
	
	private Server server;
	
	public AfkCommandHandler (Server server)
	{
		this.server = server;
	}
	
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args)
	{
		String username = sender.getName();
		Player player = this.server.getPlayer(username);
		if (player == null) {
			return false;
		}
		
		// busy
		if (args.length == 0) {
			boolean afk = PlayerStatusRepo.toggleAfk(player);
			if (afk) {
				player.sendMessage(Component.text("You are AFK now.").color(yellow));
			}
			else {
				player.sendMessage(Component.text("You are not AFK now.").color(yellow));
			}
			return true;
		}
		
		// busy <player name>
		if (args.length >= 1) {
			Player other = this.server.getPlayer(args[0]);
			if (other == null) {
				player.sendMessage(Component.text("Player " + args[0] + " not found.").color(red));
				return true;
			}
			boolean afk = PlayerStatusRepo.getAfk(other);
			if (afk) {
				player.sendMessage(Component.text("Player " + args[0] + " is AFK now.").color(yellow));
			}
			else {
				player.sendMessage(Component.text("Player " + args[0] + " is not AFK now.").color(yellow));
			}
			return true;
		}
		
		return true;
	}
}
