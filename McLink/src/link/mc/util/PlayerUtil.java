package link.mc.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import link.mc.McLink;
import net.dv8tion.jda.core.entities.User;

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
	
	public static String getRankId(String uuid) {
		PlayerUtil.initDataset(uuid);
		
		try {
			//System.out.println(McLink.instance.database.getConnection().isClosed());
			String query = "SELECT * FROM users WHERE uuid = ?";
			PreparedStatement prepStmt = McLink.instance.database.getConnection().prepareStatement(query);
			prepStmt.setString(1, uuid);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				String discord = rs.getString("rank");
				rs.close();
				return discord;
			}
			rs.close();
		} catch (Exception e) {e.printStackTrace();};
		
		return null;
	}
	
	public static void setRankId(String uuid, String rank) {
		PlayerUtil.initDataset(uuid);
		
		try {
			String query = "UPDATE users SET rank = ? WHERE uuid = ?";
			PreparedStatement prepStmt = McLink.instance.database.getConnection().prepareStatement(query);
			prepStmt.setString(1, rank);
			prepStmt.setString(2, uuid);
			prepStmt.executeUpdate();
			prepStmt.close();
			McLink.instance.database.getConnection().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public static boolean initDataset(Player player) {
		return initDataset(player.getUniqueId().toString());
	}
	
	public static String getRankId(Player player) {
		return getRankId(player.getUniqueId().toString());
	}
	
	public static void setRankId(Player player, String rank) {
		setRankId(player.getUniqueId().toString(), rank);
	}
	
	public static String getPlayerDiscord(Player player) {
		return getPlayerDiscord(player.getUniqueId().toString());
	}
	
	public static User getDiscordUserFromId(String id) {
		List<User> u = McLink.instance.bot.bot.getUsersByName(id.split("#")[0], false);
		if (u.size() > 0) {
			for (User us : u) {
				if (us.getDiscriminator().equals(id.split("#")[1])) {
					return us;
				}
			}
		}
		
		return null;
	}
	
	public static String getUuidFromDiscord(String id) {
		try {
			//System.out.println(McLink.instance.database.getConnection().isClosed());
			String query = "SELECT * FROM users WHERE discord = ?";
			PreparedStatement prepStmt = McLink.instance.database.getConnection().prepareStatement(query);
			prepStmt.setString(1, id);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				String discord = rs.getString("uuid");
				rs.close();
				return discord;
			}
			rs.close();
		} catch (Exception e) {e.printStackTrace();};
		
		return null;
	}
	
	public static Player getPlayerFromDiscord(String id) {
		String did = getUuidFromDiscord(id);
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getUniqueId().toString().equalsIgnoreCase(did)) {
				return p;
			}
		}
		
		return null;
	}
	
}
