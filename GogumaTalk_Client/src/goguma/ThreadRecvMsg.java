package goguma;

import java.awt.Color;

public class ThreadRecvMsg extends Thread{
	
	ThreadAlarm aTread;
	String name = "";
	static boolean isStart = false;
	
	ThreadRecvMsg(String name){
		this.name = name;
		Main.pnl_Profile.btnChat.setIcon(Function.lbImageSetSize("img/newChat.png", 60, 60)); // img chage
		Main.pnl_MultiChat.btnChat.setIcon(Function.lbImageSetSize("img/newChat.png", 60, 60)); // img chage
		Main.pnl_Setting.btnChat.setIcon(Function.lbImageSetSize("img/newChat.png", 60, 60)); // img chage
		
		Dialog_ViewAnn.addMsg(name+"���� ���ο� �޼����� ���½��ϴ�.", Color.yellow);
	}
	
	public void run(){
		while(isStart == false){ // while ������ �ߺ� ���� ����
			isStart = true;
			if(ThreadAlarm.Overlay == 0 && ThreadAlarm.isStart == false){
				
				Main.alarm.setText(name+"���� ���ο� �޼����� ���½��ϴ�.");
				Main.alarm.setForeground(Color.yellow);

				aTread = new ThreadAlarm();
				aTread.start();
				break;
			}
		}
		isStart = false;
		
	}

	
}
