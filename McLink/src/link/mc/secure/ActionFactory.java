package link.mc.secure;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import link.mc.McLink;
import link.mc.command.McLinkCommand;
import link.mc.util.ImageUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Guild;

public class ActionFactory implements Listener {
	
	/*
	 * TODO Add anti-fly hacking
	 */
	
	public static HashMap<Player, Cheats> rp = new HashMap<Player, Cheats>();
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		/*if (event.getPlayer().isInsideVehicle() || event.getPlayer().isDead() || (event.getFrom().getX() == event.getTo().getX() && event.getFrom().getY() == event.getTo().getY() && event.getFrom().getZ() == event.getTo().getZ())) return;
		
		double vd = event.getTo().getY() - event.getFrom().getY();
		//double yd = Math.abs(event.getFrom().getY() - event.getTo().getY());
		
		boolean up = (vd > -0.9);
		
		
		if (!event.getPlayer().isOnGround() && vd < -0.1 && vd < 0.1)
			event.getPlayer().sendMessage(String.valueOf(vd));*/
		
		/*if (event.getFrom().distance(event.getTo()) > 0.75 && !event.getPlayer().hasPotionEffect(PotionEffectType.SPEED) && !event.getPlayer().isFlying() && !event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
			ActionFactory.log(Cheats.SPEED_HACKING, event.getPlayer());
		}*/
		
		if (McLinkCommand.FREEZED.contains(event.getPlayer())) {
			event.setCancelled(true);
		}
	}
	
	public static void log(String message, OfflinePlayer p, String info) {
		/*for (Guild g : McLink.instance.bot.bot.getGuilds()) {
			if (!g.getTextChannelsByName("staff", false).isEmpty()) {
				try {
					System.out.println("Sending");
					g.getTextChannelsByName("staff", false).get(0).sendFile(ImageUtil.extractBytes(ImageIO.read(new URL("https://crafatar.com/avatars/" + p.getUniqueId() + "?overlay")), "png"), "p.png", (new MessageBuilder().setEmbed(new EmbedBuilder().setColor(new Color(255, 85, 0)).setTitle("AntiCheat Message").setDescription(p.getName() + " has triggered the McLink anticheat for `" + at.name().toLowerCase().replace("_", " ") + "`.").build()).build())).queue();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}*/
		
		McLink.instance.bot.log(new MessageBuilder().setEmbed(new EmbedBuilder().setColor(new Color(255, 85, 0)).setTitle("AntiCheat Message (" + info + ")").setDescription(message).setImage("https://crafatar.com/avatars/" + p.getUniqueId() + "?overlay").build()).build());
	}
	
	public static void log(String message, Player p, String info) {
		/*for (Guild g : McLink.instance.bot.bot.getGuilds()) {
			if (!g.getTextChannelsByName("staff", false).isEmpty()) {
				try {
					System.out.println("Sending");
					g.getTextChannelsByName("staff", false).get(0).sendFile(ImageUtil.extractBytes(ImageIO.read(new URL("https://crafatar.com/avatars/" + p.getUniqueId() + "?overlay")), "png"), "p.png", (new MessageBuilder().setEmbed(new EmbedBuilder().setColor(new Color(255, 85, 0)).setTitle("AntiCheat Message").setDescription(p.getName() + " has triggered the McLink anticheat for `" + at.name().toLowerCase().replace("_", " ") + "`.").build()).build())).queue();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}*/
		
		McLink.instance.bot.log(new MessageBuilder().setEmbed(new EmbedBuilder().setColor(new Color(255, 85, 0)).setTitle("AntiCheat Message (" + info + ")").setDescription(message).setImage("https://crafatar.com/avatars/" + p.getUniqueId() + "?overlay").build()).build());
	}
	
	public static void log(Cheats at, Player p) {
		if (rp.containsKey(p)) return;
		/*for (Guild g : McLink.instance.bot.bot.getGuilds()) {
			if (!g.getTextChannelsByName("staff", false).isEmpty()) {
				try {
					System.out.println("Sending");
					g.getTextChannelsByName("staff", false).get(0).sendFile(ImageUtil.extractBytes(ImageIO.read(new URL("https://crafatar.com/avatars/" + p.getUniqueId() + "?overlay")), "png"), "p.png", (new MessageBuilder().setEmbed(new EmbedBuilder().setColor(new Color(255, 85, 0)).setTitle("AntiCheat Message").setDescription(p.getName() + " has triggered the McLink anticheat for `" + at.name().toLowerCase().replace("_", " ") + "`.").build()).build())).queue();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}*/
		
		rp.put(p, at);
		//McLink.instance.bot.staff(new MessageBuilder().setEmbed(new EmbedBuilder().setColor(new Color(255, 85, 0)).setTitle("AntiCheat Message").setDescription(p.getName() + " has triggered the McLink anticheat for `" + at.name().toLowerCase().replace("_", " ") + "`.").setImage("https://crafatar.com/avatars/" + p.getUniqueId() + "?overlay").build()).build());
		ActionFactory.log(p.getName() + " has triggered the McLink anticheat for `" + at.name().toLowerCase().replace("_", " ") + "`.", p, "HackDetect");
	}
	
}
