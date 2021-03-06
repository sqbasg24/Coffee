package club.rigox.coffee.commands.admin;

import club.rigox.coffee.Coffee;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.coffee.utils.ConsoleUtils.debug;
import static club.rigox.coffee.utils.MsgUtils.color;

@CommandAlias("setspawn")
@CommandPermission("coffee.setspawn")
public class SetSpawn extends BaseCommand {
    private Coffee plugin;

    public SetSpawn (Coffee plugin) {
        this.plugin = plugin;
    }

    @Default
    public void onSetSpawn(CommandSender sender) {
        if (plugin.getCommandUtils().isConsole(sender)) return;
        Player player = (Player) sender;
        String location = plugin.getCommandUtils().setSpawn(player.getLocation());

        plugin.getSetting().set("spawn.location", location);
        debug(String.format("SetSpawn location has been updated to: %s", location));

        plugin.createConfig("settings");
        plugin.saveSetting();

        player.sendMessage(color("&aYou successfully set the server's spawn!"));
    }
}
