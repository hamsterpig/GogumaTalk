package goguma;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class DB {
	private static DB instance;
	private ServerUI serverUI;
	private ArrayList<String> usersList;
	private ArrayList<String> friendList;
	private HashMap<String, ArrayList<String>> userList;
	private HashMap<String, ArrayList<String>> privateChatList;
	private BufferedReader brMember,brFriend;
	private PrintWriter pwMember, pwFriend;
	private Hashmap hm;
	private HashMap openRoom;
	private HashMap objectHM;
	private DB(){
		
		hm = hm.getInstance();
		serverUI = serverUI.getInstance();
		friendList = new ArrayList<String>(30);
		userList = new HashMap<String, ArrayList<String>>();
		privateChatList = new HashMap<String, ArrayList<String>>();
		//openRoom = new HashMap<String, ArrayList>();
		openRoom = OpenRoom.getInstance();
		objectHM = new HashMap<String, ObjectOutputStream>();
		try {
			brMember = new BufferedReader(new FileReader("profile/member.txt"));
			brFriend = new BufferedReader(new FileReader("profile/friends.txt"));
			pwMember = new PrintWriter(new FileWriter(new File("profile/member.txt"),true));
			pwFriend = new PrintWriter(new FileWriter(new File("profile/friends.txt"),true));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e1){
			
		}
		getAllUser();
		//printUserMap();
	}
	public static DB getInstance(){
		if(instance == null)
			instance = new DB();
		return instance;
	}
	
	public void getAllUser(){
		try {
			String str = null;
			while((str = brFriend.readLine())!=null){
				ArrayList<String> tempArr = new ArrayList<String>();
				String strArr[] = str.split("/");
				for(int i=1;i<strArr.length;i++){
					
					tempArr.add(strArr[i]);
				}
				userList.put(strArr[0],tempArr);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void signUp(String id, String pw, PrintWriter printWriter){
		if(userList.containsKey(id)){
			//���̵� ������
			printWriter.println("/reg/fail");
			System.out.println("�̹� �ִ� ���̵��Դϴ�.");
		}else{
			//���̵� ������
			printWriter.println("/reg/ok");
			synchronized (userList) {
				userList.put(id,null);
			}
			pwMember.println(id+"/"+pw);
			pwMember.flush();
			pwFriend.println(id);
			pwFriend.flush();
			System.out.println("ȸ�������� �Ǿ����ϴ�.");
			printWriter.flush();
			printUserMap();
		}
	}
	
	public boolean logIn(String id, String pw, PrintWriter printWriter, ObjectOutputStream oos, String ip) {
				String str = null;
				try {
					brMember = new BufferedReader(new FileReader("profile/member.txt"));
					while((str = brMember.readLine()) != null) {
						String tempID = str.substring(0, str.indexOf("/"));
						String tempPW = str.substring(str.indexOf("/")+1,str.length());
						if(tempID.equals(id) && tempPW.equals(pw)){
								if(!hm.containsKey(id)){
									System.out.println(hm.get(id));
									synchronized (hm) {
										hm.put(id, printWriter);
									}
									synchronized (objectHM) {
										objectHM.put(id, oos);
									}
									serverUI.insertUser(id, ip);
									printWriter.println("/possible");
									printWriter.flush();
									System.out.println("�α��� ����!");
									
									synchronized (userList) {
										if(userList.containsKey(id) && (userList.get(id) != null)) {
											ArrayList<String> tempArr = userList.get(id);
											PrintWriter tempPrintWriter;
											for(int i=0; i<tempArr.size();i++) {
												if(hm.containsKey(tempArr.get(i))) {
													tempPrintWriter = (PrintWriter) hm.get(tempArr.get(i));
													//tempPrintWriter.println("/userInfo "+id+" true");
													//tempPrintWriter.flush();
													tempPrintWriter.println("/userUpdate ");
													tempPrintWriter.flush();
													tempPrintWriter.println("/isOnline "+id+" true");
													tempPrintWriter.flush();
												}
											}
											
										}
									}
									return false;
								}else{
									printWriter.println("/reduplication");
									printWriter.flush();
									System.out.println("�̹� �α��� �Ǿ��ִ� ���̵��Դϴ�.");								
									return true;
								}
						}
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				printWriter.println("/impossible");
				printWriter.flush();
				System.out.println("���� ���̵� �Դϴ�. �ٽ� �õ����ּ���");
				return false;
	}
	
	public void sendFriendsList(String id, PrintWriter printWriter) {
		if(userList.containsKey(id) && (userList.get(id) != null && !userList.get(id).isEmpty())){
			ArrayList<String> tempArr = userList.get(id);
				String isExist = null;
				for(int i=0;i<tempArr.size();i++){
					synchronized (hm) {
						if(hm.containsKey(tempArr.get(i))) {
							isExist = "true";
						}else {
							isExist = "false";
						}
					}
					printWriter.println("/userInfo "+tempArr.get(i)+" "+isExist);
					printWriter.flush();
				}
			
		}else{
			printWriter.println("/outSider");
			printWriter.flush();
		}
	
	}	
	
	public void editFriend(String user, String line, boolean editState){
		String str[] = line.split(" ");
		String targetID = str[1];
		System.out.print("ģ�� ������ �մϴ�. ��û�� ID : "+user+" ����� : "+targetID);
		if(editState == true){
			System.out.println(" �߰�");
		}else{
			System.out.println(" ����");
		}
		synchronized (userList) {
			if(userList.containsKey(user) && userList.containsKey(targetID)){
				ArrayList<String> tempArr; 
				if(editState == true) { //���°� �߰�
					if(userList.get(user) != null){ 
						tempArr = userList.get(user);		
						if(!(tempArr.contains(targetID))){
							if(!user.equals(targetID)){
								tempArr.add(targetID);
							}
						}
					}else{
						tempArr = new ArrayList<String>();		
						tempArr.add(targetID);
					}
					
					if(userList.get(targetID) != null){
						tempArr = userList.get(targetID);
						if(!tempArr.contains(user)){
							if(!targetID.equals(user)){
								tempArr.add(user);
							}
						}
					}else{
						tempArr = new ArrayList<String>();
						tempArr.add(user);
					}			
					updateFriends();
					sendMassage(user, targetID,true);
					//sendUpdateMessage(user, targetID, editState);
					//sendUpdateMessage(targetID, editState);
				}else {//���°� ����
					System.out.println("�����մϴ�~");
					tempArr = userList.get(targetID);
					tempArr.remove(user);
					tempArr = userList.get(user);
					tempArr.remove(targetID);
					updateFriends();
					sendMassage(user, targetID, false);
				}
				
			};
		}
	}

	private void sendMassage(String user, String targetID, boolean state) {
		System.out.println(""+state);
		synchronized (hm) {
			PrintWriter printWriter;
			if(state == true){//�߰�
				if(hm.containsKey(user)){				
					printWriter = (PrintWriter) hm.get(user);
					if(hm.containsKey(targetID)){
						printWriter.println("/userInfo "+targetID+" true");
					}else{
						printWriter.println("/userInfo "+targetID+" false");
					}				
					printWriter.flush();
				}
				if(hm.containsKey(targetID)){
					printWriter = (PrintWriter) hm.get(targetID);
					if(hm.containsKey(user)){
						printWriter.println("/userInfo "+user+" true");
					}else{
						printWriter.println("/userInfo "+user+" false");
					}
					printWriter.flush();		
				}
			}else{//����
				if(hm.containsKey(user)){				
					printWriter = (PrintWriter) hm.get(user);
					if(hm.containsKey(targetID)){
						printWriter.println("/userUpdate");
						printWriter.flush();
					}else{
						printWriter.println("/userUpdate");
						printWriter.flush();
					}
				}
				if(hm.containsKey(targetID)){
					printWriter = (PrintWriter) hm.get(targetID);
					if(hm.containsKey(user)){
						printWriter.println("/userUpdate");
						printWriter.flush();
					}else{
						printWriter.println("/userUpdate");
						printWriter.flush();
					}
				}
			}	
		}
	}
	public void updateFriends(){
			try {
				pwFriend = new PrintWriter(new FileWriter(new File("profile/friends.txt")));
				synchronized (userList) {
					Iterator<String> mapIter = userList.keySet().iterator();
					ArrayList<String> tempArr;
					while(mapIter.hasNext()){
						String key = mapIter.next();
												 
						if(userList.get(key) == null){
							pwFriend.println(key);							
						}else{							
							tempArr = userList.get(key);
							pwFriend.print(key);
							for(int i=0;i<tempArr.size();i++){
								pwFriend.print("/"+tempArr.get(i));
							}
							pwFriend.println();
						}
						
					}
				}
				pwFriend.flush();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	public void sendUpdateMessage(String userA, String userB, boolean editSate){
	//public void sendUpdateMessage(String userA, boolean editSate){
		synchronized (hm) {
			if(hm.containsKey(userA)){
				PrintWriter printWriter = (PrintWriter) hm.get(userA);
				shoot(userA, printWriter, editSate);
			}
			if(hm.containsKey(userB)){
				PrintWriter printWriter = (PrintWriter) hm.get(userB);
				shoot(userB, printWriter, editSate);
			}
		}
	}
	
	public void shoot(String user, PrintWriter printWriter, boolean editState){
		if(userList.get(user) == null){
			synchronized (userList) {
				ArrayList<String> tempArr = new ArrayList<String>();
				boolean isExist = false;
				for(int i=0;i< tempArr.size(); i++){	
					synchronized (hm) {
						if(hm.containsKey(tempArr.get(i))){
							isExist = true;
						}
					}
					if(editState == true) {
						printWriter.println("/userInfo "+tempArr.get(i)+" "+isExist);
					}else {
						printWriter.println("/userUpdate");
		
					}
					printWriter.flush();
					isExist = false;
				}
			}
		}else{
			synchronized (userList) {
				ArrayList<String> tempArr = userList.get(user);
				boolean isExist = false;
				//for(int i=0;i< tempArr.size(); i++){
				int temp = tempArr.size()-1;
					synchronized (hm) {
						if(hm.containsKey(tempArr.get(temp))){
							isExist = true;
						}
				//	}
					if(editState == true) {
						printWriter.println("/userInfo "+tempArr.get(temp)+" "+isExist);
					}else {
						printWriter.println("/userUpdate");
					}
					printWriter.flush();
					isExist = false;
				}
			}
		}
	}
	
	public void makePrivteChat(String id, String line){
		String str[] = line.split(" ");
		String targetID = str[1];
		System.out.println("1��1 ä��â�� ����ϴ�. ��û�� id : "+id+" ����� ID : "+targetID);
		synchronized (hm) {
			if(hm.containsKey(id) && hm.containsKey(targetID)){
				PrintWriter printWriter = (PrintWriter) hm.get(id);
				printWriter.println("/maked "+targetID);
				printWriter.flush();
				printWriter = (PrintWriter)hm.get(targetID);
				printWriter.println("/maked "+id);
				printWriter.flush();
				//privateChat();
			}else{
				synchronized (hm) {
					PrintWriter printWriter = (PrintWriter) hm.get(id);
					printWriter.println("/targetOff "+targetID);
					printWriter.flush();
				}
			}
		}				
	}
	
	public void sendMSG(String id, String line){
		String str[] = line.split(" ",3);
		String targetID = str[1];
		String MSG = str[2];
		System.out.println("�޼����� �����ϴ�. ��û�� ID : "+id+"����� ID : "+targetID);
		synchronized (hm) {
			if(hm.containsKey(id) && hm.containsKey(targetID)){
				PrintWriter printWriter = (PrintWriter) hm.get(targetID);
				printWriter.println("/recvMSG "+id+" "+MSG);
				printWriter.flush();
			}
		}
		
	}
	public void logOut(String id){
		serverUI.kickBackUser(id,false);
	}
	public void userClose(String id, String ip, BufferedReader buffReader, PrintWriter printWriter, boolean dupleFlag) {		
		System.out.println("�����մϴ�. ID : "+id+" ip : "+ip);
		synchronized (userList) {
			if(userList.containsKey(id) && (userList.get(id) != null)) {
				ArrayList<String> tempArr = userList.get(id);
				PrintWriter tempPrintWriter;
				for(int i=0; i<tempArr.size();i++) {
					if(hm.containsKey(tempArr.get(i))) {
						tempPrintWriter = (PrintWriter) hm.get(tempArr.get(i));						
						tempPrintWriter.println("/isOnline "+id+" false");
						tempPrintWriter.flush();
						tempPrintWriter.println("/userUpdate ");
						tempPrintWriter.flush();
					}
				}
				
			}
		}
		synchronized (hm) {
			if(hm.containsKey(id)) {
				if(serverUI.existIP(ip)) {
					if(dupleFlag == false) {
						System.out.println(id+"���� ���� �Ǿ����ϴ�.");
						serverUI.kickBackUser(id,false);
						hm.remove(id);
					}
				}
			}
		}
		synchronized (objectHM) {
			if(objectHM.containsKey(id)){
				objectHM.remove(id);
			}
		}
		printWriter.println("/quit");
		printWriter.flush();
		try {
			buffReader.close();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void makeRoom(String line, PrintWriter printWriter) {
		//System.out.println(line);
		String str[] = line.split("��");
		//������ ��й�ȣ �ο���
		System.out.println("���� ��������ϴ�. ���� : "+str[1]+" �̹��� ��ȣ : "+str[2]+" ��й�ȣ : "+
		str[3]+" �ִ� �ο��� : "+str[4]+	" �ؽ��±� : "+str[5]);
		String title = str[1];
		String imgNum = str[2];
		String pw = str[3];
		String max = str[4];
		String hashtag = str[5];
		synchronized (openRoom) {
			if(openRoom.containsKey(title)){
				printWriter.println("/noMakeRoom");
				printWriter.flush();
			}else{
				ArrayList roomArr = new ArrayList();
				roomArr.add(title);
				roomArr.add(imgNum);
				roomArr.add(pw);
				roomArr.add(max);
				roomArr.add(hashtag);
				ArrayList<String> list = new ArrayList<String>();
				roomArr.add(list);
				openRoom.put(title, roomArr);	
				String tempStr = "/okMakeRoom ��"+str[1]+"��"+str[2]+"��"+str[3]+"��"+str[4]+"��"+str[5];
				//printWriter.println(tempStr);
				//printWriter.flush();	
				serverUI.insertOpenChat(title, pw, "0/"+max, true);
				SprayChatRoom(title,tempStr);
			}		
		}		
	}
	public void SprayChatRoom(String title, String tempStr){
		synchronized (openRoom) {
							
				synchronized (hm) {
					Iterator<String> mapIter = hm.keySet().iterator();
					PrintWriter printWriter = null;
					while(mapIter.hasNext()){
						String key = mapIter.next();													 
						if(hm.get(key) != null){											
							printWriter = (PrintWriter) hm.get(key);
							printWriter.println(tempStr);
							printWriter.flush();
							printWriter.println("/updateOpenChat");
							printWriter.flush();
						}							
					}
				}
			
				
			
		}
		
	}
	public void accessRoom(String id, String line){
		String str[] = line.split(" ",3);
		String title = str[1];
		String pw = str[2];
		System.out.println("����ä�ù濡 ��� ���� ��û�մϴ�. ��û�� ID : "+id+" ��� �� �̸�  : "+title);
		synchronized (openRoom) {
			if(openRoom.containsKey(title)){
				ArrayList tempArr = (ArrayList) openRoom.get(title);
				String maximum = (String)tempArr.get(3);
				int tempInt = Integer.parseInt((String)tempArr.get(3));
				ArrayList<String> tempList = (ArrayList<String>) tempArr.get(5);				
				if((tempInt>tempList.size()) && !(tempList.contains(id))){
					System.out.println("���� �ȵ��µ�");
					synchronized (hm) {
						PrintWriter printWriter = (PrintWriter) hm.get(id);
						printWriter.println("/accessSuccess");
						printWriter.flush();
					}
					tempList.add(id);
				}else{					
					System.out.println("���䰡");
					synchronized (hm) {
						PrintWriter printWriter = (PrintWriter) hm.get(id);
						printWriter.println("/accessFail");
						printWriter.flush();
					}
					
				}
				maximum = (tempList.size())+"/"+maximum;
				System.out.println("accessRoom_maximum : "+maximum);
				serverUI.openChatTableUpdate(title, maximum);
			}
		}
	}
	public void outRoom(String id, String line){
		String str[] = line.split(" ",2);
		String title = str[1];
		synchronized (openRoom) {
			if(openRoom.containsKey(title)){
				ArrayList tempArr = (ArrayList) openRoom.get(title);
				String maximum = (String)tempArr.get(3);
				ArrayList<String> tempList = (ArrayList<String>) tempArr.get(5);	
				tempList.remove(id);
				maximum = (tempList.size())+"/"+maximum;
				System.out.println("outRoom_maximum : "+maximum);
				serverUI.openChatTableUpdate(title, maximum);
			}
		}
	}
	
	public void deleteRoom(String id, String line){
		String str[] = line.split(" ",2);
		String title = str[1];
		System.out.println("���� ä�ù��� �����մϴ�. ��û�� ID : "+id+" ��� �� �̸� : "+title);
		synchronized (openRoom) {
			openRoom.remove(title);
		}
		serverUI.deleteOpenChat(title);
	}
	
	public void sendMsgOpenChat(String id, String line){
		String str[] = line.split("��");
		String title = str[1];
		String Msg = str[2];
		System.out.println("����ä�ù濡 �޼����� �����ϴ�. ��û�� ID : "+id+"��� �� �̸� : "+title+"�޼��� : "+Msg);
		synchronized (openRoom) {
			if(openRoom.containsKey(title)){
				ArrayList tempArr = (ArrayList) openRoom.get(title);
				ArrayList<String> tempList = (ArrayList<String>) tempArr.get(5);
				synchronized (hm) {
					for(int i=0;i<tempList.size();i++){
						if(hm.containsKey(tempList.get(i))){
							PrintWriter printWriter =  (PrintWriter) hm.get(tempList.get(i));
							printWriter.println("/o/from "+id+" "+Msg);
							printWriter.flush();
						}
					}
				}
			}
		}
	}
	public void printUserMap(){
		getAllUser();
		Iterator<String> mapIter = userList.keySet().iterator();

		while(mapIter.hasNext()){
			String key = mapIter.next();
			ArrayList<String> tempArr = new ArrayList<String>();
			//String value = 
			if(userList.get(key) == null){
				
				System.out.println(key+" : ");
			}else{
				tempArr = userList.get(key);
				System.out.print(key+" : ");
				for(int i=0;i<tempArr.size();i++){
					System.out.print(tempArr.get(i)+" ");
				}
				System.out.println();
			}
			
		}
	}
	public void sendEmoticon(String id, String line, ObjectInputStream ois) {
		String str[] = line.split(" ");
		String target = str[1]; 
		System.out.println("�̹����� �����ϴ�. ��û�� ID : "+id+" ����� : "+target);
		DataFormat df;
		try {
			df = (DataFormat) ois.readObject();
			 synchronized (objectHM) {
				 ObjectOutputStream oos = (ObjectOutputStream) objectHM.get(target);
				 synchronized (hm) {
					PrintWriter printWriter = (PrintWriter) hm.get(target);
					printWriter.println("/recvImg");
					printWriter.flush();
				}
				 oos.writeObject(df);
				 oos.flush();
			}
			/*String fileName = df.getFileName();
	        ImageIcon icon = df.getImg();
	        Image img = icon.getImage();
	        BufferedImage bimg = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2 = bimg.createGraphics();
	        g2.drawImage(img, 0, 0, null);
	        g2.dispose();
	        System.out.println("�������� ���ſϷ�"+fileName);
	        synchronized (objectHM) {
				 ObjectOutputStream oos = (ObjectOutputStream) objectHM.get(target);
				 oos.writeObject(bimg);
				 oos.flush();
			}*/
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        
        //ImageIO.write(bimg, "jpg", new File("img2/"+fileName));
        
	}
	
	
	
	
	
	
}
