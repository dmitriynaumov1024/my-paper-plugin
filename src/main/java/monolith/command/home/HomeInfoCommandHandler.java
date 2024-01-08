package monolith.command.home;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import monolith.core.home.Home;
import monolith.core.tpa.Teleport;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class HomeInfoCommandHandler implements CommandExecutor
{
	final TextColor red = TextColor.color(0xfe3333);
	final TextColor yellow = TextColor.color(0xfec922);
	
	private Server server;
	private Home home;
	
	public HomeInfoCommandHandler (Server server)
	{
		this.server = server;
		this.home = new Home(server, new Teleport(server));
	}
	
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args)
	{
		String username = sender.getName();
		Player player = this.server.getPlayer(username);
		Player owner = null;
		
		if (player == null || args.length < 1) {
			return false;
		}
		
		String homeName = "";
		Location homeLoc = null;
		
		// homeinfo <home name>
		if (args.length == 1) {
			homeName = args[0].toLowerCase();
			owner = player;
			homeLoc = this.home.getHome(player, player, homeName);
		}
		
		// homeinfo <owner name> <home name> 
		if (args.length >= 2) {
			homeName = args[1].toLowerCase();
			owner = this.server.getPlayer(args[0]);
			homeLoc = this.home.getHome(player, owner, homeName);
		}
		
		if (homeLoc != null) {
			String info = String.format("Home info\n+ Home: %s\n+ Owner: %s\n+ World: %s\n+ Location: %.2f, %.2f, %.2f", 
					homeName, owner.getName(), homeLoc.getWorld().getName(), homeLoc.getX(), homeLoc.getY(), homeLoc.getZ());
			player.sendMessage(Component.text(info));
		}
		else {
			player.sendMessage(Component.text("Sorry, that home does not exist, or it is private, or home owner is offline.").color(red));
		}
		
		return true;
	}
}