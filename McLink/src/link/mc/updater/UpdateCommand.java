package link.mc.updater;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import link.mc.McLink;
import link.mc.updater.ReleaseData.Asset;

public class UpdateCommand extends Command {
	
	public UpdateCommand() {
		super("update", "Updating stuff", "/update", new ArrayList<String>());
	}
	
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		ReleaseData[] r = UpdateFetcher.fetchAll("GigaGamma/McLink");
		if (r == null) {
			sender.sendMessage("No connection could be established at this time");
			return true;
		} else if (r.length == 0) {
			sender.sendMessage("No updates available at this time");
			return true;
		}
		ReleaseData rr = r[0];
		if (rr.tag_name.replace("v", "").equals(McLink.VERSION)) {
			sender.sendMessage("No updates to install");
			return true;
		}
		for (Asset a : rr.assets) {
			if (a.browser_download_url.endsWith(".jar")) {
				try {
					Paths.get(McLink.instance.getDataFolder().getParentFile().getAbsolutePath(), "update").toFile().mkdir();
					UpdateFetcher.download(new URL(a.browser_download_url), Paths.get(McLink.instance.getDataFolder().getParentFile().getAbsolutePath(), "update", "mclink.jar").toFile());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		sender.sendMessage("McLink updated! Please reload your server");
			
		return true;
	}
	
}
