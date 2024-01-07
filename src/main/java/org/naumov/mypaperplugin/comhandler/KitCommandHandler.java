package org.naumov.mypaperplugin.comhandler;

import java.util.*;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.naumov.mypaperplugin.kit.*;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class KitCommandHandler implements CommandExecutor 
{	
	final static TextColor red = TextColor.color(0xfe3333);
	
	private Server server;
	private HashMap<String, Kit> kits;
	
	public KitCommandHandler (Server server)
	{
		this.server = server;
		this.kits = new HashMap<>();
		Kit[] tmpKits = new Kit[] { new KitStart(), new KitLevel1(), new KitLevel2(), new KitArcher() };
		for (Kit kit : tmpKits) {
			this.kits.put(kit.getName().toLowerCase(), kit);
		}
	}
	
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args)
	{
		String username = sender.getName();
		Player p = this.server.getPlayer(username);
		
		if (p == null) {
			return false;
		}
		
		if (args.length < 1) {
			String kitNames = String.join(", ", this.kits.keySet());
			p.sendMessage(Component.text("Known kit names: \n"+kitNames+"\n"));
			return true;
		}
		
		Kit kit = this.kits.get(args[0].toLowerCase());
		if (kit == null) {
			p.sendMessage(Component.text("Kit " + args[0] + " does not exist.").color(red));
		}
		else if (!kit.canGiveTo(p)) {
			p.sendMessage(Component.text("You can not get this kit because of cooldown.").color(red));
		}
		else if (kit.giveTo(p)) {
			p.sendMessage(Component.text("Giving kit " + args[0] + " to " + p.getName()));
		}
		else {
			p.sendMessage(Component.text("You must have enough space in your inventory to get this kit.").color(red));
		}
		return true;
	}
}
