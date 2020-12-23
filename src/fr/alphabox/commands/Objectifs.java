package fr.alphabox.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import fr.alphabox.main.Main;
import fr.alphabox.utils.DefaultFontInfo;
import net.md_5.bungee.api.ChatColor;


public class Objectifs implements CommandExecutor {
	
	@EventHandler
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("objectifs")|| cmd.getName().equalsIgnoreCase("palier")) {
			sendCenteredMessage(player,"§7Voici les §eobjectifs §7en fonction des niveaux:");
			for(int x = 0; x <= 20; x = x+1 ) {
				sendCenteredMessage(player, "§7Niveau §6" + x + " §7- §e"+ Main.getPaliers(x));
				
			}
			player.sendMessage("§e⚠ §cUn nouveau kit est disponible tout les 4 niveaux !");
			return true;
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
