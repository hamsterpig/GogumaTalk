package goguma;

import java.awt.Color;

public class NewChatThread extends Thread{
	
	AlarmThread aTread;
	String name;
	
	NewChatThread(String name){
		this.name = name;
	}

	public void run(){
		
		while(true){ // while 쓰레드 중복 실행 방지
			if(AlarmThread.Overlay == 0){

				Main.alarm.setText(name +" 님이 새로운 채팅방을 만들었습니다");
				Main.alarm.setForeground(Color.yellow);
			
				aTread = new AlarmThread(); 
				aTread.start();
				break;
			}
		}
	}
	
}
