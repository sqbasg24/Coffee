package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.utils.CommandInterface;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class Staff implements CommandInterface {
    private final VanillaCore plugin;

    public Staff(VanillaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;

        if (!(player.hasPermission("staff.use"))) {
            player.sendMessage(color("&cYou don't have enough permission to use this!"));
            return true;
        }

        if (plugin.getStaffMode().get(player)) {
            player.sendMessage(color("&cStaff mode disabled"));
            plugin.getStaffMode().replace(player, true, false);

            for (Player all : Bukkit.getServer().getOnlinePlayers()) {
                if (all != player) {
                    all.showPlayer(player);
                }
            }
            return true;
        }

        for (Player all : Bukkit.getServer().getOnlinePlayers()) {
            if (all != player) {
                all.hidePlayer(player);
            }

        }

        player.sendMessage(color("&aStaff mode enabled"));
        plugin.getStaffMode().replace(player, false, true);
        return false;
    }
}
