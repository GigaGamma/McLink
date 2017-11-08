package link.mc.updater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import com.google.gson.Gson;

public class UpdateFetcher {
	
	public static String get(URL url) {
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
	
			// optional default is GET
			con.setRequestMethod("GET");
	
			//add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
	
			int responseCode = con.getResponseCode();
			//System.out.println("\nSending 'GET' request to URL : " + url);
			//System.out.println("Response Code : " + responseCode);
	
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ReleaseData[] fetchAll(String repo) {
		try {
			String response = get(new URL("https://api.github.com/repos/" + repo + "/releases"));
			ReleaseData[] r = new Gson().fromJson(response, ReleaseData[].class);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ReleaseData fetch(String repo) {
		try {
			String response = get(new URL("https://api.github.com/repos/" + repo + "/releases/latest"));
			ReleaseData r = new Gson().fromJson(response, ReleaseData.class);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void download(URL url, File file) {
		try {
			ReadableByteChannel rbc = Channels.newChannel(url.openStream());
			FileOutputStream fos = new FileOutputStream(file);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			rbc.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
