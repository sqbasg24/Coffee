package club.rigox.vanillacore.rigox.vanillacore.utils;

import org.bukkit.Bukkit;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class ConsoleUtils {

    public static void debug(String str) {
        Bukkit.getConsoleSender().sendMessage(color(String.format("&f[&9DEBUG&f] %s", str)));
    }
}
