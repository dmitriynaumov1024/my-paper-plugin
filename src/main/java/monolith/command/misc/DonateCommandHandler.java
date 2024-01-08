package monolith.command.misc;

import java.util.List;

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
	private String message;
	
	final TextColor green = TextColor.color(0x53f054);
	
	public DonateCommandHandler (Server server, List<String> message)
	{
		this.server = server;
		this.message = String.join("\n", message);
	}
	
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args)
	{
		String username = sender.getName();
		Player p = this.server.getPlayer(username);
		
		if (p == null) {
			return false;
		}
		
		p.sendMessage(Component.text(this.message).color(TextColor.color(green)));
		return true;
	}
}
