package fr.alphabox.utils;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fr.alphabox.main.Main;
import net.minecraft.server.v1_8_R3.ChatMessage;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenWindow;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class InventoryCreate {
    private static Integer rows;
    private static String name;
    private static org.bukkit.inventory.Inventory inv;
    private static Boolean clickable;
    private static Player handler;

    private static List<InventoryCreate> invs = Lists.newLinkedList();
    private static Map<Integer, Map<InventoryCreate, String>> commands = Maps.newConcurrentMap();
    private static Map<Integer, Map<InventoryCreate, Runnable>> runnables = Maps.newConcurrentMap();


    public InventoryCreate(Player player, String name, Integer rows) {
        inv = Bukkit.createInventory(null, rows*9, name);
        InventoryCreate.name = name;
        InventoryCreate.rows = rows;
        clickable = true;
        handler = player;
    }

    public void fill(ItemStack itemStack) {
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, new ItemCreate(Material.STAINED_GLASS_PANE, (short)7).setNoName().build());
        }
    }

    public void addDelayed(ItemStack itemStack, Long delay) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                add(itemStack);
            }
        }, delay);
    }

    public void setContents(ItemStack[] contents) {
        inv.setContents(contents);
    }

    public void setDelayed(Integer i, ItemStack itemStack, Long delay) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                set(i, itemStack);
            }
        }, delay);
    }


    public void playSound(Sound sound) {
        handler.playSound(handler.getLocation(), sound, 2F, 2F);
    }

    public void playDelayedSound(Sound sound, Long delay) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                playSound(sound);
            }
        }, delay);
    }


    public void add(ItemStack itemStack) {
        inv.addItem(itemStack);
    }

    public void set(Integer i, ItemStack itemBuilder) {
        inv.setItem(i, itemBuilder);
    }

    public void changeName(String string) {
        invs.remove(this);
        name = string;
        EntityPlayer ep = ((CraftPlayer)handler).getHandle();
        PacketPlayOutOpenWindow packet = new PacketPlayOutOpenWindow(ep.activeContainer.windowId, "minecraft:chest", new ChatMessage(string), handler.getOpenInventory().getTopInventory().getSize());
        ep.playerConnection.sendPacket(packet);
        ep.updateInventory(ep.activeContainer);
        invs.add(this);
    }

    public void clear() {
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, new ItemStack(Material.AIR));
        }
    }

    public void setClickable(Boolean b) {
        clickable = b;
    }

    public void close() {
        handler.closeInventory();
    }

    public void addCommand(Integer slot, String command) {
        Map<InventoryCreate, String> map = Maps.newConcurrentMap();
        map.put(this, command);
        commands.put(slot, map);
    }

    public void addRunnable(Integer slot, Runnable runnable) {
        Map<InventoryCreate, Runnable> rb = Maps.newConcurrentMap();
        rb.put(this, runnable);
        runnables.put(slot, rb);
    }


    public void open() {
        handler.openInventory(inv);
        if (!invs.contains(this)) {
            invs.add(this);
        }
    }


    public Boolean getClickable() {
        return clickable;
    }

    public static List<InventoryCreate> getInvs() {
        return invs;
    }

    public static Map<InventoryCreate, Runnable> getRunnables(Integer slot) {
        return runnables.get(slot);
    }

    public static Map<InventoryCreate, String> getCommand(Integer slot) {
        return commands.get(slot);
    }

    public String getName() {
        return name;
    }

    public Boolean contains(ItemStack item) {
        return inv.contains(item);
    }

    public Boolean containsMinimum(ItemStack itemStack, Integer min) {
        return inv.containsAtLeast(itemStack, min);
    }

    public ItemStack getItem(Integer slot) {
        return inv.getItem(slot);
    }

    public Player getPlayer() {
        return handler;
    }

    public List<ItemStack> getContentsAsList() {
        List<ItemStack> list = Lists.newLinkedList();
        for (ItemStack c : getContents()) {
            list.add(c);
        }
        return list;
    }

    public Integer getSize() {
        return rows*9;
    }

    public ItemStack[] getContents() {
        return inv.getContents();
    }
}
