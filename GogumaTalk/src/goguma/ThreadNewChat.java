package goguma;

import java.awt.Color;

public class ThreadNewChat extends Thread{
	
	ThreadAlarm aTread;
	String name;
	static boolean isStart = false;
	
	ThreadNewChat(String name){
		this.name = name;
	}

	public void run(){
		
		while(isStart == false){ // while 쓰레드 중복 실행 방지
			isStart = true;
			if(ThreadAlarm.Overlay == 0){

				Main.alarm.setText(name +" 님이 새로운 채팅방을 만들었습니다");
				Main.alarm.setForeground(Color.yellow);
			
				aTread = new ThreadAlarm(); 
				aTread.start();
				break;
			}
		}
		isStart = false;
	}
	
}
