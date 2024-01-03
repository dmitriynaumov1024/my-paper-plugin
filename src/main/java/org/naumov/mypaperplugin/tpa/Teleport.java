package org.naumov.mypaperplugin.tpa;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class Teleport 
{
	private Server server;
	
	public Teleport (Server server) {
		this.server = server;
	}
	
	public void tp (Player player, Location target) {
		Location prev = PlayerTpaHistoryRepo.getPrevLocation(player, server);
		if (prev != player.getLocation()) PlayerTpaHistoryRepo.updatePrevLocation(player);
		player.teleport(target);
	}
	
	public void back (Player player) {
		Location target = PlayerTpaHistoryRepo.getPrevLocation(player, this.server);
		tp (player, target);
	}
}
