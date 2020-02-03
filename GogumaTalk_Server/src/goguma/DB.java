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
	private BufferedReader brMember,brFriend;
	private PrintWriter pwMember, pwFriend;
	private Hashmap hm;
	private HashMap roomHashMap;
	private DB(){
		
		hm = hm.getInstance();
		serverUI = serverUI.getInstance();
		friendList = new ArrayList<String>(30);
		userList = new HashMap<String, ArrayList<String>>();
		roomHashMap = new HashMap<String, String>();
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
	
	public void addFriend(String user, String line){
		String str[] = line.split(" ", 2);
		String addID = str[1];
		synchronized (userList) {
			if(userList.containsKey(addID)){
				ArrayList<String> tempArr = userList.get(user);
				tempArr.add(addID);
				tempArr = userList.get(addID);
				tempArr.add(user);
				updateFriends();
				sendUpdateMessage(user, addID);
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
	public void sendUpdateMessage(String userA, String userB){
		synchronized (hm) {
			if(hm.containsKey(userA)){
				PrintWriter printWriter = (PrintWriter) hm.get(userA);
				shoot(userA, printWriter);
			}
			if(hm.containsKey(userB)){
				PrintWriter printWriter = (PrintWriter) hm.get(userB);
				shoot(userB, printWriter);
			}
		}
	}
	
	public void shoot(String user, PrintWriter printWriter){
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
						printWriter.println("/userInfo "+tempArr.get(i)+" "+isExist);
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
					printWriter.println("/userInfo "+tempArr.get(temp)+" "+isExist);
					printWriter.flush();
					isExist = false;
				}
		}
		}
		
	}
	public void userClose(String id, String ip, BufferedReader buffReader, PrintWriter printWriter, boolean dupleFlag) {		
		System.out.println("userClose ID : "+id+" ip : "+ip);
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
	
	public void makeRoom(String line) {
		System.out.println(line);
		String str[] = line.split(" ", 4);
		System.out.println(str[0]);
		System.out.println(str[1]);
		System.out.println(str[2]);
		System.out.println(str[3]);
		
		
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
