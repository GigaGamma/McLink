package link.mc.js;

import com.jplug.js.lib.Library;

import link.mc.util.ChatUtil;
import link.mc.util.MarkupUtil;
import link.mc.util.ServerUtil;

public class McJsUtils implements Library {

	@Override
	public String getName() {
		return "McLink Utilities";
	}

	@Override
	public String getNamespace() {
		return "mclink-util";
	}

	@Override
	public Object getPackage() {
		return this;
	}

	@Override
	public String getVersion() {
		return McJs.VERSION;
	}
	
	public void broadcast(String message) {
		ServerUtil.broadcast(message);
	}
	
	public String markup(String string) {
		return MarkupUtil.markupToChat(string);
	}
	
}
