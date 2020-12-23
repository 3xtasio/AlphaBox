package fr.alphabox.main;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.alphabox.commands.Leaderboard;
import fr.alphabox.commands.Objectifs;
import fr.alphabox.commands.SetRespawn;
import fr.alphabox.commands.Stats;
import fr.alphabox.inventory.Inventory;
import fr.alphabox.listeners.AngeFly;
import fr.alphabox.listeners.BarbareJump;
import fr.alphabox.listeners.Kits;
import fr.alphabox.listeners.PaladinHeal;
import fr.alphabox.listeners.PlayerListener;
import fr.alphabox.listeners.SorcierSort;
import fr.alphabox.scoreboard.Scoreboard;
import fr.alphabox.sql.SQL;
import fr.alphabox.utils.InventoryListener;

public class Main extends JavaPlugin {
    private static Main instance;
    
	private Connection connection;
	public String host;

	public static SQL database;

	public String username;
	public String base;
	public String password;

	public String table;
	public int port;
    public static HashMap<Player, ArrayList<Integer>> infos;
	static {Main.infos = new HashMap<Player, ArrayList<Integer>>();
	
	}
	
	
	
	
	
	@Override
	public void onEnable() {
		Main.instance = this;
		saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new Scoreboard(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        this.getServer().getPluginManager().registerEvents(new Inventory(), this);
        this.getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        this.getServer().getPluginManager().registerEvents(new Kits(), this);
        this.getServer().getPluginManager().registerEvents(new BarbareJump(), this);
        this.getServer().getPluginManager().registerEvents(new PaladinHeal(), this);
        this.getServer().getPluginManager().registerEvents(new SorcierSort(), this);
        this.getServer().getPluginManager().registerEvents(new AngeFly(), this);
        this.getCommand("setrespawn").setExecutor((CommandExecutor) new SetRespawn());
        this.getCommand("stats").setExecutor((CommandExecutor) new Stats());
        this.getCommand("leaderboard").setExecutor((CommandExecutor) new Leaderboard());
        this.getCommand("objectifs").setExecutor((CommandExecutor) new Objectifs());
        this.mysqlsetup();
		System.out.println("Plugin PvPBox activé");
	}
	
	
    public static Main getInstance() {
        return Main.instance;
    }
    
	@Override
	public void onDisable() {
		System.out.println("Plugin PvPBox désactivé");
	}
	
	
	
	
	public void mysqlsetup() {
		host = Main.getInstance().getConfig().getString("Stockage.host");
		base = Main.getInstance().getConfig().getString("Stockage.database");
		username = Main.getInstance().getConfig().getString("Stockage.username");
		password = Main.getInstance().getConfig().getString("Stockage.password");
		port = Main.getInstance().getConfig().getInt("Stockage.port");
		table = Main.getInstance().getConfig().getString("Stockage.table");
		(Main.database = new SQL(host, base, username, password)).open();
		final boolean table = Main.database.isTable("box");
        if (!table) {
            Main.database.set("CREATE TABLE IF NOT EXISTS `box` ( `id` int(11) NOT NULL AUTO_INCREMENT, `name` varchar(30) NOT NULL, `uuid` varchar(255) NOT NULL, `kills` int(11) NOT NULL, `deaths` int(11) NOT NULL, `lvl` int(11) NOT NULL, PRIMARY KEY (`id`) ) AUTO_INCREMENT=1 ;");
        }
	}
	
    public static int getPaliers(int level) {
        if (level == 1) {
            return 10;
        }
        if (level == 2) {
            return 25;
        }
        if (level == 3) {
            return 50;
        }
        if (level == 4) {
            return 100;
        }
        if (level == 5) {
            return 150;
        }
        if (level == 6) {
            return 200;
        }
        if (level == 7) {
            return 300;
        }
        if (level == 8) {
            return 400;
        }
        if (level == 9) {
            return 500;
        }
        if (level == 10) {
            return 750;
        }
        if (level == 11) {
            return 900;
        }
        if (level == 12) {
            return 1000;
        }
        if (level >= 13 && level < 20) {
            return (level*90 + 250);
        }
        if (level >= 20) {
            return (10000);
        }
        return 0;
    }

	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
