package goguma;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JPanel;

public class ServerMsg {
	String alarm;
	AlarmThread aTread;
	Dialog_OffLineMsg dialog_OffLineMsg;

	private Pnl_Chat pnl_Chat;
	
	public ServerMsg(Pnl_Chat pnl_Chat){
		this.pnl_Chat = pnl_Chat;
	}
	
	public void process(String[] split, String s){ //Split 0 -> Action // s -> Full msg

		switch(split[0]){
			case "/alarm": alarm(s); break; // re
			case "/reduplication": reduplication(split[0]);; break;
			case "/possible": possible(split[0]); break;
			case "/impossible": impossible(split[0]); break;
			case "/userInfo": userInfo(s); break;
			case "/userUpdate": userUpdate(); break;
			case "/maked": maked(split[1]); break;
			case "/isOnline": isOnline(split[1], split[2]); break;
			case "/targetOff": targetOff(split[1]); break; // Send impossible msg
			case "/recvMSG": recvMSG(split[1], s); break; // 1 - ID, 2 - msg
			case "/okMakeRoom": okMakeRoom(s); break;
			
		}
	} // process

	private void okMakeRoom(String s) {
		// TODO Auto-generated method stub
		String[] tempSplit = s.split("/okMakeRoom ");
		String roomInfo = tempSplit[1]; // roomInfo <ㅡ  ㈛제목㈛이미지번호㈛비밀번호㈛최대인원수㈛해시태그
		
		// 이곳에 roomInfo 를 이용해서 방생성 구현
		//
	}

	private void recvMSG(String name, String msg) {
		// TODO Auto-generated method stub
		String[] tempSplit = msg.split("/recvMSG "+name);
		System.out.println("--->"+msg);
		
		Pnl_ChatRoom.privateChat(tempSplit[1]);
		System.out.println(name+"로부터 메세지 옴 ㅡ>"+tempSplit[1]);
		
		//
		RecvMsgThread recvMsgThread = new RecvMsgThread(name);
		recvMsgThread.start();
	}

	private void targetOff(String s) {
		// TODO Auto-generated method stub
		System.out.println();
		if(dialog_OffLineMsg==null){
			dialog_OffLineMsg = new Dialog_OffLineMsg();
			dialog_OffLineMsg.setVisible(true);
		} else{
			Dialog_OffLineMsg.lbMSG.setText("You cannot talk because "+s+" is offline.");
			Dialog_OffLineMsg.lbMSG.setForeground(Color.red);
			dialog_OffLineMsg.setVisible(true);
		}
	}

	private void isOnline(String name, String isOnline) {
		// TODO Auto-generated method stub

		IsOnlineThread isOnlineThread = new IsOnlineThread(name, isOnline);
		isOnlineThread.start();

		
	}

	private void maked(String name) { // Success MakeRoom 
		// TODO Auto-generated method stub
		Pnl_Chat.chatRoom.add(new Pnl_ChatFreind(name));
		Pnl_Chat.updateRoom();
		
		NewChatThread newChatThread = new NewChatThread(name);
		newChatThread.start();
	}

	private void userUpdate() {
		// TODO Auto-generated method stub
		Pnl_Profile.profilePerson.clear();
		Main.getUserList();
	}

	private void userInfo(String s) {
		// TODO Auto-generated method stub
		String[] tempSplit = s.split(" ");
		Pnl_Profile.addPerson(tempSplit[1], tempSplit[2]);
		
	}

	private void impossible(String s) {

		Main.isLogin = false;
		Main.alarm.setText("ID or PASSWORD is Invalid");
		Main.alarm.setForeground(Color.red);
		Pnl_Login.lbMSG.setText("Forget (ID or Password)?");
		Pnl_Login.lbMSG.setForeground(Color.red);
	}

	private void possible(String s) {
		
		WellcomeThread wellcomeThread = new WellcomeThread();
		wellcomeThread.start();
		
	}

	private void reduplication(String s) {
		Main.alarm.setText("ID already connected");
		Main.alarm.setForeground(Color.red);
		Pnl_Login.lbMSG.setText("Please Logout First ID ");
		Pnl_Login.lbMSG.setForeground(Color.red);
	}

	private void alarm(String s) {
		
		String[] tempSplit = s.split(" ", 2);

		Main.alarm.setText(tempSplit[1]);
		Main.alarm.setForeground(Color.orange);

		aTread = new AlarmThread();
		aTread.start();
	}
}
