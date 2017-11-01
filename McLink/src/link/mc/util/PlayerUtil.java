package link.mc.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import link.mc.McLink;

public class PlayerUtil {
	
	public static String getPlayerDiscord(String uuid) {
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
