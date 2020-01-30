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
			case "/reduplication": reduplication(split[0]);; break;
			case "/possible": possible(split[0]);; break;
			case "/impossible": impossible(split[0]);; break;
		}
		
		
/*		if(split[0].equals("/alarm")){
			alarm(split[1]);
		} else if(split[0].equals("/reduplication")){
			reduplication(split[0]);
		} else if(split[0].equals("/possible")){
			possible(split[0]);
		} else if(split[0].equals("/impossible")){
			impossible(split[0]);
			
		}*/
	} // process

	private void impossible(String s) {
		// TODO Auto-generated method stub
		Main.isLogin = false;
		Main.alarm.setText("ID or PASSWORD is Invalid");
		Main.alarm.setForeground(Color.red);
	}

	private void possible(String s) {
		// TODO Auto-generated method stub
		Main.isLogin = true;
		
		Main.alarm.setText(Pnl_Login.tfID.getText()+" ´Ô ¹Ý°©½À´Ï´Ù ^^");
		Main.alarm.setForeground(Color.gray);
		Main.changePnl(Main.pnl_Login, Main.pnl_Profile);
		
		AlarmThread aTread = new AlarmThread();
		aTread.start();
	}

	private void reduplication(String s) {
		// TODO Auto-generated method stub
		Main.alarm.setText("ID already connected");
		Main.alarm.setForeground(Color.red);
		try {
			soket.fromServ.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void alarm(String s) {
		// TODO Auto-generated method stub
		Main.alarm.setText(s);
		Main.alarm.setForeground(Color.orange);
		AlarmThread aTread = new AlarmThread();
		aTread.start();
	}
}
