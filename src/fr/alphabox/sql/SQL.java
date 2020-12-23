package fr.alphabox.sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.bukkit.entity.Player;

import fr.alphabox.main.Main;

public class SQL
{
    private String url;
    ResultSet resultSet;
    Statement statement;
    String driver;
    String user;
    String database;
    String password;
    String port;
    String host;
    Connection c;
    
    public SQL(final String Host, final String db, final String username, final String password) {
        this.url = "";
        this.resultSet = null;
        this.statement = null;
        this.driver = "";
        this.user = "";
        this.database = "";
        this.password = "";
        this.port = "";
        this.host = "";
        this.c = null;
        this.host = Host;
        this.database = db;
        this.user = username;
        this.password = password;
        this.url = "jdbc:mysql://" + this.host + "/" + this.database + "?user=" + this.user + "&password=" + password;
        this.driver = "com.mysql.jdbc.Driver";
    }
    
    public SQL(final String filePath) {
        this.url = "";
        this.resultSet = null;
        this.statement = null;
        this.driver = "";
        this.user = "";
        this.database = "";
        this.password = "";
        this.port = "";
        this.host = "";
        this.c = null;
        this.url = "jdbc:sqlite:" + new File(filePath).getAbsolutePath();
        this.driver = "org.sqlite.JDBC";
    }
    
    public Connection open() {
        try {
            Class.forName(this.driver);
            return this.c = DriverManager.getConnection(this.url);
        }
        catch (SQLException e) {
            System.out.println("Could not connect to MySQL/SQLite server! because: " + e.getMessage());
        }
        catch (ClassNotFoundException e2) {
            System.out.println("JDBC Driver not found!");
        }
        return this.c;
    }
    
    public boolean checkConnection() {
        return this.c != null;
    }
    
    public Connection getConn() {
        return this.c;
    }
    
    public void closeConnection(Connection c) {
        c = null;
    }
    
    public ResultSet get(final String syntax) {
        ResultSet res = null;
        try {
            res = this.getConn().createStatement(1003, 1007).executeQuery(syntax);
        }
        catch (SQLException e) {
            Main.getInstance().getLogger().severe("Error with a SQL syntax : " + syntax);
            e.printStackTrace();
        }
        return res;
    }
    
    public void set(final String syntax) {
        try {
            this.getConn().createStatement().executeUpdate(syntax);
        }
        catch (SQLException e) {
            Main.getInstance().getLogger().severe("Error with a SQL syntax : " + syntax);
            e.printStackTrace();
        }
    }
    
    public void setNoErrorLog(final String syntax) {
        try {
            this.getConn().createStatement().executeUpdate(syntax);
        }
        catch (SQLException ex) {}
    }
    
    public boolean isTable(final String table) {
        Statement statement;
        try {
            statement = this.getConn().createStatement();
        }
        catch (Exception e) {
            return false;
        }
        try {
            statement.executeQuery("SELECT 1 FROM " + table);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    public void createAccount(final Player player) {
        if (!this.hasAccount(player)) {
            this.set("INSERT INTO `box` (`id`, `name`, `uuid`, `kills`, `deaths`, `lvl`) VALUES (NULL, '" + player.getName() + "', '" + player.getUniqueId() + "', '0', '0', '1');");
        }
    }
    
    public boolean hasAccount(final Player player) {
        final ResultSet res = this.get("SELECT id FROM box WHERE name=('" + player.getName() + "');");
        try {
            return res.first();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Integer> getInfos(final Player player) {
        final ResultSet res = this.get("SELECT * FROM box WHERE name=('" + player.getName() + "');");
        final ArrayList<Integer> infos = new ArrayList<Integer>();
        try {
            if (res.first()) {
                infos.add(res.getInt("kills"));
                infos.add(res.getInt("lvl"));
                infos.add(res.getInt("deaths"));
                return infos;
            }
            infos.add(0);
            infos.add(1);
            infos.add(0);
            return infos;
        }
        catch (SQLException e) {
            e.printStackTrace();
            infos.add(0);
            infos.add(1);
            infos.add(0);
            return infos;
        }
    }

    
    public Object getTop(String column, int amount) {
    	Object array = new Object();
    	ResultSet r = this.get("SELECT * FROM box ORDER BY " + column + " DESC LIMIT " + amount + ";");
		array = r;
		return array;
    }
    
    public void setInfos(final Player player, final ArrayList<Integer> infos) {
        if (this.hasAccount(player)) {
            this.set("UPDATE box SET `kills`='" + infos.get(0) + "', `deaths`='" + infos.get(2) + "', `lvl`='" + infos.get(1) + "' WHERE name='" + player.getName() + "';");
        }
        else {
            this.createAccount(player);
            this.set("UPDATE box SET `kills`='" + infos.get(0) + "', `deaths`='" + infos.get(2) + "', `lvl`='" + infos.get(1) + "' WHERE name='" + player.getName() + "';");
        }
    }
}