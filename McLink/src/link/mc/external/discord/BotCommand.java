package link.mc.external.discord;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public interface BotCommand {
	
	public String getName();
	public String getDescription();
	public String getUsage();
	public void onCall(User user, MessageChannel channel, String[] args);
	
}
