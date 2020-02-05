package goguma;

import java.awt.Color;

public class ThreadIsOnline extends Thread{
	
	ThreadAlarm aTread;
	String name;
	String isOnline;
	static boolean isStart = false;
	
	ThreadIsOnline(String name, String isOnline){
		this.name = name;
		this.isOnline = isOnline;
	}

	public void run(){
		PnlProfile.profilePerson.clear();
		
		while(isStart == false){ // while 쓰레드 중복 실행 방지
			isStart = true;
			if(ThreadAlarm.Overlay == 0){
				if(isOnline.equals("true")){
					Main.alarm.setText("친구" + name +" 님이 "+"접속하셨습니다.");
					Main.alarm.setForeground(Color.green);
				} else if(isOnline.equals("false")){
					Main.alarm.setText("친구" + name +" 님이 "+"종료하셨습니다.");
					Main.alarm.setForeground(Color.white);
				}
				aTread = new ThreadAlarm(); 
				aTread.start();
				break;
			}
		}
		isStart = false;
	}
	
}
