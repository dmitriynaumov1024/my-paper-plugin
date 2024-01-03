package org.naumov.mypaperplugin.comhandler;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

import org.naumov.mypaperplugin.gamble.*;

public class GambleCommandHandler implements CommandExecutor 
{	
	static final int MIN_LEVEL = 15,
					 PRICE = 5;
	
	private Server server;
	
	public GambleCommandHandler (Server server)
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
		
		if (p.getInventory().firstEmpty() < 0) {
			p.sendMessage(Component.text("You must have at least one empty slot in your inventory to gamble.").color(TextColor.color(0xf05555)));
			return true;
		}
		
		int tier = Casino.getTier(p);
		if (tier < 0) {
			p.sendMessage(Component.text("You must be at least level " + Casino.minLevel + " to gamble.").color(TextColor.color(0xf05555)));
			return true;
		}
		
		ItemStack prize = Casino.getPrize(tier).getItem();
		int count = prize.getAmount();
		Component prizeName = prize.getItemMeta().hasDisplayName() ? 
				prize.getItemMeta().displayName() : 
				Component.text(prize.getI18NDisplayName());

		p.getInventory().addItem(prize);
		p.setLevel(p.getLevel() - PRICE);
		
		p.sendMessage(
			Component
			.text(String.format("You get %s Tier Prize: \n%dx ", Casino.tierNames[tier], count))
			.append(prizeName)
		);
		
		return true;
	}
}
