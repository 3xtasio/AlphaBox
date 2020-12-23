package fr.alphabox.listeners;

import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;

import fr.alphabox.utils.ParticleEffects;

public class SorcierSort implements Listener{
	public HashMap<String, Long> cooldownsorcier1 = new HashMap<String, Long>();
	public HashMap<String, Long> cooldownsorcier2 = new HashMap<String, Long>();
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	   Player p = e.getPlayer();
	   cooldownsorcier1.put(p.getName(), (long) 0);
	   cooldownsorcier2.put(p.getName(), (long) 0);
	}
	
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {

		Player player = e.getPlayer();
		ItemStack it = player.getItemInHand();
		if(it != null && it.hasItemMeta()) {
			if(it.getItemMeta().getDisplayName().equals("§9Kit §7- §5Sorcier") && it.getType().equals(Material.STICK)) {
	            if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
	    			int cooldownTime = 20; 
	    		    if(cooldownsorcier2.containsKey(player.getName())) {
	    		          long secondsLeft = ((cooldownsorcier2.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
	    		          if(secondsLeft > 0) {
	    		              player.sendMessage("§cVous devez patienter encore "+ secondsLeft +" secondes avant d'utiliser votre compétence.");
	    		          } else {
	    		           cooldownsorcier2.put(player.getName(), System.currentTimeMillis());
	    		           //player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>) null, 50).getLocation());
	    		           LivingEntity victime =  getVisiblePlayer(player);
	    		           if(victime instanceof Player) {
		    		   	    	ParticleEffects.FLAME.display(victime.getLocation(), 2, 0, 1, 20, 0, 10);
		    		   	    	victime.damage(5);
		    		   	    	victime.setFireTicks(100);
		    		   	    	victime.getWorld().createExplosion(victime.getLocation(), 0.0F);  
	    		           }
	    		          }
	    		    }
	    		}
	            
			}
			
			if(it.getItemMeta().getDisplayName().equals("§9Kit §7- §5Sorcier") && it.getType().equals(Material.STICK)) {
	            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
	    			int cooldownTime = 15; 
	    		    if(cooldownsorcier1.containsKey(player.getName())) {
	    		          long secondsLeft = ((cooldownsorcier1.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
	    		          if(secondsLeft > 0) {
	    		              player.sendMessage("§cVous devez patienter encore "+ secondsLeft +" secondes avant d'utiliser votre compétence.");
	    		          } else {
	    		           cooldownsorcier1.put(player.getName(), System.currentTimeMillis());
	    		           //player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>) null, 50).getLocation());
	    		           LivingEntity victime =  getVisiblePlayer(player);
	    		           if(victime instanceof Player) {
				            	victime.setVelocity(victime.getLocation().getDirection().multiply(1.25).setY(1.25));
	    		           }
	    		         }
	    		    }
	    		}	
			}
		}
	}

	  public void explodeOther(Player p, Boolean damage) {
		    boolean found = false;
		    for (Entity entity : p.getNearbyEntities(8, 8, 8)) {
		      if (entity instanceof Player && !found) {
		        Player pl = (Player)entity;
		        explode((LivingEntity)pl, damage);
		        found = true;
		      } 
		    } 
		  }
		  
	  public Player getClosestPlayer(Player p, Integer i) {
	    if (!p.getNearbyEntities(i.intValue(), i.intValue(), i.intValue()).isEmpty()) {
	      Entity ent = p.getNearbyEntities(i.intValue(), i.intValue(), i.intValue()).get(0);
	      if (ent instanceof Player) {
	        Player pl = (Player)ent;
	        return pl;
	      } 
	      return null;
	    } 
	    return null;
	  }
	  
	  
	  public void explode(LivingEntity plr2, Boolean damage) {
	    if (damage.booleanValue()) {
	      for (Entity entity : plr2.getNearbyEntities(5.0D, 5.0D, 5.0D)) {
	        if (entity instanceof LivingEntity) {
	          LivingEntity plr = (LivingEntity)entity;
	          plr.damage(5);
	        } 
	      } 
	      plr2.damage(5);
	    } 
	    ParticleEffects.FLAME.display(plr2.getLocation(), 2, 0, 1, 20, 0, 10);
	    plr2.setFireTicks(80);
	    plr2.getWorld().createExplosion(plr2.getLocation(), 0.0F);
	  }
	  
	  public LivingEntity getVisiblePlayer(Player p) {
	    BlockIterator iterator = new BlockIterator(p.getWorld(), p.getLocation().toVector(), p.getEyeLocation().getDirection(), 0.0D, 100);
	    Entity target = null;
	    while (iterator.hasNext()) {
	      Block item = iterator.next();
	      for (Entity entity : p.getNearbyEntities(100.0D, 100.0D, 100.0D)) {
	        int acc = 2;
	        for (int x = -acc; x < acc; x++) {
	          for (int z = -acc; z < acc; z++) {
	            for (int y = -acc; y < acc; y++) {
	              if (entity.getLocation().getBlock()
	                .getRelative(x, y, z).equals(item))
	                target = entity; 
	            } 
	          } 
	        } 
	      } 
	    } 
	    if (target instanceof LivingEntity)
	      return (LivingEntity)target; 
	    return null;
	  }

}
