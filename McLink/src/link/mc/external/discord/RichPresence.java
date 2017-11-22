package link.mc.external.discord;

import org.json.JSONObject;

import link.mc.McLink;
import net.dv8tion.jda.core.WebSocketCode;
import net.dv8tion.jda.core.entities.impl.JDAImpl;

public class RichPresence {

	public static void setGame() {
		if (McLink.instance.bot != null) {
			JSONObject obj = new JSONObject();
			JSONObject gameObj = new JSONObject();

			gameObj.put("name", "SuperiorNetworks");
			gameObj.put("type", 0);
			gameObj.put("details", "Playing on the lobby");
			gameObj.put("state", "SuperiorNetworks - PLAYING");
			gameObj.put("timestamps", new JSONObject().put("start", 1508373056));

			JSONObject assetsObj = new JSONObject();
			assetsObj.put("large_image", "lobby");
			assetsObj.put("large_text", "In the lobby");
			assetsObj.put("small_image", "lobby");
			gameObj.put("assets", assetsObj);
			gameObj.put("application_id", McLink.instance.bot.bot.getToken());

			obj.put("game", gameObj);
			obj.put("afk", McLink.instance.bot.bot.getPresence().isIdle());
			obj.put("status", McLink.instance.bot.bot.getPresence().getStatus().getKey());
			obj.put("since", System.currentTimeMillis());

			((JDAImpl) McLink.instance.bot.bot).getClient()
					.send(new JSONObject().put("d", obj).put("op", WebSocketCode.PRESENCE).toString());
		}
	}

}
