package goguma;

import java.awt.Color;

public class RecvMsgThread extends Thread{
	
	AlarmThread aTread;
	String name = "";
	
	RecvMsgThread(String name){
		this.name = name;
	}
	
	public void run(){
		
		while(true){ // while ������ �ߺ� ���� ����
			if(AlarmThread.Overlay == 0 && AlarmThread.isStart == false){

				Main.alarm.setText(name+"���� ���ο� �޼����� ���½��ϴ�.");
				Main.alarm.setForeground(Color.yellow);

				aTread = new AlarmThread();
				aTread.start();
				break;
			}
		}
		
	}

	
}
