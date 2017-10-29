package link.mc.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
	
}
