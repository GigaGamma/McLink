package link.mc.updater;

public class ReleaseData {

	public String tag_name;
	public Asset[] assets;
	
	class Asset {
		public String url;
		public String name;
		public String browser_download_url;
	}
	
}
