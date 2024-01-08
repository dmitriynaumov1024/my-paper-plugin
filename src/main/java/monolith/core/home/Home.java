package monolith.core.home;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import monolith.core.tpa.Teleport;

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
	
	public Location getHome (Player player, Player owner, String homeName) {
		if (owner == null) {
			return null;
		}
		if (PlayerHomeRepo.getHomeShare(owner, player, homeName)) {
			return PlayerHomeRepo.getHome(owner, homeName, this.server);
		}
		return null;
	}
	
	public boolean setHome (Player player, String homeName) {
		List<String> existingHomes = PlayerHomeRepo.getHomeNames(player, this.server);
		if (existingHomes.contains(homeName)) {
			return PlayerHomeRepo.updateHome(player, homeName, this.server);
		}
		else if (getHomeLimit(player) > existingHomes.size()) {
			return PlayerHomeRepo.insertHome(player, homeName, this.server);
		}
		else {
			return false;
		}
	}
	
	public boolean unsetHome (Player player, String homeName) {
		return PlayerHomeRepo.deleteHome(player, homeName, this.server);
	}
	
	// tp to player's home
	public boolean tpHome (Player player, Player owner, String homeName) {
		if (!PlayerHomeRepo.getHomeShare(owner, player, homeName)) {
			return false;
		}
		Location loc = PlayerHomeRepo.getHome(owner, homeName, this.server);
		if (loc != null) {
			this.teleport.tp(player, loc);
			return true;
		}
		else {
			return false;
		}
	}
	
	// share/unshare this home with player
	public boolean shareHome (Player owner, Player peer, String homeName, boolean share) {
		boolean wasShared = PlayerHomeRepo.getHomeShareStrict(owner, peer, homeName);
		if (share == wasShared) {
			return true;
		}
		if (share) {
			return PlayerHomeRepo.insertHomeShare(owner, peer, homeName);
		}
		if (!share) {
			return PlayerHomeRepo.deleteHomeShare(owner, peer, homeName);
		}
		return false;
	}
	
	// share/unshare this home with anyone (*) 
	public boolean shareHomeAll (Player owner, String homeName, boolean share) {
		boolean wasShared = PlayerHomeRepo.getHomeShareAll(owner, homeName);
		if (share == wasShared) {
			return true;
		}
		if (share) {
			return PlayerHomeRepo.insertHomeShareAll(owner, homeName);
		}
		if (!share) {
			return PlayerHomeRepo.deleteHomeShareAll(owner, homeName);
		}
		return false;
	}
	
	// remove all home share records
	public boolean unshareHomeAtAll (Player owner, String homeName) {
		return PlayerHomeRepo.deleteHomeShareAtAll(owner, homeName);
	}
}
