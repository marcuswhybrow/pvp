package net.marcuswhybrow.minecraft.pvp;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class Manager {
	private static Manager self = null;
	private Plugin plugin = null;
	
	private Manager() {
		
	}
	
	private static Manager get() {
		if (self == null) {
			self = new Manager();
		}
		return self;
	}

	/**
	 * @return the plugin
	 */
	public static Plugin getPlugin() {
		return get().plugin;
	}

	/**
	 * @param plugin the plugin to set
	 */
	public static void setPlugin(Plugin plugin) {
		get().plugin = plugin;
	}
	
	/**
	 * Register all events defined for a specific listener.
	 * 
	 * @param listener The listener whose events should be registered.
	 */
	public static void registerEvents(Listener listener) {
		get().plugin.getServer().getPluginManager().registerEvents(listener, get().plugin);
	}
}
