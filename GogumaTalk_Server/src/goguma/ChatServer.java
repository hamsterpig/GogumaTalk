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
	private static SocketManager2 socket;
	private static DB dataBase;
	
	public static void main(String[] args){
		try{
			try{
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			}catch(Exception e){
					System.out.println("ERROR");
			}
			serverUI = new ServerUI();	
			ServerSocket server = new ServerSocket(10001);
		
			System.out.println("접속을 기다립니다.");
			
			socket = SocketManager2.getInstance();
			dataBase = DB.getInstance();
			//wait client
			while(true){
				Socket sock = server.accept();
				ChatThread chatthread = new ChatThread(sock, socket, serverUI);
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
		private String password;
		private String ip;
		private String idstr[];
		private BufferedReader br;
		private PrintWriter pw;
		//private static SocketManager2 socket;
		private HashMap<String,PrintWriter> hm;
		private boolean initFlag = false;
		private  ServerUI serverUI;
		private boolean dupleFlag = false;
		private DB dataBase;
		
		public ChatThread(Socket sock, HashMap<String,PrintWriter> hm, ServerUI serverUI){
			dataBase = DB.getInstance();
			this.serverUI = serverUI;
			this.sock = sock;
			this.hm = hm;
			try{
				pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
				br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				id = br.readLine();
				String str[] = id.split(" ", 3);
				if(str[0].equals("/reg")){
					System.out.println("생성할때 회원가입");
					dataBase.signUp(str[1],str[2],pw);
					
				}else{
					id = str[1];
					password = str[2];
					broadcast(id +"님이 접속하였습니다.");
					sendMassage(id, password);
				}
			}catch(NullPointerException e1){
				System.out.println("이건데..");
				e1.printStackTrace();
				dataBase.printUserMap();
			}
			catch(Exception ex){
				System.out.println("server thread constructor: "+ex);
			}
		}
		
		public void run(){
			try{
				String line = null;
				//point:
				while((line = br.readLine()) != null){
					System.out.println("line : "+line);
					if(line.equals("/quit")){
						break;
					}
					if(line.indexOf("/to") == 0){
						sendmsg(line);
					}
					if(line.indexOf("/log") == 0){
						String str[] = line.split(" ", 3);
						id = str[1];
						password = str[2];
						System.out.println("str[1]: "+str[1]);
						System.out.println("str[2]: "+str[2]);
						sendMassage(id, password);
					}
					if(line.indexOf("/reg") == 0){
						System.out.println("클라이언트가 서버에게 회원가입 요청");
						String str[] = line.split(" ", 3);
						dataBase.signUp(str[1],str[2],pw);
						
					}
					if(line.indexOf("/req/user") == 0){
						System.out.println("친구 보내드림 ㄱㄷ");
						
						System.out.println(id);
					}
				}
			}catch(Exception ex){
				System.out.println("server thread run : "+ex);
			}finally{
				String info = id + " 님이 접속 종료하였습니다.";
				if(dupleFlag == false){
					synchronized (hm) {
					 hm.remove(id);
					}
					serverUI.kickBackUser(id);
					broadcast(info);
				}
				
			}
		}
		
		public void sendMassage(String id, String password){
			String ip = ""+sock.getInetAddress();
			ip = ip.substring(1);
			System.out.println("접속한 사용자의 아이디는 "+id+"입니다.");
			System.out.println("password = "+ password);
			System.out.println("접속자의 ip는 = "+ip);
			System.out.println(pw);
			int confirmationCase = confirmation(id,password);
			if(confirmationCase == 1){
				serverUI.insertUser(id, ip);
				synchronized (hm) {
					hm.put(id, pw);
				}
				pw.println("/possible");
				pw.flush();
			}else if(confirmationCase == 2){
				/*if(dupleFlag == true){
					System.out.println("여긴 안오냐1");
					pw.println("/reduplication");							
					pw.flush();
				}else{
					System.out.println("여긴 안오냐2");
					pw.println("/reduplication");							
					pw.flush();
				}*/
				
				pw.println("/reduplication");							
				pw.flush();
				System.out.println(pw);
				dupleFlag = true;
				System.out.println("이곳!");
			}else{
				pw.println("/impossible");
				pw.flush();
			}
		}
		
		public int confirmation(String id, String pw){
			System.out.println("id : "+id);
			System.out.println("pw : "+pw);
			try {
				BufferedReader buffReader = new BufferedReader(new FileReader("profile/member.txt"));
				
				 String str = null;
				while((str = buffReader.readLine()) != null){
					String tempID = str.substring(0, str.indexOf("/"));
					String tempPW = str.substring(str.indexOf("/")+1,str.length());
					
					if(tempPW.equals(pw) && tempID.equals(id)){
						if(hm.containsKey(id) == false) {
							System.out.println("success");
							System.out.println("");
							dupleFlag = false;
							return 1;
						}
						else {
							System.out.println("already exist ID!");
							dupleFlag = true;
							return 2;
						}
					}
				}
				System.out.println("fail");
				System.out.println("");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e1){
				System.out.println("읽기오류!");
			}
			return 3;
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
	

