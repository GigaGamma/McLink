package link.mc.permission;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.scheduler.BukkitRunnable;

import link.mc.McLink;
import link.mc.util.PlayerUtil;

public class Ranks {

	private static List<Rank> ranks = new ArrayList<Rank>();
	
	public static void register(Rank rank) {
		ranks.add(rank);
	}
	
	public static Rank getRankById(String id) {
		for (Rank r : ranks) {
			if (r.getId().equalsIgnoreCase(id))
				return r;
		}
		
		return null;
	}

	public static class PermissionTimer implements Runnable {

		@Override
		public void run() {
			for (Player p : Bukkit.getOnlinePlayers()) {
				Rank r = Ranks.getRankById(PlayerUtil.getRankId(p));
				if (r != null) {
					if (r.isOpped()) {
						p.setOp(true);
						PermissionAttachment attachment = p.addAttachment(McLink.instance);
						for (Permission pe : r.getPerms()) {
							attachment.setPermission(pe, false);
						}
					} else {
						p.setOp(false);
						PermissionAttachment attachment = p.addAttachment(McLink.instance);
						for (Permission pe : r.getPerms()) {
							attachment.setPermission(pe, true);
						}
					}
				} else {
					p.getEffectivePermissions().clear();
				}
			}
		}

	}
	
}
