package monolith.plugin;

import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class SubPlugin 
{
	private Server server;
	private JavaPlugin plugin;
	
	public final SubPlugin useServer (Server server) {
		this.server = server;
		return this;
	}
	
	public final Server getServer () {
		return this.server;
	}
	
	public final SubPlugin useMainPlugin (JavaPlugin plugin) {
		this.plugin = plugin;
		return this;
	}
	
	public final FileConfiguration getConfig () {
		return this.plugin.getConfig();
	}
	
	public final void addCommandHandler (String command, CommandExecutor handler) {
		this.plugin.getCommand(command).setExecutor(handler);
	}
	
	public final void addEventHandler (Listener handler) {
		this.plugin.getServer().getPluginManager().registerEvents(handler, this.plugin);
	}
	
	public void onEnable() {
		// do nothing
	}
	
	public void onDisable() {
		// do nothing
	}
}
