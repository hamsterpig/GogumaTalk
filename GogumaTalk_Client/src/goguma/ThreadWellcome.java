package goguma;

import java.awt.Color;

public class ThreadWellcome extends Thread{
	
	ThreadAlarm aTread;

	public void run(){
		Dialog_ViewAnn.addMsg(PnlLogin.tfID.getText()+" 님 반갑습니다 ^^", Color.white);
		
		while(true){ // while 쓰레드 중복 실행 방지
			if(ThreadAlarm.Overlay == 0){

				Main.isLogin = true;
				Main.alarm.setText(PnlLogin.tfID.getText()+" 님 반갑습니다 ^^");
				Main.alarm.setForeground(Color.white);
				Main.changePnl(Main.pnl_Login, Main.pnl_Profile);
				Main.pnl_Profile.lbProfile.setText(PnlLogin.tfID.getText());

				aTread = new ThreadAlarm();
				aTread.start();
				break;
			}
		}
	}
}
