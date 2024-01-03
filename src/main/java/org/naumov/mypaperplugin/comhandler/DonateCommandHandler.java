package org.naumov.mypaperplugin.comhandler;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class DonateCommandHandler implements CommandExecutor 
{	
	private Server server;
	
	public DonateCommandHandler (Server server)
	{
		this.server = server;
	}
	
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args)
	{
		String username = sender.getName();
		Player p = this.server.getPlayer(username);
		
		if (p == null) {
			return false;
		}
		
		Component text = Component.text(
			"Server owner: DMYTRO NAUMOV\n" +
			"Native name: \u041d\u0410\u0423\u041c\u041e\u0412 \u0414\u041c\u0418\u0422\u0420\u041e \u041f\u0410\u0412\u041b\u041e\u0412\u0418\u0427\n" +
			"Mail to: naumov1024@gmail.com\n" +
			"PrivatBank: 5169 3600 0727 1061\n" +
			"IBAN: UA 7930529900000 26206878986863"
		).color(TextColor.color(0x43fe43));
		
		p.sendMessage(text);
		return true;
	}
}
