package net.marcuswhybrow.minecraft.pvp.listeners;

import net.marcuswhybrow.minecraft.pvp.protection.Area;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class CreationListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(BlockPlaceEvent event) {
		Block block = event.getBlockPlaced();
		if (block.getType() == Material.GOLD_BLOCK) {
			World world = event.getPlayer().getWorld();
			
			// Make some smoke and a steam sound
			world.playEffect(block.getLocation().add(0, 1, 0), Effect.SMOKE, 4);
			world.playEffect(block.getLocation(), Effect.EXTINGUISH, 0);
			
			new Area(event.getPlayer().getName(), block, 10);
		}
	}
}
