package org.naumov.mypaperplugin.comhandler;

import java.util.*;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.naumov.mypaperplugin.kit.*;

import net.kyori.adventure.text.Component;

public class KitCommandHandler implements CommandExecutor 
{	
	private Server server;
	private HashMap<String, Kit> kits;
	
	public KitCommandHandler (Server server)
	{
		this.server = server;
		this.kits = new HashMap<>();
		this.kits.put("start", new KitStart());
	}
	
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args)
	{
		String username = sender.getName();
		Player p = this.server.getPlayer(username);
		
		if (p == null || args.length < 1) {
			return false;
		}
		
		Kit kit = this.kits.get(args[0].toLowerCase());
		if (kit == null) {
			p.sendMessage(Component.text("Sorry, kit " + args[0] + " does not exist."));
		}
		else if (kit.giveTo(p)) {
			p.sendMessage(Component.text("Giving kit " + args[0] + " to " + p.getName()));
		}
		else {
			p.sendMessage(Component.text("You must have enough space in your inventory to get this kit."));
		}
		return true;
	}
}
