package goguma;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.UIManager;

public class ChatServer {
	private static ServerUI serverUI;
	private static Hashmap hm;
	private static DB dataBase;
	
	public static void main(String[] args){
		try{
			try{
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			}catch(Exception e){
					System.out.println("ERROR");
			}
			serverUI = ServerUI.getInstance();	
			ServerSocket server = new ServerSocket(10001);
		
			System.out.println("접속을 기다립니다.");
			
			hm = Hashmap.getInstance();
			dataBase = DB.getInstance();
			//wait client
			while(true){
				Socket sock = server.accept();
				ChatThread chatthread = new ChatThread(sock, hm);
				chatthread.start();
			}
		}
		catch(Exception e){
			System.out.println("server main : "+e);
		}
	}
	
}
	class ChatThread extends Thread{
		private Socket sock;
		private String id;
		private String pw;
		private String ip;
		private String idstr[];
		private BufferedReader br;
		private PrintWriter printWriter;
		private HashMap<String,PrintWriter> hm;
		private boolean initFlag = false;
		private boolean dupleFlag = false;
		private DB dataBase;
		
		public ChatThread(Socket sock, HashMap<String,PrintWriter> hm){
			dataBase = DB.getInstance();
			this.sock = sock;
			this.hm = hm;
			this.ip = ""+sock.getInetAddress();
			this.ip = ip.substring(1);
			try{
				printWriter = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
				br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				id = br.readLine();
				String str[] = id.split(" ", 3);
				id = str[1];
				pw = str[2];
				if(str[0].equals("/reg")){
					System.out.println("회원가입을 요청합니다. id: "+id+", pw: "+pw);
					dataBase.signUp(str[1],str[2],printWriter);
				}else{
					//broadcast(id +"님이 접속하였습니다.");					
					System.out.println("서버에게 로그인을 요청합니다. id: "+id+" pw: "+pw);
					dupleFlag = dataBase.logIn(id,pw,printWriter,ip);
				}
			}catch(NullPointerException e1){
				e1.printStackTrace();
			}
			catch(Exception ex){
				System.out.println("server thread constructor: "+ex);
				//ex.printStackTrace();
			}
		}
		
		public void run(){
			try{
				String line = null;
				while((line = br.readLine()) != null){
					System.out.println("line : "+line);
					if(line.equals("/quit")){
						break;
					}
					if(line.indexOf("/to") == 0){
						sendmsg(line);
					}
					if(line.indexOf("/log") == 0){//로그인 요청
						String str[] = line.split(" ", 3);
						id = str[1];
						pw = str[2];
						System.out.println("id: "+str[1]+" pw: "+str[2]+"로 서버에게 로그인을 요청합니다.");
						dupleFlag = dataBase.logIn(str[1],str[2],printWriter,ip);
					}
					if(line.indexOf("/req/user") == 0){//유저의 친구목록 요청
						System.out.println("친구목록을 요청합니다. id : "+id);
						dataBase.sendFriendsList(id,printWriter);
					}
					if(line.indexOf("/req/friend") == 0){//친구 추가
						dataBase.addFriend(id, line);
					}
					if(line.indexOf("/make/room") == 0){//방 만들기
						dataBase.makeRoom(line);
					}
				}
			}catch(Exception ex){
				System.out.println("server thread run : "+ex);
				ex.printStackTrace();
			}finally{
				dataBase.userClose(id, ip, br, printWriter, dupleFlag);	
			}
		}
		
		
		public void sendmsg(String msg){
			int start = msg.indexOf("")+1;
			int end = msg.indexOf("",start);
			if(end != -1){
				String to = msg.substring(start, end);
				String msg2 = msg.substring(end+1);
				PrintWriter pw = hm.get(to);
				if(pw != null){
					pw.println(id+" 님이 다음의 귓속말을 보내셨습니다. :"+msg2);
					pw.flush();
				}
				pw = hm.get(id);
				pw.println(id+" 님께 다음의 귓속말을 보냈습니다. :"+msg2);
				pw.flush();
			}
		}
		
		public void broadcast(String msg){
			synchronized (hm) {
				Collection<PrintWriter> collection = hm.values();
				Iterator<PrintWriter> iter = collection.iterator();
				while(iter.hasNext()){
					PrintWriter pw = iter.next();
					pw.println(msg);
					pw.flush();
				}
			}
		}
	
		
		
	}
	

