package link.mc.external.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ResponseListener implements Runnable {
	
	public MessageReceivedEvent event;
	public boolean used;
	
	@Override
	public void run() {
		
	}
	
	public void dispose() {
		used = true;
	}

}
