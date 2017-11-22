package link.mc.forward;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.io.input.ReaderInputStream;
import org.bukkit.util.io.BukkitObjectInputStream;

public class ForwardJsListener {
	
	private ServerSocket server;
	
	public ForwardJsListener() {
		
	}
	
	public void start() {
		try {
			this.setServer(new ServerSocket(25));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread() {
			
			@Override
			public void run() {
				Socket conn = null;
				
				try {
					conn = server.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				InputStream in = null;
				DataOutputStream out = null;
				
				try {
					in = conn.getInputStream();
					out = new DataOutputStream(conn.getOutputStream());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					BukkitObjectInputStream b = new BukkitObjectInputStream(in);
					System.out.println(b.readObject());
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				/*clientSentence = in.readLine();
				System.out.println("Received: " + clientSentence);
				capitalizedSentence = clientSentence.toUpperCase() + '\n';
				outToClient.writeBytes(capitalizedSentence);*/
			}
			
		}.start();
	}
	
	public void stop() {
		try {
			getServer().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ServerSocket getServer() {
		return server;
	}

	public void setServer(ServerSocket server) {
		this.server = server;
	}
	
}
