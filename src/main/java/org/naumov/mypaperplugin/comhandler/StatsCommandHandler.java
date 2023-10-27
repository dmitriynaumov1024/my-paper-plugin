package org.naumov.mypaperplugin.comhandler;

import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.Component;

public class StatsCommandHandler implements CommandExecutor 
{	
	private Server server;
	
	public StatsCommandHandler (Server server)
	{
		this.server = server;
	}
	
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args)
	{
		String username = sender.getName();
		Player p = this.server.getPlayer(username);
		if (p != null) {
			int playtime = p.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20,
				playS = playtime % 60,
				playM = (playtime / 60) % 60,
				playH = (playtime / 3600) % 24,
				playD = (playtime / 86400);
			String[] textLines = {
				String.format("%s's stats:", username),
				String.format("+ Playtime: %dd %dh %dm", playD, playH, playM),
				String.format("+ Deaths: %d", p.getStatistic(Statistic.DEATHS)),
				String.format("+ More stats coming soon...")
			};
			p.sendMessage(Component.text(String.join("\n", textLines)));
			return true;
		}
		return false;
	}
}
