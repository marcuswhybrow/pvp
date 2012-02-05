package net.marcuswhybrow.minecraft.pvp;

import net.marcuswhybrow.minecraft.pvp.listeners.CreationListener;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		Manager.setPlugin(this);
		
		// Setup event registrations
		PluginManager pluginManager = getServer().getPluginManager();
		
		pluginManager.registerEvents(new CreationListener(), this);
	}
	
	@Override
	public void onDisable() {
		//TODO onDisable
		
	}
}
