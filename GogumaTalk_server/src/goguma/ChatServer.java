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
		
			System.out.println("������ ��ٸ��ϴ�.");
			
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
					System.out.println("ȸ�������� ��û�մϴ�. id: "+id+", pw: "+pw);
					dataBase.signUp(str[1],str[2],printWriter);
				}else{
					//broadcast(id +"���� �����Ͽ����ϴ�.");					
					System.out.println("�������� �α����� ��û�մϴ�. id: "+id+" pw: "+pw);
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
					if(line.indexOf("/log") == 0){//�α��� ��û
						String str[] = line.split(" ", 3);
						id = str[1];
						pw = str[2];
						System.out.println("id: "+str[1]+" pw: "+str[2]+"�� �������� �α����� ��û�մϴ�.");
						dupleFlag = dataBase.logIn(str[1],str[2],printWriter,oos,ip);
					}
					if(line.indexOf("/reg") == 0){//ȸ������ ��û
						String str[] = line.split(" ");
						id = str[1];
						pw = str[2];
						System.out.println("ȸ�������� ��û�մϴ�. id: "+id+", pw: "+pw);
						dataBase.signUp(id, pw, printWriter);
					}
					if(line.indexOf("/Logout") == 0){//ȸ������ ��û
						System.out.println("�α׾ƿ��� ��û�մϴ�. id : "+id);
						dataBase.logOut(id);
					}
					if(line.indexOf("/req/user") == 0){//������ ģ����� ��û
						System.out.println("ģ������� ��û�մϴ�. id : "+id);
						dataBase.sendFriendsList(id,printWriter);
					}
					if(line.indexOf("/req/friend") == 0){//ģ�� �߰�
						dataBase.editFriend(id, line, true);
					}
					if(line.indexOf("/del/friend") == 0){//ģ�� ����
						System.out.println("ģ�������� ��û�մϴ�. ��û�� id : "+id);
						dataBase.editFriend(id, line, false);
					}
					if(line.indexOf("/make/privateChat")==0){//1��1 ä�ù� �����
						System.out.println("1��1 ä�ù��� ����� ��û�մϴ�. ��û�� id : "+id);
						dataBase.makePrivteChat(id, line);
					}
					if(line.indexOf("/make/room") == 0){//���� ä�ù� �����
						System.out.println("����ä�ù��� ����� ��û�մϴ�. ��û�� ID : "+id);
						dataBase.makeRoom(line,printWriter);
					}
					if(line.indexOf("/access/room") == 0){//���� ä�ù� ������ �㰡 �޽��ϴ�.
						//System.out.println("����ä�ù濡 ��� ���� ��û�մϴ�. ��û�� ID : "+id);
						dataBase.accessRoom(id, line);
					}
					if(line.indexOf("/out/room") == 0){//���� ä�ù��� �����ϴ�.
						//System.out.println("����ä�ù濡 ��� ���� ��û�մϴ�. ��û�� ID : "+id);
						dataBase.outRoom(id, line);
					}
					if(line.indexOf("/delete/room") == 0){//���� ä�ù� ���� �մϴ�.
						dataBase.deleteRoom(id,line);
					}
					if(line.indexOf("/o/sendMsg") == 0){//���� ä�ù濡�� �޼����� �����ϴ�.
						dataBase.sendMsgOpenChat(id,line);
					}
					if(line.indexOf("/sendMSG") == 0){//�޼��� ������						
						dataBase.sendMSG(id, line);
					}
					if(line.indexOf("/sendEmoticon") == 0){//�̸�Ƽ�� ������
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
					pw.println(id+" ���� ������ �ӼӸ��� �����̽��ϴ�. :"+msg2);
					pw.flush();
				}
				pw = hm.get(id);
				pw.println(id+" �Բ� ������ �ӼӸ��� ���½��ϴ�. :"+msg2);
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
	

