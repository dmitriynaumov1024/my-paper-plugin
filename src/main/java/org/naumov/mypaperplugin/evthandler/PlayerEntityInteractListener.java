package org.naumov.mypaperplugin.evthandler;

import org.bukkit.Server;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.naumov.mypaperplugin.enchant.EnchantResult;
import org.naumov.mypaperplugin.enchant.Enchanter;

import net.kyori.adventure.text.*;
import net.kyori.adventure.text.format.*;


public class PlayerEntityInteractListener implements Listener
{
	final TextColor red = TextColor.color(0xfe3333);
	final TextColor yellow = TextColor.color(0xfec922);
	final TextColor green = TextColor.color(0x33fe35);
	final TextColor cyan = TextColor.color(0x33e0f0);
	
	private Server server;
	
	public PlayerEntityInteractListener(Server server)
	{
		this.server = server;
	}
	
	@EventHandler
	public void onPlayerEntityInteract (PlayerInteractEntityEvent event)
	{
		Player player = event.getPlayer();
		
		if (event.getRightClicked() instanceof ItemFrame itframe) {
			ItemStack source = itframe.getItem();
			ItemStack target = player.getInventory().getItemInMainHand();
			
			if (source == null || source.isEmpty()) return; 
			
			EnchantResult result = Enchanter.handleInteract(source, target, player);
			
			if (result.info) {
				StringBuilder sb = new StringBuilder();
				sb.append("Stored enchantments:");
				for (EnchantResult.Ench ench : result.enchantments) {
					sb.append(String.format("\n+ %s %d", ench.id, ench.level));
				}
				sb.append("\nEnchantment cost: ").append(result.price);
				player.sendMessage(Component.text(sb.toString()));
			}
			
			if (result.ignore) {
				event.setCancelled(true);
				return;
			}
			
			if (result.prepared) {
				player.sendMessage(Component.text("Interact with this item frame again to confirm enchantment.").color(yellow));
			}
			
			if (result.cancelled) {
				player.sendMessage(Component.text("Enchantment cancelled.").color(yellow));
			}
			
			if (result.confirmed) {
				player.sendMessage(Component.text("Successfully enchanted item in your hand!").color(green));
			}
			
			if (result.targetEmpty) {
				player.sendMessage(Component.text("Hold Armor, Tool or Weapon in your main hand to enchant it."));
			}
			
			if (result.targetNotSupported) {
				player.sendMessage(Component.text("This is not supported. You must hold Armor, Tool or Weapon in your main hand to enchant it.").color(yellow));
			}
			
			if (result.targetTypeMismatch) {
				player.sendMessage(Component.text("This is not compatible. You can transfer enchantment only from Tool to Tool, from Armor to Armor, from Weapon to Weapon, or from Book to all other categories.").color(yellow));
			}
			
			if (result.tooExpensive) {
				player.sendMessage(Component.text("Too expensive! Get enough XP levels and try again.").color(yellow));
			}
			
			event.setCancelled(true);
		}
		else {
			return;
		}
	}
}
