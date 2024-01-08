package monolith.command.book;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import monolith.core.book.*;
import net.kyori.adventure.text.Component;

public class BookCommandHandler implements CommandExecutor 
{	
	static final String UPGRADE = "upgrade",
						DUPE = "dupe",
						DISENCHANT = "disenchant";
	
	private Server server;
	
	public BookCommandHandler (Server server)
	{
		this.server = server;
	}
	
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args)
	{
		String username = sender.getName();
		Player p = this.server.getPlayer(username);
		if (p != null && args.length >= 1) {
			ItemStack itemHand = p.getInventory().getItemInMainHand();
			String action = args[0].toLowerCase();
			BookModifyResult result;
			switch (action) {
			case UPGRADE:result = BookModifier.tryUpgradeBook(itemHand, p);
				break;
			case DUPE:
				result = BookModifier.tryDupeBook(itemHand, p);
				break;
			case DISENCHANT:
				result = BookModifier.tryDisenchantBook(itemHand, p);
				break;
			default:
				p.sendMessage(Component.text("Error: Action not supported."));
				return false;
			}
			String message = "Book " + action;
			switch (result) {
			case SUCCESS:
				message += ": success!";
				break;
			case TOO_EXPENSIVE:
				message += ": not enough XP Levels.";
				break;
			case TARGET_NOT_SUPPORTED:
				message += ": item in hand is not supported.";
				break;
			}
			p.sendMessage(Component.text(message));
			return true;
		}
		return false;
	}
}
