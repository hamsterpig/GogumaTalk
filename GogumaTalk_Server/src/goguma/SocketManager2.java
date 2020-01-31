package goguma;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class SocketManager2 extends HashMap{
	private static SocketManager2 instance;
	private HashMap <String, PrintWriter> hashMap;
	
	private SocketManager2(){
		hashMap = new HashMap<String, PrintWriter>();
	}

	public static SocketManager2 getInstance(){
		if(instance == null)
			instance = new SocketManager2();
		return instance;
	}

	public PrintWriter getStream(String id){
		return hashMap.get(id);
	}
	
	public void addUser(String id, Socket sock){
		PrintWriter pw;
		try {
			pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
			synchronized(hashMap){
				hashMap.put(id, pw);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}