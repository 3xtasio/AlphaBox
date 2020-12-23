package fr.alphabox.listeners;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class BarbareJump implements Listener{
	
	 public HashMap<String, Long> cooldowns = new HashMap<String, Long>();
	 
	  
	  
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	   Player p = e.getPlayer();
	   cooldowns.put(p.getName(), (long) 0);
	}
	
	@EventHandler
	  public void onInteract(PlayerInteractEvent e) {
	    Player p = e.getPlayer();
	    ItemStack it = p.getItemInHand();
	    if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
	    	if(it != null && it.hasItemMeta()) {
	    		if(it.getItemMeta().getDisplayName() == "§9Kit §7- §cBarbare"){
			      int cooldownTime = 10; 
			      if(cooldowns.containsKey(p.getName())) {
			            long secondsLeft = ((cooldowns.get(p.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
			            if(secondsLeft > 0) {
			                p.sendMessage("§cVous devez patienter encore "+ secondsLeft +" secondes avant d'utiliser votre compétence.");
			            } else {
			            	cooldowns.put(p.getName(), System.currentTimeMillis());
			            	p.setVelocity(p.getLocation().getDirection().multiply(1.25).setY(1));
			            }
			      }
		      
	    		}	 
	    	}
	    }
	    
	}
}
