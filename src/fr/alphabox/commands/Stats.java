package fr.alphabox.commands;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alphabox.main.Main;


public class Stats implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		Player player = (Player)s;
		if(label.equalsIgnoreCase("stats")) {
			if(args.length == 0) {
				double ratio = Math.round(Main.infos.get(player).get(0) / Main.infos.get(player).get(2));
				s.sendMessage("");
				s.sendMessage("§8-----------------");
				s.sendMessage("");
				s.sendMessage("§7Information sur §a" + player.getDisplayName());
				s.sendMessage("");
				s.sendMessage("§8• §7Niveau : §e" + Main.infos.get(player).get(1));
				s.sendMessage("");
				s.sendMessage("§8• §7Kills : §e" + Main.infos.get(player).get(0));
				s.sendMessage("§8• §7Morts : §e" + Main.infos.get(player).get(2));
				s.sendMessage("§8• §7Ratio : §e" + ratio);
				s.sendMessage("");
				s.sendMessage("§aObjectif: §e" + Main.getPaliers(Main.infos.get(player).get(1)) + " kills");
				s.sendMessage("");
				s.sendMessage("§8-----------------");
				s.sendMessage("");
			} else {
				Player target = Bukkit.getPlayerExact(args[0]);
				if(target == null) {
					s.sendMessage("§cErreur: Le joueur n'est pas en ligne");
				} else {
					double ratio = Math.round(Main.infos.get(target).get(0) / Main.infos.get(player).get(2));
					s.sendMessage("");
					s.sendMessage("§8-----------------");
					s.sendMessage("");
					s.sendMessage("§7Information sur §a" + target.getDisplayName());
					s.sendMessage("");
					s.sendMessage("§8• §7Niveau : §e" + Main.infos.get(target).get(1));
					s.sendMessage("");
					s.sendMessage("§8• §7Kills : §e" + Main.infos.get(target).get(0));
					s.sendMessage("§8• §7Morts : §e" + Main.infos.get(player).get(2));
					s.sendMessage("§8• §7Ratio : §e" + ratio);
					s.sendMessage("");
					s.sendMessage("§aObjectif: §e" + Main.getPaliers(Main.infos.get(target).get(1)) + " kills");
					s.sendMessage("");
					s.sendMessage("§8-----------------");
					s.sendMessage("");
				}	
			
			}
			
		}
		return true;
	}

	
	
}
