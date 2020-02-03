package goguma;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class Hashmap extends HashMap{
	private static Hashmap instance;
	private HashMap <String, PrintWriter> hashMap;
	
	private Hashmap(){
		hashMap = new HashMap<String, PrintWriter>();
	}

	public static Hashmap getInstance(){
		if(instance == null)
			instance = new Hashmap();
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