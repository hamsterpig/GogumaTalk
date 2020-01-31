package goguma;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JPanel;

public class ServerMsg {
	String alarm;
	AlarmThread aTread;

	private Pnl_Chat pnl_Chat;
	
	public ServerMsg(Pnl_Chat pnl_Chat){
		this.pnl_Chat = pnl_Chat;
	}
	
	public void process(String[] split, String s){

		switch(split[0]){
			case "/alarm": alarm(split[1]); break;
			case "/reduplication": reduplication(split[0]);; break;
			case "/possible": possible(split[0]); break;
			case "/impossible": impossible(split[0]); break;
			case "/userInfo": userInfo(s); break;
		}
		
	} // process

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

		Main.alarm.setText(s);
		Main.alarm.setForeground(Color.orange);

		aTread = new AlarmThread();
		aTread.start();
	}
}
