package goguma;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JPanel;

public class ServerMsg {
	SocketManager soket = SocketManager.getInstance();
	String alarm;

	private Pnl_Chat pnl_Chat;
	
	public ServerMsg(Pnl_Chat pnl_Chat){
		this.pnl_Chat = pnl_Chat;
	}
	
	public void process(String[] split){
		
		switch(split[0]){
			case "/alarm": alarm(split[1]); break;
			case "/reduplication": reduplication	(split[0]);; break;
			case "/possible": possible(split[0]);; break;
			case "/impossible": impossible(split[0]);; break;
		}
		
	} // process

	private void impossible(String s) {

		Main.isLogin = false;
		Main.alarm.setText("ID or PASSWORD is Invalid");
		Main.alarm.setForeground(Color.red);
	}

	private void possible(String s) {
		
		Main.isLogin = true;
		
		Main.alarm.setText(Pnl_Login.tfID.getText()+" ´Ô ¹Ý°©½À´Ï´Ù ^^");
		Main.alarm.setForeground(Color.gray);
		Main.changePnl(Main.pnl_Login, Main.pnl_Profile);
		
		AlarmThread aTread = new AlarmThread();
		aTread.start();
	}

	private void reduplication(String s) {

		Main.alarm.setText("ID already connected");
		Main.alarm.setForeground(Color.red);
		try {
			soket.fromServ.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void alarm(String s) {

		Main.alarm.setText(s);
		Main.alarm.setForeground(Color.orange);
		AlarmThread aTread = new AlarmThread();
		aTread.start();
	}
}
