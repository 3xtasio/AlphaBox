package fr.alphabox.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import fr.alphabox.main.Main;
import fr.alphabox.utils.ParticleEffects;
import me.extasio.alphaapi.mysql.PlayerInfo;
import me.extasio.alphaapi.mysql.Rank;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;




public class PlayerListener implements Listener {
    
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		event.setFoodLevel(20);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
			Player player = event.getEntity();
			switch (player.getLastDamageCause().getCause()) {
				case ENTITY_ATTACK:
				case FIRE:
				case FIRE_TICK:
				case PROJECTILE:
	                if (player.getKiller() == null || (!(player.getKiller() instanceof Player))) return;
					Player attacker = player.getKiller();
					event.setDeathMessage("§e" + player.getName() + " §7a été tué par §e" + attacker.getName() + " §7(§e" + Math.round(attacker.getHealth()*100)/100.0 + "❤§7)");
			        event.getDrops().clear();
			        event.setDroppedExp(0);
			        ParticleEffects.HEART.display(attacker.getLocation().add(0, 2, 0), 20, 10, 0, 0, 10, 10);
			        ParticleEffects.EXPLOSION_LARGE.display(player.getLocation().add(0, 1, 0), 20, 10, 0, 0, 10, 10);
			        attacker.setHealth(20);
			        //autorespawn
			        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							PacketPlayInClientCommand packet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
							((CraftPlayer)player).getHandle().playerConnection.a(packet);	
						}
					}, 40L);
			        
			        final int kills = Main.infos.get(attacker).get(0) + 1;
			        final int death = Main.infos.get(player).get(2) + 1;
			        Main.infos.get(attacker).set(0, kills);
			        Main.infos.get(player).set(2, death);
			        //Level Up
			        int level = 1;
			        for (int i = 1; kills >= Main.getPaliers(i); ++i) {
			            level = i + 1;
			        }
			        if (level > Main.infos.get(attacker).get(1)) {
			            Main.infos.get(attacker).set(1, level);
			            attacker.sendMessage("§7----------------------------");
			            attacker.sendMessage("");
			            attacker.sendMessage("§8> §e§lALPHABOX §8<");
			            attacker.sendMessage("");
			            attacker.sendMessage("");
			            attacker.sendMessage("§aVous venez de passer niveau: §e" + level);
			            attacker.sendMessage("");
			            attacker.sendMessage("§8> §a§lNiveau suivant");
			            attacker.sendMessage("§bObjectif: §e" + kills + "§7/§a " + Main.getPaliers(level));
			            attacker.sendMessage("");
			            attacker.sendMessage("§7----------------------------");
			            Bukkit.broadcastMessage("§e" + attacker.getName() + " §7passe niveau §6" + Main.infos.get(attacker).get(1));
			            attacker.playSound(attacker.getLocation(), Sound.LEVEL_UP, 3, 1);
			        }
			        Main.database.setInfos(attacker, Main.infos.get(attacker));  
			        Main.database.setInfos(player, Main.infos.get(player)); 
			        SpawnTP(player);
			        break;
				case FALL:
					event.setDeathMessage("§e" + player.getName() + " §7est mort de chute..");
			        event.getDrops().clear();
			        event.setDroppedExp(0);
			        //autorespawn
			        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							PacketPlayInClientCommand packet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
							((CraftPlayer)player).getHandle().playerConnection.a(packet);	
						}
					}, 40L);
			        
			        final int mort = Main.infos.get(player).get(2) + 1;
			        Main.infos.get(player).set(2, mort);
			        Main.database.setInfos(player, Main.infos.get(player)); 
			        SpawnTP(player);
			        break;
			}
					
		}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		
		if(player.isOp() == false) {
			event.setCancelled(true);
		} else {
			event.setCancelled(false);
		}
	}
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if(player.isOp() == false) {
			event.setCancelled(true);
		} else {
			event.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		PlayerInfo playerInfo = new PlayerInfo(player);
		
		event.setCancelled(true);
		
		if(event.getMessage() != null) {
			event.setFormat( "§7[§e"+ Main.infos.get(player).get(1)+ "§7] "+ Rank.powerToRank(playerInfo.getRank()).getName() + player.getName() + " §7» " + event.getMessage());
			
			
			for (Player players: Bukkit.getOnlinePlayers()){
				players.sendMessage(event.getFormat());
			}
		}
	}
	
	
	public static void SpawnTP(Player player) {
		 World w = Bukkit.getServer().getWorld(Main.getInstance().getConfig().getString("Hub.spawn.world"));
	     double x = Main.getInstance().getConfig().getDouble("Hub.spawn.x");
	     double y = Main.getInstance().getConfig().getDouble("Hub.spawn.y");
	     double z = Main.getInstance().getConfig().getDouble("Hub.spawn.z");
	     float pitch = (float) Main.getInstance().getConfig().getDouble("Hub.spawn.pitch");
	     float yaw = (float) Main.getInstance().getConfig().getDouble("Hub.spawn.yaw");
	     player.teleport(new Location(w, x, y, z, yaw, pitch));
	}
	
	
}

	
