package link.mc.external.discord;

import link.mc.McLink;
import link.mc.command.Commands;

public class BotThread extends Thread {
	
	public boolean enabled = true;
	
	@Override
	public void run() {
		
		
		while (enabled) {
			
		}
		
		System.out.println("Bot Shutdown!BOTBOTBOTWORKSWORKSWORKS");
		McLink.instance.bot.bot.shutdown();
		return;
	}
	
}
