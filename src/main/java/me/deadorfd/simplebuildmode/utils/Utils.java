package me.deadorfd.simplebuildmode.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.deadorfd.simplebuildmode.utils.Config.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author DeaDorfd
 * @Project SimpleBuildMode
 * @Package me.deadorfd.simplebuildmode.utils
 * @Date 07.06.2023
 * @Time 18:12:54
 */
public class Utils {

	private static ArrayList<Player> buildmode = new ArrayList<>();
	private static HashMap<Player, ItemStack[]> content = new HashMap<>();
	private static HashMap<Player, ItemStack[]> armor = new HashMap<>();

	public static boolean hasPermission(Player player, String permission) {
		return player.hasPermission(Config.getPermission("Admin"))
				|| player.hasPermission(Config.getPermission(permission));
	}

	public static String wrongCommand(String command) {
		return getString("Prefix") + getMessage("WrongCommand").replace("%command%", command);
	}

	public static String noPermission() {
		return getString("Prefix") + getMessage("NoPermission");
	}

	public static String mustPlayer() {
		return getMessage("MustPlayer");
	}

	public static HashMap<Player, ItemStack[]> getArmor() {
		return armor;
	}

	public static HashMap<Player, ItemStack[]> getContent() {
		return content;
	}

	public static ArrayList<Player> getBuildmode() {
		return buildmode;
	}

}
