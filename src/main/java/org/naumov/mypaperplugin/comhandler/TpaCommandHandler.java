package org.naumov.mypaperplugin.comhandler;

import org.bukkit.Server;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.naumov.mypaperplugin.pstatus.PlayerStatusRepo;
import org.naumov.mypaperplugin.tpa.Teleport;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class TpaCommandHandler implements CommandExecutor 
{	
	final TextColor yellow = TextColor.color(0xfec922);
	
	private Server server;
	private Teleport teleport;
	
	public TpaCommandHandler (Server server)
	{
		this.server = server;
		this.teleport = new Teleport(server);
	}
	
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args)
	{
		String username = sender.getName();
		Player player = this.server.getPlayer(username);
		
		if (player == null || args.length < 1) {
			return false;
		}
		
		Player target = this.server.getPlayer(args[0]);
		if (target == null) {
			player.sendMessage(Component.text("Player not found.").color(yellow));
			return true;
		}
		if (PlayerStatusRepo.getBusy(target)) {
			player.sendMessage(Component.text("Player " + args[0] + " is busy now. Can not teleport.").color(yellow));
			return true;
		}
		
		this.teleport.tp(player, target.getLocation());
		player.sendMessage(Component.text("Teleported to " + target.getName() + ".").color(yellow));
		target.sendMessage(Component.text(player.getName() + " teleported to you.").color(yellow));
		return true;
	}
}
