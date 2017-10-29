package link.mc.external.discord;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import link.mc.McLink;
import link.mc.util.ImageUtil;
import link.mc.util.PlayerUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public class McLinkBotCommand implements BotCommand {

	@Override
	public String getName() {
		return "mclink";
	}

	@Override
	public String getDescription() {
		return "Executes tasks for the McLink plugin";
	}

	@Override
	public String getUsage() {
		return "!mclink [...]";
	}

	@Override
	public void onCall(User user, MessageChannel channel, String[] args) {
		if (args.length > 0 && args[0].equalsIgnoreCase("info")) {
			String ps = "**Online Players**";
			
			//ResultSet rs = McLink.instance.database.execQuery("SELECT * FROM users");
			for (Player player : Bukkit.getOnlinePlayers()) {
				ps += "\n- " + player.getName();
				String discord = PlayerUtil.getPlayerDiscord(player.getUniqueId().toString());
				if (discord != null) {
					ps += " (" + discord + ")";
				}
			}
			channel.sendMessage(ps).queue();
		}
		if (args.length == 2 && args[0].equalsIgnoreCase("player")) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player.getName().equalsIgnoreCase(args[1])) {
					try {
						channel.sendFile(ImageUtil.extractBytes(ImageIO.read(new URL("https://crafatar.com/renders/body/" + player.getUniqueId() + "?overlay")), "png"), "p.png", new MessageBuilder().append("Player skin").build()).queue();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		//System.out.println("MESSAGE: " + McLink.logg.logs.get(McLink.logg.logs.size() - 1));
	}

}
