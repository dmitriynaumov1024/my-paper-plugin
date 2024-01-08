package monolith.command.tpa;

import org.bukkit.Server;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import monolith.core.tpa.Teleport;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class BackCommandHandler implements CommandExecutor 
{
	final TextColor yellow = TextColor.color(0xfec922);
	private Server server;
	private Teleport teleport;
	
	public BackCommandHandler (Server server)
	{
		this.server = server;
		this.teleport = new Teleport(server);
	}
	
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args)
	{
		String username = sender.getName();
		Player player = this.server.getPlayer(username);
		
		if (player == null) {
			return false;
		}
		
		this.teleport.back(player);
		player.sendMessage(Component.text("Teleporting to previous location.").color(yellow));
		
		return true;
	}
}
