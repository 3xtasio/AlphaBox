package fr.alphabox.utils;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Map;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) {
            return;
        }
        if (event.getCurrentItem().getItemMeta() == null) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        InventoryCreate i = null;
        for (InventoryCreate inventory : InventoryCreate.getInvs()) {
            if (inventory.getName().equalsIgnoreCase(event.getInventory().getName())) {
                i = inventory;
            }
        }
        if (i != null) {
            if (!i.getClickable()) {
                event.setCancelled(true);
            }
            if (InventoryCreate.getCommand(event.getSlot()) != null) {
                Map<InventoryCreate, String> map = InventoryCreate.getCommand(event.getSlot());
                    player.chat("/" + map.get(i));
            }
            if (InventoryCreate.getRunnables(event.getSlot()) != null) {
                Map<InventoryCreate, Runnable> map = InventoryCreate.getRunnables(event.getSlot());
                    map.get(i).run();
            }
        }
    }
}

