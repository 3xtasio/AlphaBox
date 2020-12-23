package fr.alphabox.commands;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alphabox.main.Main;
import fr.alphabox.utils.DefaultFontInfo;
import net.md_5.bungee.api.ChatColor;

public class Leaderboard implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		Player player = (Player)s;
		if(label.equalsIgnoreCase("leaderboard")|| label.equalsIgnoreCase("top") || label.equalsIgnoreCase("classement")) {
			if(args.length == 0) {
				player.sendMessage("§7Erreur : §cVeuillez faire /classement help");
				return true;
			} 
			if(args.length >= 1) {
				if(args[0].equalsIgnoreCase("kills") || args[0].equalsIgnoreCase("kill")) {
					
					ResultSet result = (ResultSet) Main.database.getTop("kills", 10);
					sendCenteredMessage(player, "§7---------§a Classement des kills §7--------- \n");
					sendCenteredMessage(player, "§b ");
						try {
							while (result.next()) {
								String nom = result.getString("name");
								String kills = result.getString("kills");
								sendCenteredMessage(player, "§e" + nom + " §7- §6" + kills + " §7kills");
								 
								
								
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					sendCenteredMessage(player, "§a ");
					sendCenteredMessage(player, "§7----------------------------------");
					sendCenteredMessage(player, "§a ");
				}	
				if(args[0].equalsIgnoreCase("death") || args[0].equalsIgnoreCase("mort") || args[0].equalsIgnoreCase("deaths") || args[0].equalsIgnoreCase("morts")) {
					
					ResultSet result = (ResultSet) Main.database.getTop("deaths", 10);
					sendCenteredMessage(player, "§7---------§a Classement des morts §7--------- \n");
					sendCenteredMessage(player, "§b ");
						try {
							while (result.next()) {
								String nom = result.getString("name");
								String mort = result.getString("deaths");
								sendCenteredMessage(player, "§e" + nom + " §7- §6" + mort + " §7morts");
								 
								
								
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					sendCenteredMessage(player, "§a ");
					sendCenteredMessage(player, "§7----------------------------------");
					sendCenteredMessage(player, "§a ");
				}				
				if(args[0].equalsIgnoreCase("level") || args[0].equalsIgnoreCase("niveau") || args[0].equalsIgnoreCase("lvl")) {
					ResultSet result = (ResultSet) Main.database.getTop("lvl", 10);
					sendCenteredMessage(player, "§7---------§a Classement des niveaux §7--------- \n");
					sendCenteredMessage(player, "§b ");
						try {
							while (result.next()) {
								String nom = result.getString("name");
								String lvl = result.getString("lvl");
								sendCenteredMessage(player, "§e" + nom + " §7- Niveau§a " + lvl);
								 
								
								
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					sendCenteredMessage(player, "§a ");
					sendCenteredMessage(player, "§7-----------------------------------------");
					sendCenteredMessage(player, "§a ");
				}
				if(args[0].equalsIgnoreCase("help")) {
					s.sendMessage("§7---------------------");
					s.sendMessage("§e- §7/classement level §6| §aPermet de voir le classement des joueurs par §eNiveau");
					s.sendMessage("§e- §7/classement kills §6| §aPermet de voir le classement des joueurs par §eKill");
					s.sendMessage("§7---------------------");
				}
			}
		}
			
		return true;
	}
	
	
	
	
	private final static int CENTER_PX = 154;
	 
	public static void sendCenteredMessage(Player player, String message){
	        if(message == null || message.equals("")) player.sendMessage("");
	                message = ChatColor.translateAlternateColorCodes('&', message);
	               
	                int messagePxSize = 0;
	                boolean previousCode = false;
	                boolean isBold = false;
	               
	                for(char c : message.toCharArray()){
	                        if(c == '§'){
	                                previousCode = true;
	                                continue;
	                        }else if(previousCode == true){
	                                previousCode = false;
	                                if(c == 'l' || c == 'L'){
	                                        isBold = true;
	                                        continue;
	                                }else isBold = false;
	                        }else{
	                                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
	                                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
	                                messagePxSize++;
	                        }
	                }
	               
	                int halvedMessageSize = messagePxSize / 2;
	                int toCompensate = CENTER_PX - halvedMessageSize;
	                int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
	                int compensated = 0;
	                StringBuilder sb = new StringBuilder();
	                while(compensated < toCompensate){
	                        sb.append(" ");
	                        compensated += spaceLength;
	                }
	                player.sendMessage(sb.toString() + message);
	        }
	

}
