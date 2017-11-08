package link.mc.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import link.mc.McLink;

public class PlayerUtil {
	
	public static boolean initDataset(String uuid) {
		try {
			//System.out.println(McLink.instance.database.getConnection().isClosed());
			String query = "SELECT * FROM users WHERE uuid = ?";
			PreparedStatement prepStmt = McLink.instance.database.getConnection().prepareStatement(query);
			prepStmt.setString(1, uuid);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				rs.close();
				return true;
			}
			rs.close();
		} catch (Exception e) {e.printStackTrace();};
		
		try {
			String query = "INSERT INTO users (uuid, money) VALUES (?, ?)";
			PreparedStatement prepStmt = McLink.instance.database.getConnection().prepareStatement(query);
			prepStmt.setString(1, uuid);
			prepStmt.setFloat(2, 100);
			prepStmt.executeUpdate();
			prepStmt.close();
			McLink.instance.database.getConnection().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public static void setMoney(String uuid, float newAmount) {
		PlayerUtil.initDataset(uuid);
		
		try {
			String query = "UPDATE users SET money = ? WHERE uuid = ?";
			PreparedStatement prepStmt = McLink.instance.database.getConnection().prepareStatement(query);
			prepStmt.setFloat(1, newAmount);
			prepStmt.setString(2, uuid);
			prepStmt.executeUpdate();
			prepStmt.close();
			McLink.instance.database.getConnection().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getPlayerDiscord(String uuid) {
		PlayerUtil.initDataset(uuid);
		
		try {
			//System.out.println(McLink.instance.database.getConnection().isClosed());
			String query = "SELECT * FROM users WHERE uuid = ?";
			PreparedStatement prepStmt = McLink.instance.database.getConnection().prepareStatement(query);
			prepStmt.setString(1, uuid);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				String discord = rs.getString("discord");
				rs.close();
				return discord;
			}
			rs.close();
		} catch (Exception e) {e.printStackTrace();};
		
		return null;
	}
	
	public static long getMoney(String uuid) {
		PlayerUtil.initDataset(uuid);

		try {
			//System.out.println(McLink.instance.database.getConnection().isClosed());
			String query = "SELECT * FROM users WHERE uuid = ?";
			PreparedStatement prepStmt = McLink.instance.database.getConnection().prepareStatement(query);
			prepStmt.setString(1, uuid);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				long m = rs.getLong("money");
				rs.close();
				return m;
			}
			rs.close();
		} catch (Exception e) {e.printStackTrace();};
		
		return (Long) null;
	}
	
	public static Player getPlayer(String player) {
		for (Player ps : Bukkit.getOnlinePlayers()){
			if (ps.getName().equalsIgnoreCase(player)) {
				return ps;
			}
		}
		
		return null;
	}

	public static OfflinePlayer getOfflinePlayer(String player) {
		for (OfflinePlayer ps : Bukkit.getOfflinePlayers()){
			if (ps.getName().equalsIgnoreCase(player)) {
				return ps;
			}
		}
		
		return null;
	}
	
}
