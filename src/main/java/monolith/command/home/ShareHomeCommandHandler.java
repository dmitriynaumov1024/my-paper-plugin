package monolith.command.home;

import org.bukkit.Server;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import monolith.core.home.Home;
import monolith.core.tpa.Teleport;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class ShareHomeCommandHandler implements CommandExecutor
{
	final TextColor red = TextColor.color(0xfe3333);
	final TextColor yellow = TextColor.color(0xfec922);
	
	private Server server;
	private Home home;
	
	public ShareHomeCommandHandler (Server server)
	{
		this.server = server;
		this.home = new Home(server, new Teleport(server));
	}
	
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args)
	{
		String username = sender.getName();
		Player player = this.server.getPlayer(username);
		
		if (player == null || args.length < 2) {
			return false;
		}
		
		// sharehome <home name> <peer name | *>
		
		String homeName = args[0].toLowerCase();
		String peerName = args[1];
		
		if (!this.home.listHomes(player).contains(homeName)) {
			player.sendMessage(Component.text("Sorry, that home does not exist.").color(red));
			return true;
		}
		
		if (peerName.startsWith("*")) {
			this.home.shareHomeAll(player, homeName, true);
			player.sendMessage(Component.text("You shared home " + homeName + " with everyone.").color(yellow));
			return true;
		}
		
		Player peer = server.getPlayer(peerName);
		if (peer == null) {
			player.sendMessage(Component.text("Sorry, player " + peerName + " is offline or never played here at all.").color(red));
			return true;
		}
		
		this.home.shareHome(player, peer, homeName, true);
		player.sendMessage(Component.text("Successfully shared home " + homeName + " with " + peer.getName() + ".").color(yellow));
		if (!peer.equals(player)) peer.sendMessage(Component.text(player.getName() + " shared their home " + homeName + " with you.").color(yellow));
		return true;
	}
}
