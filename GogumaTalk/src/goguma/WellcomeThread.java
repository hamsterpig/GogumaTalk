package goguma;

import java.awt.Color;

public class WellcomeThread extends Thread{
	
	AlarmThread aTread;

	public void run(){
		
		while(true){ // while 쓰레드 중복 실행 방지
			if(AlarmThread.Overlay == 0){

				Main.isLogin = true;
				Main.alarm.setText(Pnl_Login.tfID.getText()+" 님 반갑습니다 ^^");
				Main.alarm.setForeground(Color.gray);
				Main.changePnl(Main.pnl_Login, Main.pnl_Profile);
				Main.pnl_Profile.lbProfile.setText(Pnl_Login.tfID.getText());

				aTread = new AlarmThread();
				aTread.start();
				break;
			}
		}
		
	}
	
}
