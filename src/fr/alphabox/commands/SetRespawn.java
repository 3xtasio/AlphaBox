package fr.alphabox.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import fr.alphabox.main.Main;

public class SetRespawn implements CommandExecutor {
	
	@EventHandler
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("setrespawn")) {
			Main.getInstance().getConfig().set("Hub.spawn.world", player.getLocation().getWorld().getName());
			Main.getInstance().getConfig().set("Hub.spawn.x", player.getLocation().getX());
			Main.getInstance().getConfig().set("Hub.spawn.y", player.getLocation().getY());
			Main.getInstance().getConfig().set("Hub.spawn.z", player.getLocation().getZ());
			Main.getInstance().getConfig().set("Hub.spawn.yaw", player.getLocation().getYaw());
			Main.getInstance().getConfig().set("Hub.spawn.pitch", player.getLocation().getPitch());
			Main.getInstance().saveConfig();
            player.sendMessage("§cSpawn défini!");
            return true;
		}
		return false;
		
	}

}
