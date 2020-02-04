package goguma;

import java.awt.Color;

public class RecvMsgThread extends Thread{
	
	AlarmThread aTread;
	String name = "";
	
	RecvMsgThread(String name){
		this.name = name;
	}
	
	public void run(){
		
		while(true){ // while 쓰레드 중복 실행 방지
			if(AlarmThread.Overlay == 0 && AlarmThread.isStart == false){

				Main.alarm.setText(name+"님이 새로운 메세지를 보냈습니다.");
				Main.alarm.setForeground(Color.yellow);

				aTread = new AlarmThread();
				aTread.start();
				break;
			}
		}
		
	}

	
}
