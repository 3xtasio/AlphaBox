package fr.alphabox.scoreboard;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;

import fr.alphabox.main.Main;

public class Scoreboard implements Listener {
	
	public Map<Player, ScoreboardSign> boards = new HashMap<>();
	

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		Main.infos.put(p, Main.database.getInfos(p));
		int niveau = Main.infos.get(p).get(1);
		ScoreboardSign scoreboard = new ScoreboardSign(p, "§e§lALPHABOX");
		scoreboard.create();
		scoreboard.setLine(0, "§1 ");
		scoreboard.setLine(1, "§7Niveau: §e" + niveau);
		scoreboard.setLine(2, "§4 ");
		scoreboard.setLine(3, "§7Kills: §c " + Main.infos.get(p).get(0));
		scoreboard.setLine(4, "§7Morts: §c " + Main.infos.get(p).get(2));
		scoreboard.setLine(6, "§2 ");
		scoreboard.setLine(7, "§aObjectif: §e" + Main.getPaliers(niveau) + " kills");
		scoreboard.setLine(8, "§5 ");
		scoreboard.setLine(9, "§e    §6play.alphagames.fr");
		boards.put(p,scoreboard);
		event.setJoinMessage(null);;
        for (final PotionEffect pe : p.getActivePotionEffects()) {
            p.removePotionEffect(pe.getType());
        }
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage(null);
		if(boards.containsKey(p)) {
			boards.get(p).destroy();
		}
	}
	
	@EventHandler
	public void onDeath(PlayerRespawnEvent e) {
		Player victim = e.getPlayer();
		Player attacker = e.getPlayer().getKiller();
		if(this.boards.containsKey(victim)) {
			Main.infos.put(victim, Main.database.getInfos(victim));
			int niveau = Main.infos.get(victim).get(1);
		    boards.get(victim).setLine(1, "§7Niveau: §e" + niveau);
			boards.get(victim).setLine(3, "§7Kills: §c " + Main.infos.get(victim).get(0));
			boards.get(victim).setLine(4, "§7Morts: §c " + Main.infos.get(victim).get(2));
			boards.get(victim).setLine(7, "§aObjectif: §e" + Main.getPaliers(niveau) + " kills");
		}
		if(this.boards.containsKey(attacker)) {
			Main.infos.put(attacker, Main.database.getInfos(attacker));
			int niveau = Main.infos.get(attacker).get(1);
		    boards.get(attacker).setLine(1, "§7Niveau: §e" + niveau);
			boards.get(attacker).setLine(3, "§7Kills: §c " + Main.infos.get(attacker).get(0));
			boards.get(attacker).setLine(4, "§7Morts: §c " + Main.infos.get(attacker).get(2));
			boards.get(attacker).setLine(7, "§aObjectif: §e" + Main.getPaliers(niveau) + " kills");
		}
	}
	
}
