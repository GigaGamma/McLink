package link.mc.external.discord;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Bot {
	
	public JDA bot = null;
	private BotListener listener = new BotListener();
	public boolean shutdown = false;
	
	public Bot() {
		try {
			bot = new JDABuilder(AccountType.BOT)
					.addEventListener(listener)
					.setToken("MzcyOTQ5MzMxNzAxMDA2MzM3.DNLnuA.GdHJgqHD3rcXoWO8TcX0O2JJNo8")
					.setEnableShutdownHook(true)
					.buildBlocking();
			listener.bot = this;
		} catch (LoginException | IllegalArgumentException | InterruptedException | RateLimitedException e) {
			e.printStackTrace();
		}
	}
	
	public void shutdown() {
		shutdown = true;
		bot.shutdown();
	}
	
	public void broadcast(Message message) {
		for (Guild g : bot.getGuilds()) {
			g.getTextChannelsByName("general", false).get(0).sendMessage(message).queue();
		}
	}
	
	public void broadcast(String message) {
		for (Guild g : bot.getGuilds()) {
			g.getTextChannelsByName("general", false).get(0).sendMessage(message).queue();
		}
	}
	
	public void staff(Message message) {
		for (Guild g : bot.getGuilds()) {
			if (!g.getTextChannelsByName("staff-only", false).isEmpty()) {
				g.getTextChannelsByName("staff-only", false).get(0).sendMessage(message).queue();
			}
		}
	}
	
	public void staff(String message) {
		for (Guild g : bot.getGuilds()) {
			if (!g.getTextChannelsByName("staff-only", false).isEmpty()) {
				g.getTextChannelsByName("staff-only", false).get(0).sendMessage(message).queue();
			}
		}
	}
	
	
}
