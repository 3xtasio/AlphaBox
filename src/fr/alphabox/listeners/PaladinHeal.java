package fr.alphabox.listeners;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.alphabox.utils.Title;

public class PaladinHeal implements Listener{

	public HashMap<String, Long> cooldownspaladin = new HashMap<String, Long>();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {

		Player player = e.getPlayer();
		ItemStack it = player.getItemInHand();
        Title title = new Title("§6§lCompétence: §dRégénération","",1,0,1);
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
        	if(it != null && it.hasItemMeta()) {
	    		if(it.getItemMeta().getDisplayName() == "§9Kit §7- §dPaladin") {
	    			int cooldownTime = 20; 
	    		    if(cooldownspaladin.containsKey(player.getName())) {
	    		          long secondsLeft = ((cooldownspaladin.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
	    		          if(secondsLeft > 0) {
	    		              player.sendMessage("§cVous devez patienter encore "+ secondsLeft +" secondes avant d'utiliser votre compétence.");
	    		          } else {
	    		           cooldownspaladin.put(player.getName(), System.currentTimeMillis());
	    		           title.send(player);
	    		           player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 1));
	    		          }
	    		      }
	    		}	
        	}
        }
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	   Player p = e.getPlayer();
	   cooldownspaladin.put(p.getName(), (long) 0);
	}
}
