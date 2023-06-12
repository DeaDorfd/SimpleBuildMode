package me.deadorfd.simplebuildmode.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import static me.deadorfd.simplebuildmode.utils.Utils.*;
import static me.deadorfd.simplebuildmode.utils.Config.*;

/**
 * @Author DeaDorfd
 * @Project Simplebuildmode
 * @Package me.deadorfd.simplebuildmode.commands
 * @Date 07.06.2023
 * @Time 16:38:08
 */
public class Build_Command implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(mustPlayer());
			return true;
		}
		Player player = (Player) sender;
		if (args.length == 0) {
			if (!hasPermission(player, "Build")) {
				player.sendMessage(noPermission());
				return true;
			}
			if (getBuildmode().contains(player)) {
				player.getInventory().clear();
				player.getInventory().setContents(getContent().get(player));
				player.getInventory().setArmorContents(getArmor().get(player));
				getBuildmode().remove(player);
				getContent().remove(player);
				getArmor().remove(player);
				player.sendMessage(getString("Prefix") + getMessage("BuildMode Leave"));
				return true;
			}
			getBuildmode().add(player);
			getContent().put(player, player.getInventory().getContents());
			getArmor().put(player, player.getInventory().getArmorContents());
			player.getInventory().clear();
			player.setGameMode(GameMode.CREATIVE);
			player.sendMessage(getString("Prefix") + getMessage("BuildMode Join"));
			return true;
		} else if (args.length == 1) {
			if (!hasPermission(player, "Build Other")) {
				player.sendMessage(noPermission());
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {

				return true;
			}
			if (getBuildmode().contains(target)) {
				target.getInventory().clear();
				target.getInventory().setContents(getContent().get(target));
				target.getInventory().setArmorContents(getArmor().get(target));
				getBuildmode().remove(target);
				getContent().remove(target);
				getArmor().remove(target);
				player.sendMessage(getString("Prefix") + getMessagePlayer("BuildMode Other Leave", target.getName()));
				target.sendMessage(getString("Prefix") + getMessage("BuildMode Leave"));
				return true;
			}
			getBuildmode().add(target);
			getContent().put(target, target.getInventory().getContents());
			getArmor().put(target, target.getInventory().getArmorContents());
			target.getInventory().clear();
			target.setGameMode(GameMode.CREATIVE);
			player.sendMessage(getString("Prefix") + getMessagePlayer("BuildMode Other Join", target.getName()));
			target.sendMessage(getString("Prefix") + getMessage("BuildMode Join"));
			return true;
		}
		player.sendMessage(wrongCommand("Build <player-name>"));
		return true;
	}
}