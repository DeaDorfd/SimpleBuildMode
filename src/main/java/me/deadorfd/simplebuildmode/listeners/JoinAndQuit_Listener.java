package me.deadorfd.simplebuildmode.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.deadorfd.simplebuildmode.utils.Check;
import me.deadorfd.simplebuildmode.utils.Config;
import static me.deadorfd.simplebuildmode.utils.Utils.*;

/**
 * @Author DeaDorfd
 * @Project SimpleBuildMode
 * @Package me.deadorfd.simplebuildmode.listeners
 * @Date 07.06.2023
 * @Time 19:19:38
 */
public class JoinAndQuit_Listener implements Listener {

	@EventHandler
	private static void onPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!Config.getBoolean("Check for Updates")) return;
		if (Check.isUpdated()) return;
		if (player.hasPermission(Config.getPermission("Admin"))) {
			player.sendMessage(Config.getString("Prefix") + Config.getMessage("Notify on Join").replaceAll("%link%",
					"https://www.spigotmc.org/resources/" + Check.getResourceID() + "/"));
		}
	}

	@EventHandler
	private static void onPlayerQuitEvent(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if (getBuildmode().contains(player)) {
			player.getInventory().clear();
			player.getInventory().setContents(getContent().get(player));
			player.getInventory().setArmorContents(getArmor().get(player));
			getBuildmode().remove(player);
			getContent().remove(player);
			getArmor().remove(player);
		}
	}

}
