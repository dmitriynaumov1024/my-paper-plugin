package org.naumov.mypaperplugin.evthandler;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.naumov.mypaperplugin.enchant.EnchantResult;
import org.naumov.mypaperplugin.enchant.Enchanter;

import net.kyori.adventure.text.*;
import net.kyori.adventure.text.format.*;
import de.tr7zw.changeme.nbtapi.*;


public class PlayerEntityInteractListener implements Listener
{
	private Server server;
	
	public PlayerEntityInteractListener(Server server)
	{
		this.server = server;
	}
	
	@EventHandler
	public void onPlayerEntityInteract (PlayerInteractEntityEvent event)
	{
		Player p = event.getPlayer();
		
		if (event.getRightClicked() instanceof ItemFrame itframe) {
			ItemStack item = itframe.getItem();
			ItemStack hand = p.getInventory().getItemInMainHand();
			
			NBTItem nitem = new NBTItem(item);
			NBTCompoundList lst = nitem.getCompoundList(Enchanter.isBook(item) ? "StoredEnchantments" : "Enchantments");
			Iterable<String> textLines = lst.stream()
				.map((ench) -> String.format("  + %s %d", ench.getString("id"), ench.getShort("lvl")))
				.toList();
			
			p.sendMessage(Component.join(JoinConfiguration.newlines(), 
					Component.text("[i] Stored enchantments:").color(TextColor.color(0x00f0ff)), 
					Component.text(String.join("\n", textLines))));
			
			switch(Enchanter.tryCopyEnchant(item, hand, p)) {
			case SUCCESS:
				p.sendMessage(Component.text("[+] Successfully enchanted item in your hand!")
									   .color(TextColor.color(0x07ff00)));
				break;
			case SOURCE_TARGET_MISMATCH:
				p.sendMessage(Component.text("[x] Target item can not be enchanted with this provider.")
									   .color(TextColor.color(0xf05555)));
				break;
			case TARGET_ALREADY_ENCHANTED:
				p.sendMessage(Component.text("[x] Target is already enchanted. Disenchant it and try again.")
						   .color(TextColor.color(0xf05555)));
				break;
			case TARGET_NOT_READY:
				Enchanter.trySetEnchantReady(hand);
				p.sendMessage(Component.text("Interact with this item frame again to confirm enchant."));
				break;
			case TARGET_NOT_SUPPORTED:
				p.sendMessage(Component.text("[x] Target is not supported.")
						   .color(TextColor.color(0xf05555)));
				break;
			case TOO_EXPENSIVE:
				p.sendMessage(Component.text("[x] Too expensive. Get more XP levels and try again.")
						   .color(TextColor.color(0xf05555)));
				break;
			default:
				p.sendMessage(Component.text("[?] Default switch statement. You should not have seen this! -_-")
						   .color(TextColor.color(0xf05555)));
				break;
			}
			
			event.setCancelled(true);
		}
		else {
			return;
		}
	}
}
