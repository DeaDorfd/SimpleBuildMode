package me.deadorfd.simplebuildmode.listeners;

import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent.RemoveCause;

import me.deadorfd.simplebuildmode.utils.Utils;

/**
 * @Author DeaDorfd
 * @Project SimpleBuildMode
 * @Package me.deadorfd.simplebuildmode.listeners
 * @Date 07.06.2023
 * @Time 19:29:31
 */
public class BlockEvents_Listener implements Listener {

	@EventHandler
	private static void onBlockBreakEvent(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (Utils.getBuildmode().contains(player)) {
			event.setCancelled(false);
		} else
			event.setCancelled(true);
	}

	@EventHandler
	private static void onBlockPlaceEvent(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (Utils.getBuildmode().contains(player)) {
			event.setCancelled(false);
		} else
			event.setCancelled(true);
	}

	@EventHandler
	private static void onHangingBreakbyEntityEvent(HangingBreakByEntityEvent event) {
		if (!(event.getRemover() instanceof Player)) return;
		if (!event.getCause().equals(RemoveCause.ENTITY)) return;
		Player player = (Player) event.getRemover();
		if (Utils.getBuildmode().contains(player)) {
			event.setCancelled(false);
		} else
			event.setCancelled(true);
	}

	@EventHandler
	private static void onItemRemoveFromItemFrameEvent(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player)) return;
		if (!(event.getEntity() instanceof ItemFrame)) return;
		Player player = (Player) event.getDamager();
		if (Utils.getBuildmode().contains(player)) {
			event.setCancelled(false);
		} else
			event.setCancelled(true);
	}

}