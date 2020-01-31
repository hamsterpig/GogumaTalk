package goguma;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
	
	private ArrayList<String> usersList;
	private ArrayList<String> friendList;
	private HashMap<String, ArrayList<String>> userList;
	private BufferedReader brMember,brFriend;
	private PrintWriter pwMember, pwFriend;
	private SocketManager2 hm;
	private DB(){
		hm = hm.getInstance();
		friendList = new ArrayList<String>(30);
		userList = new HashMap<String, ArrayList<String>>();
		try {
			brMember = new BufferedReader(new FileReader("profile/member.txt"));
			brFriend = new BufferedReader(new FileReader("profile/friends.txt"));
			pwMember = new PrintWriter(new FileWriter(new File("profile/member.txt"),true));
			pwFriend = new PrintWriter(new FileWriter(new File("profile/friends.txt"),true));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DB파일 입/출력 문제");
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
	
	public boolean find(String id){
		synchronized (hm) {
			if(hm.containsKey(id)){
				return true;
			}else{
				return false;
			}			
		}		
	}
	@SuppressWarnings("unchecked")
	public void signUp(String id, String pw, PrintWriter printWriter){
		if(userList.containsKey(id)){
			//아이디가 있으면
			printWriter.println("/reg/fail");
		}else{
			//아이디가 없으면
			printWriter.println("/reg/ok");
			synchronized (userList) {
				userList.put(id,null);
			}
			synchronized (hm) {
				hm.put(id, printWriter);
			}
			pwMember.println(id+"/"+pw);
			pwMember.flush();
			pwFriend.println(id+"/");
			pwFriend.flush();
		}
		printWriter.flush();
		printUserMap();
	}
	public void printUserMap(){
		Iterator<String> mapIter = userList.keySet().iterator();

		while(mapIter.hasNext()){
			String key = mapIter.next();
			ArrayList<String> tmp = new ArrayList<String>();
			//String value = 
			if(userList.get(key) == null){
				
				System.out.println(key+" : ");
			}else{
				tmp = userList.get(key);
				System.out.print(key+" : ");
				for(int i=0;i<tmp.size();i++){
					System.out.print(tmp.get(i)+" ");
				}
				System.out.println();
			}
			
		}
	}
	
	
	
	
}
