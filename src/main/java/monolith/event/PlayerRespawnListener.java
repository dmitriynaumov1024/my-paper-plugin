package monolith.event;

import org.bukkit.Server;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.player.PlayerRespawnEvent.RespawnReason;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class PlayerRespawnListener implements Listener
{
	final TextColor cyan = TextColor.color(0x33e0f0);
	
	private Server server;
	
	public PlayerRespawnListener(Server server)
	{
		this.server = server;
	}
	
	@EventHandler
	public void onPlayerRespawn (PlayerRespawnEvent event)
	{
		Player player = event.getPlayer();
		if (player != null && event.getRespawnReason() == RespawnReason.DEATH) {
			player.sendMessage(Component.text("Oops! But do not worry, you can do /death to go to your last death location.").color(cyan));
		}
		
		player.getLastDeathLocation();
	}
}
