package monolith.core.pstatus;

import java.util.*;

import org.bukkit.entity.Player;

public class PlayerStatusRepo 
{
	private static Map<String, PlayerStatus> playerStats;
	
	static {
		playerStats = new HashMap<>();
	}
	
	private static PlayerStatus getStatusOf (Player player) {
		String id = player.getUniqueId().toString();
		PlayerStatus result = playerStats.get(id);
		if (result == null) {
			result = new PlayerStatus();
			playerStats.put(id, result);
		}
		return result;
	}
	
	private static void setStatusOf (Player player, PlayerStatus status) {
		String id = player.getUniqueId().toString();
		playerStats.put(id, status);
	}
	
	public static boolean getAfk (Player player) {
		return getStatusOf(player).isAfk;
	}
	
	public static boolean setAfk (Player player, boolean afk) {
		PlayerStatus status = getStatusOf(player);
		status.isAfk = afk;
		setStatusOf(player, status);
		return status.isAfk;
	}
	
	public static boolean toggleAfk (Player player) {
		PlayerStatus status = getStatusOf(player);
		status.isAfk = !status.isAfk;
		setStatusOf(player, status);
		return status.isAfk;
	}
	
	public static boolean getBusy (Player player) {
		return getStatusOf(player).isBusy;
	}
	
	public static boolean setBusy (Player player, boolean busy) {
		PlayerStatus status = getStatusOf(player);
		status.isBusy = busy;
		setStatusOf(player, status);
		return status.isBusy;
	}
	
	public static boolean toggleBusy (Player player) {
		PlayerStatus status = getStatusOf(player);
		status.isBusy = !status.isBusy;
		setStatusOf(player, status);
		return status.isBusy;
	}
}
