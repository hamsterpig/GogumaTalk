package goguma;

import java.awt.Color;

public class ThreadWellcome extends Thread{
	
	ThreadAlarm aTread;

	public void run(){
		Dialog_ViewAnn.addMsg(PnlLogin.tfID.getText()+" �� �ݰ����ϴ� ^^", Color.white);
		
		while(true){ // while ������ �ߺ� ���� ����
			if(ThreadAlarm.Overlay == 0){

				Main.isLogin = true;
				Main.alarm.setText(PnlLogin.tfID.getText()+" �� �ݰ����ϴ� ^^");
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
