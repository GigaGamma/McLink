package link.mc.js;

import java.io.File;
import java.nio.file.Paths;

import com.jplug.js.JSPlug;
import com.jplug.js.lib.Library;
import com.jplug.js.lib.Loader;

import link.mc.McLink;

public class McJs implements Library {

	public static final String VERSION = "0.1-beta";

	@Override
	public String getName() {
		return "McLink";
	}

	@Override
	public String getNamespace() {
		return "mclink";
	}

	@Override
	public Object getPackage() {
		return this;
	}

	@Override
	public String getVersion() {
		return McJs.VERSION;
	}

	public static void init() {
		JSPlug.registerLibs();
		Loader.registerLibrary(new McJs());
		Loader.registerLibrary(new McJsUtils());
		
		File dir = Paths.get(McLink.instance.getDataFolder().getParentFile().getAbsolutePath(), "mcjs").toFile();
		dir.mkdir();
		
		for (File f : dir.listFiles()) {
			if (f.getName().endsWith(".js"))
				JSPlug.loadFile(f);
		}
	}
	
}
