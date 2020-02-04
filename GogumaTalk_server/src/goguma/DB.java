package goguma;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
	private DB(){
		
		hm = hm.getInstance();
		serverUI = serverUI.getInstance();
		friendList = new ArrayList<String>(30);
		userList = new HashMap<String, ArrayList<String>>();
		privateChatList = new HashMap<String, ArrayList<String>>();
		openRoom = new HashMap<String, String>();
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
			//아이디가 있으면
			printWriter.println("/reg/fail");
			System.out.println("이미 있는 아이디입니다.");
		}else{
			//아이디가 없으면
			printWriter.println("/reg/ok");
			synchronized (userList) {
				userList.put(id,null);
			}
			pwMember.println(id+"/"+pw);
			pwMember.flush();
			pwFriend.println(id+"/");
			pwFriend.flush();
			System.out.println("회원가입이 되었습니다.");
		}
		printWriter.flush();
		printUserMap();
	}
	
	public boolean logIn(String id, String pw, PrintWriter printWriter, String ip) {
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
									serverUI.insertUser(id, ip);
									printWriter.println("/possible");
									printWriter.flush();
									System.out.println("로그인 성공!");
									
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
									System.out.println("이미 로그인 되어있는 아이디입니다.");								
									return true;
								}
						}
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				printWriter.println("/impossible");
				printWriter.flush();
				System.out.println("없는 아이디 입니다. 다시 시도해주세요");
				return false;
	}
	
	public void sendFriendsList(String id, PrintWriter printWriter) {
		if(userList.containsKey(id) && (userList.get(id) != null)){
			ArrayList<String> tempArr = userList.get(id);
				String isExist = null;
				for(int i=0;i<tempArr.size();i++){
					//System.out.print(tempArr.get(i)+" ");
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
			
		}
	
	}	
	
	public void editFriend(String user, String line, boolean editState){
		String str[] = line.split(" ");
		String targetID = str[1];
		synchronized (userList) {
			if(userList.containsKey(user) && userList.containsKey(targetID)){
				ArrayList<String> tempArr = userList.get(user);
				if(editState == true) {					
					tempArr.add(targetID);
					tempArr = userList.get(targetID);
					tempArr.add(user);
				}else {
					tempArr.remove(targetID);
					tempArr = userList.get(targetID);
					tempArr.remove(user);
				}
				updateFriends();
				sendUpdateMessage(user, targetID, editState);
			};
		}
	}

	public void updateFriends(){
			try {
				pwFriend = new PrintWriter(new FileWriter(new File("profile/friends.txt")));
				synchronized (userList) {
					Iterator<String> mapIter = userList.keySet().iterator();
					while(mapIter.hasNext()){
						String key = mapIter.next();
						ArrayList<String> tempArr = new ArrayList<String>();
						 
						if(userList.get(key) == null){
							pwFriend.println(key+"/");							
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
		System.out.println("1대1 채팅창을 만듭니다. 요청자 id : "+id+" 대상자 ID : "+targetID);
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
		System.out.println("메세지를 보냅니다. 요청자 ID : "+id+"대상자 ID : "+targetID);
		synchronized (hm) {
			if(hm.containsKey(id) && hm.containsKey(targetID)){
				PrintWriter printWriter = (PrintWriter) hm.get(targetID);
				printWriter.println("/recvMSG "+id+" "+MSG);
				printWriter.flush();
			}
		}
		
	}
	public void userClose(String id, String ip, BufferedReader buffReader, PrintWriter printWriter, boolean dupleFlag) {		
		System.out.println("종료합니다. ID : "+id+" ip : "+ip);
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
						System.out.println(id+"님이 종료 되었습니다.");
						serverUI.kickBackUser(id);
						hm.remove(id);
					}
				}
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
		System.out.println(line);
		String str[] = line.split("㈛");
		//방제목 비밀번호 인원수
		System.out.println("방을 만들었습니다. 제목 : "+str[1]+" 이미지 번호 : "+str[2]+" 비밀번호 : "+
		str[3]+" 최대 인원수 : "+str[4]+	" 해시태그 : "+str[5]);
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
				roomArr.add(str[1]);
				roomArr.add(str[2]);
				roomArr.add(str[3]);
				roomArr.add(str[4]);
				roomArr.add(str[5]);
				ArrayList<String> list = new ArrayList<String>();
				roomArr.add(list);
				openRoom.put(title, roomArr);	
				String tempStr = "/okMakeRoom ㈛"+str[1]+"㈛"+str[2]+"㈛"+str[3]+"㈛"+str[4]+"㈛"+str[5];
				printWriter.println(tempStr);
				printWriter.flush();	
				SprayChatRoom(title,tempStr);
			}		
		}		
	}
	public void SprayChatRoom(String title, String tempStr){
		synchronized (openRoom) {
			if(openRoom.containsKey(title)){
				ArrayList tempArr = (ArrayList) openRoom.get(title);
				if(tempArr.get(5) != null){
					ArrayList<String> tempList = (ArrayList<String>) tempArr.get(5);
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
		}
		
	}
	public void accessRoom(String id, String line){
		String str[] = line.split(" ",2);
		String title = str[1];
		System.out.println("오픈채팅방에 들어 가길 요청합니다. 요청자 ID : "+id+" 대상 방 이름  : "+title);
		synchronized (openRoom) {
			if(openRoom.containsKey(title)){
				ArrayList tempArr = (ArrayList) openRoom.get(title);
				ArrayList<String> tempList = (ArrayList<String>) tempArr.get(5);
				int tempInt = Integer.parseInt(tempList.get(3));
				if((tempInt<=tempList.size()) && !(tempList.contains(id))){
					synchronized (hm) {
						PrintWriter printWriter = (PrintWriter) hm.get(id);
						printWriter.println("/accessFail");
						printWriter.flush();
					}
				}else{
					tempList.add(id);
					synchronized (hm) {
						PrintWriter printWriter = (PrintWriter) hm.get(id);
						printWriter.println("/accessSuccess");
						printWriter.flush();
					}
				}
			}
		}
	}
	public void printUserMap(){
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
	
	
	
	
	
	
}
