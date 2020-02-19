package io.github.Gogolinolett.Spleef.Spleef;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.plugin.java.JavaPlugin;

public class Spleef extends JavaPlugin {
	public static Connection sqlc;
	public static Spleef plugin;
	
	public void onEnable() {
		plugin = this;
		
		try {
			Files.createDirectories(Paths.get("plugins/WW1Plugin"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			setupDB();
		} catch (SQLException e) {
			getLogger().info("Cannot continue!");
			e.printStackTrace();
			getServer().getPluginManager().disablePlugin(this);
		}
		
		
		getCommand("spleef").setExecutor(new SpleefCommandExecutor());
	}
	
	
	private void setupDB() throws SQLException {
		boolean isNew = !Files.exists(Paths.get(plugin.getDataFolder().getAbsolutePath() + "\\database.db"));
		sqlc = connect(plugin.getDataFolder().getAbsolutePath() + "\\database.db");

		if (isNew) {
			plugin.getLogger().info("Creating DB!");
			
			runSQL("CREATE TABLE Maps(x REAL, y REAL, z REAL, )");
		}
		
	}



	

	public static Connection connect(String path) throws SQLException {
		// SQLite connection string
		String url = "jdbc:sqlite:" + path;
		Connection conn = null;

		conn = DriverManager.getConnection(url);
		return conn;
	}

	public static ResultSet runSQLQuery(String sql) {
		try {
			System.out.println(sql);
			return sqlc.createStatement().executeQuery(sql);
		} catch (SQLException e) {

			throw new RuntimeException(e);

		}
	}

	public static void runSQL(String sql) {

		try {
			System.out.println(sql);
			sqlc.createStatement().execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
