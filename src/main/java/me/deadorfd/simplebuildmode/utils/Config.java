package me.deadorfd.simplebuildmode.utils;

import org.bukkit.configuration.file.FileConfiguration;

import me.deadorfd.simplebuildmode.SimpleBuildMode;

/**
 * @Author DeaDorfd
 * @Project SimpleBuildMode
 * @Package me.deadorfd.simplebuildmode.utils
 * @Date 07.06.2023
 * @Time 18:12:09
 */
public class Config {
	public static FileConfiguration cfg = SimpleBuildMode.getInstance().getConfig();

	public static String getMessage(String path) {
		return cfg.getString("Messages." + path).replaceAll("&", "§");
	}

	public static String getMessagePlayer(String path, String playername) {
		return getMessage(path).replaceAll("%player%", playername);
	}

	public static String getPermission(String path) {
		return cfg.getString("Permissions." + path);
	}

	public static String getString(String path) {
		return cfg.getString(path).replaceAll("&", "§");
	}

	public static boolean getBoolean(String path) {
		return cfg.getBoolean(path);
	}
}
