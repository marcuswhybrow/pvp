package net.marcuswhybrow.minecraft.pvp.protection;

import net.marcuswhybrow.minecraft.cbutils.Colorise;
import net.marcuswhybrow.minecraft.cbutils.MessageDispatcher;
import net.marcuswhybrow.minecraft.pvp.Manager;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Area implements Listener {
	private Block originBlock;
	private int radius;
	private String ownerName;
	
	public Area(String ownerName, Block block, int radius) {
		if (ownerName == null)
			throw new IllegalArgumentException("ownerName cannot be null");
		
		this.setOriginBlock(block);
		this.setRadius(radius);
		this.setOwnerName(ownerName.toLowerCase());
		
		Manager.registerEvents(this);
	}
	
	/**
	 * Determines whether a location is within this area or not.
	 * 
	 * @param location The location to check.
	 * @return True if the location is within this area, false if not.
	 */
	public boolean isInArea(Location location) {
		
		Location originBlockLocation = originBlock.getLocation();
		
		if (location.getBlockX() > originBlockLocation.getBlockX() + radius)
			return false;
		
		if (location.getBlockX() < originBlockLocation.getBlockX() - radius)
			return false;
		
		if (location.getBlockZ() > originBlockLocation.getBlockZ() + radius)
			return false;
		
		if (location.getBlockZ() < originBlockLocation.getBlockZ() - radius)
			return false;
		
		return true;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		
		if (!this.isOwner(event.getPlayer().getName())
				&& this.isInArea(event.getBlock().getLocation())) {
			event.setCancelled(true);
			MessageDispatcher.sendMessage(event.getPlayer(), Colorise.error("You can't place blocks in this area."));
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (!this.isOwner(event.getPlayer().getName())
				&& this.isInArea(event.getBlock().getLocation())) {
			event.setCancelled(true);
			MessageDispatcher.sendMessage(event.getPlayer(), Colorise.error("You can't break things here."));
		}
		
		if (this.isOwner(event.getPlayer().getName())
				&& event.getBlock().equals(this.getOriginBlock())) {
			event.setCancelled(true);
			MessageDispatcher.sendMessage(event.getPlayer(), Colorise.error("You can't break your protection marker."));
		}
	}

	/**
	 * @return the radius
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * @param radius the radius to set
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	/**
	 * 
	 * @param playerName The name of a player.
	 * @return True if the player owns this area.
	 */
	public boolean isOwner(String playerName) {
		return this.getOwnerName().equals(playerName.toLowerCase());
	}

	/**
	 * @return the originBlock
	 */
	public Block getOriginBlock() {
		return originBlock;
	}

	/**
	 * @param originBlock the originBlock to set
	 */
	public void setOriginBlock(Block originBlock) {
		this.originBlock = originBlock;
	}
}
