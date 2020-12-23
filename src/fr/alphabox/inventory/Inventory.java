package fr.alphabox.inventory;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import fr.alphabox.main.Main;
import me.extasio.alphaapi.mysql.PlayerInfo;
import me.extasio.alphaapi.mysql.Rank;

public class Inventory implements Listener {
	
	ItemStack builder = new ItemBuilder(Material.BOOK).setDisplayName("§a§lRejoindre §7- §e§lAlphaBOX").build();
	/*
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack it = event.getItem();

		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			if(it != null && it.getType() == builder.getType()) {
				
				//item
				player.getInventory().clear();
				player.getEquipment().clear();
				player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("�9Ep�e �7- �eAlphaBox")
						.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
				player.getInventory().setItem(1, new ItemBuilder(Material.BOW).setDisplayName("�9Arc �7- �eAlphaBox")
						.addEnchant(Enchantment.ARROW_DAMAGE, 1).addEnchant(Enchantment.ARROW_INFINITE, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
				player.getInventory().setHelmet(new ItemBuilder(Material.DIAMOND_HELMET).setDisplayName("�9Casque �7- �eAlphaBox")
						.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
				player.getInventory().setChestplate(new ItemBuilder(Material.DIAMOND_CHESTPLATE).setDisplayName("�9Plastron �7- �eAlphaBox")
						.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
				player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS).setDisplayName("�9Jambi�re �7- �eAlphaBox")
						.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
				player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS).setDisplayName("�9Bottes �7- �eAlphaBox")
						.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
				player.getInventory().setItem(27, new ItemBuilder(Material.ARROW).build());
				
				
				
				//tp
				Random r = new Random();
				int rand = r.nextInt( Main.getInstance().getConfig().getInt("Locations.nbposition"));
				int wX = Main.getInstance().getConfig().getInt("Locations." + rand + ".x"),
				wY = Main.getInstance().getConfig().getInt("Locations." + rand + ".y"), 
				wZ = Main.getInstance().getConfig().getInt("Locations." + rand + ".z"),
				yaw = Main.getInstance().getConfig().getInt("Locations." + rand + ".yaw"),
				pitch = Main.getInstance().getConfig().getInt("Locations." + rand + ".pitch");
				World w = Bukkit.getServer().getWorld(Main.getInstance().getConfig().getString("Hub.spawn.world"));
				player.teleport(new Location (w, wX, wY, wZ, yaw, pitch));
				
			}
		}

	}
	*/
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		PlayerInfo playerInfo = new PlayerInfo(player);
        player.getInventory().clear();
	    player.getInventory().setArmorContents(null);
        player.setHealth(20.0);
        player.setFoodLevel(20);
		player.getInventory().setItem(0, builder);
		Main.infos.put(player, Main.database.getInfos(player));
	    player.setPlayerListName("§7[§e"+ Main.infos.get(player).get(1)+ "§7] "+ Rank.powerToRank(playerInfo.getRank()).getName() + player.getName());
	    player.setDisplayName("§7[§e"+ Main.infos.get(player).get(1)+ "§7] "+ Rank.powerToRank(playerInfo.getRank()).getName() + player.getName());
	    /*
        World w = Bukkit.getServer().getWorld(Main.getInstance().getConfig().getString("Hub.spawn.world"));
        double x = Main.getInstance().getConfig().getDouble("Hub.spawn.x");
        double y = Main.getInstance().getConfig().getDouble("Hub.spawn.y");
        double z = Main.getInstance().getConfig().getDouble("Hub.spawn.z");
        float pitch = (float) Main.getInstance().getConfig().getDouble("Hub.spawn.pitch");
        float yaw = (float) Main.getInstance().getConfig().getDouble("Hub.spawn.yaw");
        player.teleport(new Location(w, x, y, z, yaw, pitch));
        */
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		player.getInventory().clear();
		player.getInventory().setItem(0, builder);
	}

}
