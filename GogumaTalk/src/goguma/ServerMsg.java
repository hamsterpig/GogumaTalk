package goguma;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JPanel;

public class ServerMsg {
	String alarm;
	ThreadAlarm aTread;
	Dialog_OffLineMsg dialog_OffLineMsg;

	private PnlChat pnl_Chat;
	
	public ServerMsg(PnlChat pnl_Chat){
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
			case "/quit": quit(); break;
			case "/outSider": outSider(); break;	
		}
	} // process

	private void outSider() {
		// TODO 자동 생성된 메소드 스텁
		PnlProfile.profilePerson.clear();
		PnlProfile.updatePerson();
	}

	private void quit() {
		// TODO Auto-generated method stub
		ThreadQuit threadQuit = new ThreadQuit();
		threadQuit.start();
	}

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
		
		int temp = 0;
		for(int i=0; i<PnlChat.chatRoom.size();i++){
			if(PnlChat.chatRoom.get(i).lbName.getText().equals(name)){
				temp = i;
				break;
			}
		}
		PnlChat.chatRoom.get(temp).lbLastMsg.setText(tempSplit[1]);
		PnlChat.chatRoom.get(temp).lbTLine.setBackground(new Color(255,255,170));
		PnlChat.chatRoom.get(temp).setBackground(new Color(255,255,170));
		
		PnlChatRoom.privateChat(tempSplit[1]);
		System.out.println(name+"로부터 메세지 옴 ㅡ>"+tempSplit[1]);
		
		//
		ThreadRecvMsg recvMsgThread = new ThreadRecvMsg(name);
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

		ThreadIsOnline isOnlineThread = new ThreadIsOnline(name, isOnline);
		isOnlineThread.start();

		
	}

	private void maked(String name) { // Success MakeRoom 
		// TODO Auto-generated method stub
		PnlChat.chatRoom.add(new PnlChatFreind(name));
		PnlChat.updateRoom();
		
		ThreadNewChat newChatThread = new ThreadNewChat(name);
		newChatThread.start();
	}

	private void userUpdate() {
		// TODO Auto-generated method stub
		PnlProfile.profilePerson.clear();
		Main.getUserList();
	}

	private void userInfo(String s) {
		// TODO Auto-generated method stub
		String[] tempSplit = s.split(" ");
		PnlProfile.addPerson(tempSplit[1], tempSplit[2]);
		
	}

	private void impossible(String s) {

		Main.isLogin = false;
		Main.alarm.setText("ID or PASSWORD is Invalid");
		Main.alarm.setForeground(Color.red);
		PnlLogin.lbMSG.setText("Forget (ID or Password)?");
		PnlLogin.lbMSG.setForeground(Color.red);
	}

	private void possible(String s) {
		
		ThreadWellcome wellcomeThread = new ThreadWellcome();
		wellcomeThread.start();
		
	}

	private void reduplication(String s) {
		Main.alarm.setText("ID already connected");
		Main.alarm.setForeground(Color.red);
		PnlLogin.lbMSG.setText("Please Logout First ID ");
		PnlLogin.lbMSG.setForeground(Color.red);
	}

	private void alarm(String s) {
		
		String[] tempSplit = s.split(" ", 2);

		Main.alarm.setText(tempSplit[1]);
		Main.alarm.setForeground(Color.orange);

		aTread = new ThreadAlarm();
		aTread.start();
	}
}
