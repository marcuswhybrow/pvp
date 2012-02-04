package net.marcuswhybrow.minecraft.pvp.listeners;

import net.marcuswhybrow.minecraft.cbutils.MessageDispatcher;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class CreationListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(BlockPlaceEvent event) {
		MessageDispatcher.sendMessage(event.getPlayer(), "you placed a block");
	}
}
