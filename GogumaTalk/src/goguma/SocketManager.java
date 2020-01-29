package goguma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketManager {
	
	private static SocketManager instance;
	static final String IP = "192.168.100.220";
	private Socket sock;
	public PrintWriter toServ;
	public BufferedReader fromServ;
	
	private SocketManager(){
		try {
			sock = new Socket(IP, 10001);
			toServ = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
			fromServ = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static SocketManager getInstance(){
		if(instance == null)
			instance = new SocketManager();
		return instance;
	}
}
