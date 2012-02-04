package net.marcuswhybrow.minecraft.pvp;

import net.marcuswhybrow.minecraft.cbutils.MessageDispatcher;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		MessageDispatcher.consoleInfo("[PVP Revolution] Started");
	}
	
	@Override
	public void onDisable() {
		MessageDispatcher.consoleInfo("[PVP Revolution] Stopped");
	}

}
