package goguma;

import java.awt.Color;

public class WellcomeThread extends Thread{
	
	AlarmThread aTread;

	public void run(){
		Main.isLogin = true;
		Main.alarm.setText(Pnl_Login.tfID.getText()+" �� �ݰ����ϴ� ^^");
		Main.alarm.setForeground(Color.gray);
		Main.changePnl(Main.pnl_Login, Main.pnl_Profile);
		
		aTread = new AlarmThread();
		aTread.start();
	}
	
}
