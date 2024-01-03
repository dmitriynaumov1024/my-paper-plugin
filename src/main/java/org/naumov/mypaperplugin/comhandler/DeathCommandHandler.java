package org.naumov.mypaperplugin.comhandler;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.naumov.mypaperplugin.tpa.Teleport;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class DeathCommandHandler implements CommandExecutor 
{
	final TextColor yellow = TextColor.color(0xfec922);
	final TextColor red = TextColor.color(0xfe3333);
	
	private Server server;
	private Teleport teleport;
	
	public DeathCommandHandler (Server server)
	{
		this.server = server;
		this.teleport = new Teleport(server);
	}
	
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args)
	{
		String username = sender.getName();
		Player player = this.server.getPlayer(username);
		
		if (player == null) {
			return false;
		}
		
		Location loc = player.getLastDeathLocation();
		if (loc != null) {
			this.teleport.tp(player, loc);
			player.sendMessage(Component.text("Teleporting to last death location.").color(yellow));
		}
		else {
			player.sendMessage(Component.text("Seems like you have not died so far.").color(red));
		}
		return true;
	}
}
