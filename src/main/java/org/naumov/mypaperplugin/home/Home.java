package org.naumov.mypaperplugin.home;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.naumov.mypaperplugin.tpa.Teleport;

public class Home 
{
	private Server server;
	private Teleport teleport;
	
	public Home (Server server, Teleport teleport) {
		this.server = server;
		this.teleport = teleport;
	}
	
	public int getHomeLimit (Player player) {
		return 10; // at this time, 10 for everyone
	}
	
	public List<String> listHomes (Player player) {
		return PlayerHomeRepo.getHomeNames(player, this.server);
	}
	
	public boolean setHome (Player player, String homeName) {
		List<String> existingHomes = PlayerHomeRepo.getHomeNames(player, server);
		if (existingHomes.contains(homeName)) {
			return PlayerHomeRepo.updateHome(player, homeName, server);
		}
		else if (getHomeLimit(player) > existingHomes.size()) {
			return PlayerHomeRepo.insertHome(player, homeName, server);
		}
		else {
			return false;
		}
	}
	
	public boolean unsetHome (Player player, String homeName) {
		return PlayerHomeRepo.deleteHome(player, homeName, server);
	}
	
	public boolean tpHome (Player player, String homeName) {
		Location loc = PlayerHomeRepo.getHome(player, homeName, server);
		if (loc != null) {
			this.teleport.tp(player, loc);
			return true;
		}
		else {
			return false;
		}
	}
}
