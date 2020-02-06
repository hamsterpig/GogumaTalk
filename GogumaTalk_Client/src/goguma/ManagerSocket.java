package goguma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ManagerSocket {
	
	private static ManagerSocket instance;
	//static final String IP = "192.168.100.220"; // g
	//static final String IP = "192.168.100.211"; // j
	//static final String IP = "192.168.100.45"; // j
	//static final String IP = "192.168.100.170"; // my
	static final String IP = "192.168.200.186"; // home
	
	private Socket sock;
	public PrintWriter toServ;
	public BufferedReader fromServ;
	public ObjectOutputStream oos;
	public ObjectInputStream ois;
	
	private ManagerSocket(){
		try {
			sock = new Socket(IP, 10001);
			toServ = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
			fromServ = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			oos = new ObjectOutputStream(sock.getOutputStream());
			ois = new ObjectInputStream(sock.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static ManagerSocket getInstance(){
		if(instance == null){
			instance = new ManagerSocket();
		}
		
			
		return instance;
	}
}
