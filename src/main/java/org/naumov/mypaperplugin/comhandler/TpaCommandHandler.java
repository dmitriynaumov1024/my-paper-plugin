package org.naumov.mypaperplugin.comhandler;

import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class TpaCommandHandler implements CommandExecutor 
{	
	private Server server;
	
	public TpaCommandHandler (Server server)
	{
		this.server = server;
	}
	
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args)
	{
		String username = sender.getName();
		Player p = this.server.getPlayer(username);
		
		if (p == null || args.length < 1) {
			return false;
		}
		
		Player target = this.server.getPlayer(args[0]);
		if (target != null) {
			p.teleport(target);
			p.sendMessage(Component.text("Teleported to " + target.getName() + ".").color(TextColor.color(0xfec922)));
			target.sendMessage(Component.text(p.getName() + " teleported to you.").color(TextColor.color(0xfec922)));
		}
		else {
			p.sendMessage(Component.text("Player not found."));
		}
		
		return true;
	}
}
