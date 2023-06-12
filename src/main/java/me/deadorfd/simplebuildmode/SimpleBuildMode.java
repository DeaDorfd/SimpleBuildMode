package me.deadorfd.simplebuildmode;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static me.deadorfd.simplebuildmode.utils.Utils.*;
import me.deadorfd.simplebuildmode.commands.Build_Command;
import me.deadorfd.simplebuildmode.listeners.BlockEvents_Listener;
import me.deadorfd.simplebuildmode.listeners.JoinAndQuit_Listener;
import me.deadorfd.simplebuildmode.utils.Metrics;

/**
 * @Author DeaDorfd
 * @Project SimpleBuildMode
 * @Package me.deadorfd.simplebuildmode
 * @Date 07.06.2023
 * @Time 16:36:18
 */
public class SimpleBuildMode extends JavaPlugin {

	private static SimpleBuildMode instance;

	@Override
	public void onEnable() {
		instance = this;
		getDataFolder().mkdir();
		saveDefaultConfig();
		getCommand("Build").setExecutor(new Build_Command());
		Bukkit.getPluginManager().registerEvents(new JoinAndQuit_Listener(), instance);
		Bukkit.getPluginManager().registerEvents(new BlockEvents_Listener(), instance);
		Metrics metrics = new Metrics(instance, 18681);
	}

	@Override
	public void onDisable() {
		getBuildmode().forEach(player -> {
			player.getInventory().clear();
			player.getInventory().setContents(getContent().get(player));
			player.getInventory().setArmorContents(getArmor().get(player));
			getContent().remove(player);
			getArmor().remove(player);
		});
		getBuildmode().clear();
	}

	public static SimpleBuildMode getInstance() {
		return instance;
	}
}