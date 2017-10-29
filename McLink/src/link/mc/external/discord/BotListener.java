package link.mc.external.discord;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;

import link.mc.McLink;
import link.mc.command.Commands;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {
	
	public Bot bot;
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (bot.shutdown) {bot.bot.shutdownNow(); System.out.println("Shutdown"); return;}
		//event.getChannel().sendMessage(new MessageBuilder().append("Hi").build()).queue();
		if (event.getAuthor() == McLink.instance.bot.bot.getSelfUser()) return;
		
		for (BotCommand command : Commands.getBotCommands()) {
			if (event.getMessage().getContent().split(" ")[0].equalsIgnoreCase("!" + command.getName())) {
				String[] a = event.getMessage().getContent().substring(1).split(" ");
				command.onCall(event.getAuthor(), event.getChannel(), Arrays.copyOfRange(a, 1, a.length));
			}
		}
	}
	
}
