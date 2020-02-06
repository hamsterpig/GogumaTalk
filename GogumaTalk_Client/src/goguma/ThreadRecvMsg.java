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
		
		Dialog_ViewAnn.addMsg(name+"님이 새로운 메세지를 보냈습니다.", Color.yellow);
	}
	
	public void run(){
		while(isStart == false){ // while 쓰레드 중복 실행 방지
			isStart = true;
			if(ThreadAlarm.Overlay == 0 && ThreadAlarm.isStart == false){
				
				Main.alarm.setText(name+"님이 새로운 메세지를 보냈습니다.");
				Main.alarm.setForeground(Color.yellow);

				aTread = new ThreadAlarm();
				aTread.start();
				break;
			}
		}
		isStart = false;
		
	}

	
}
