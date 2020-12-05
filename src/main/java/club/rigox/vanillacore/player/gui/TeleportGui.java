package club.rigox.vanillacore.player.gui;

import club.rigox.vanillacore.VanillaCore;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class TeleportGui implements Listener {
    private final VanillaCore plugin;
    private final BiMap<Player, Integer> listUsers = HashBiMap.create();

    private Inventory invList;

    public TeleportGui(VanillaCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    public void openInventory(Player staff) {
        invList = Bukkit.createInventory(null, 54, "Player Teleport");

        int slot = 0;
        for(Player p: Bukkit.getOnlinePlayers()) {
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setOwningPlayer(p);
            meta.setDisplayName(color(plugin.getSetting().getString("teleport-gui.title")
                    .replaceAll("%player%", p.getName())));
            head.setItemMeta(meta);
            invList.setItem(slot, head);
            listUsers.put(p, slot);
            slot++;
        }

        staff.openInventory(invList);
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if (!e.getInventory().equals(invList)) {
            return;
        }
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();
        Player target = listUsers.inverse().get(e.getRawSlot());
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.equals(Material.AIR)) {
            return;
        }

        if (target.equals(player)) {
            player.closeInventory();
            player.sendMessage(color("&cYou can't teleport on yourself!"));
            return;
        }

        player.teleport(target);
        player.sendMessage(color(String.format("&8&l* &fYou have been teleported to &b%s", target.getName())));
    }

}
