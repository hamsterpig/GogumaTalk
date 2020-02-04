package goguma;

import java.awt.Color;

public class IsOnlineThread extends Thread{
	
	AlarmThread aTread;
	String name;
	String isOnline;
	IsOnlineThread(String name, String isOnline){
		this.name = name;
		this.isOnline = isOnline;
	}

	public void run(){
		Pnl_Profile.profilePerson.clear();
		
		while(true){ // while 쓰레드 중복 실행 방지
			if(AlarmThread.Overlay == 0){
				if(isOnline.equals("true")){
					Main.alarm.setText("친구" + name +" 님이 "+"접속하셨습니다.");
					Main.alarm.setForeground(Color.green);
				} else if(isOnline.equals("false")){
					Main.alarm.setText("친구" + name +" 님이 "+"종료하셨습니다.");
					Main.alarm.setForeground(Color.white);
				}
				aTread = new AlarmThread(); 
				aTread.start();
				break;
			}
		}
	}
	
}
