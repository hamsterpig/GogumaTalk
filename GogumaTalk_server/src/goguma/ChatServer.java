package goguma;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
				
			ServerSocket server = new ServerSocket(10001);
		
			System.out.println("접속을 기다립니다.");
			
			hm = Hashmap.getInstance();
			dataBase = DB.getInstance();
			serverUI = ServerUI.getInstance();
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
		private BufferedReader br;
		private PrintWriter printWriter;
		private HashMap<String,PrintWriter> hm;
		private boolean dupleFlag = false;
		private DB dataBase;
		private ObjectOutputStream oos;
		private ObjectInputStream ois;
		
		public ChatThread(Socket sock, HashMap<String,PrintWriter> hm){
			dataBase = DB.getInstance();
			this.sock = sock;
			this.hm = hm;
			this.ip = ""+sock.getInetAddress();
			this.ip = ip.substring(1);
			try{
				printWriter = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
				br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				oos = new ObjectOutputStream(sock.getOutputStream());
				ois = new ObjectInputStream(sock.getInputStream());
				
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
					dupleFlag = dataBase.logIn(id,pw,printWriter,oos,ip);
				}
			}catch(NullPointerException e1){
				e1.printStackTrace();
			}
			catch(Exception ex){
				System.out.println("server thread constructor: "+ex);
				ex.printStackTrace();
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
						dupleFlag = dataBase.logIn(str[1],str[2],printWriter,oos,ip);
					}
					if(line.indexOf("/reg") == 0){//회원가입 요청
						String str[] = line.split(" ");
						id = str[1];
						pw = str[2];
						System.out.println("회원가입을 요청합니다. id: "+id+", pw: "+pw);
						dataBase.signUp(id, pw, printWriter);
					}
					if(line.indexOf("/Logout") == 0){//회원가입 요청
						System.out.println("로그아웃을 요청합니다. id : "+id);
						dataBase.logOut(id);
					}
					if(line.indexOf("/req/user") == 0){//유저의 친구목록 요청
						System.out.println("친구목록을 요청합니다. id : "+id);
						dataBase.sendFriendsList(id,printWriter);
					}
					if(line.indexOf("/req/friend") == 0){//친구 추가
						dataBase.editFriend(id, line, true);
					}
					if(line.indexOf("/del/friend") == 0){//친구 삭제
						System.out.println("친구삭제를 요청합니다. 요청자 id : "+id);
						dataBase.editFriend(id, line, false);
					}
					if(line.indexOf("/make/privateChat")==0){//1대1 채팅방 만들기
						System.out.println("1대1 채팅방을 만들길 요청합니다. 요청자 id : "+id);
						dataBase.makePrivteChat(id, line);
					}
					if(line.indexOf("/make/room") == 0){//오픈 채팅방 만들기
						System.out.println("오픈채팅방을 만들길 요청합니다. 요청자 ID : "+id);
						dataBase.makeRoom(line,printWriter);
					}
					if(line.indexOf("/access/room") == 0){//오픈 채팅방 접근을 허가 받습니다.
						//System.out.println("오픈채팅방에 들어 가길 요청합니다. 요청자 ID : "+id);
						dataBase.accessRoom(id, line);
					}
					if(line.indexOf("/out/room") == 0){//오픈 채팅방을 나갑니다.
						//System.out.println("오픈채팅방에 들어 가길 요청합니다. 요청자 ID : "+id);
						dataBase.outRoom(id, line);
					}
					if(line.indexOf("/delete/room") == 0){//오픈 채팅방 삭제 합니다.
						dataBase.deleteRoom(id,line);
					}
					if(line.indexOf("/o/sendMsg") == 0){//오픈 채팅방에서 메세지를 보냅니다.
						dataBase.sendMsgOpenChat(id,line);
					}
					if(line.indexOf("/sendMSG") == 0){//메세지 보내기						
						dataBase.sendMSG(id, line);
					}
					if(line.indexOf("/sendEmoticon") == 0){//이모티콘 보내기
						dataBase.sendEmoticon(id, line, ois);
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
	

