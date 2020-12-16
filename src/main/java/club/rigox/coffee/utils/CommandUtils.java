package club.rigox.coffee.utils;

import club.rigox.coffee.Coffee;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.coffee.utils.MsgUtils.color;

public class CommandUtils {
    private final Coffee plugin;

    public CommandUtils(Coffee plugin) {
        this.plugin = plugin;
    }

    public boolean playerOffline(CommandSender sender, Player target) {
        if (target == null) {
            sender.sendMessage(color(plugin.getLang().getString("player.offline")));
            return true;
        }
        return false;
    }

    public boolean hasPermission(CommandSender sender, String permission) {
        if (sender.hasPermission(permission)) {
            sender.sendMessage(color(plugin.getLang().getString("permission.general-no")));
            return true;
        }
        return false;
    }

    public boolean self(CommandSender sender, Player player) {
        if (sender.equals(player)) {
            sender.sendMessage(color("&cYou can't perform this action to yourself!"));
            return true;
        }
        return false;
    }

    public boolean isConsole(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("only-users")));
            return true;
        }
        return false;
    }
}
