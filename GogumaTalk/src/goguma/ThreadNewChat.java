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
		
		while(isStart == false){ // while ������ �ߺ� ���� ����
			isStart = true;
			if(ThreadAlarm.Overlay == 0){

				Main.alarm.setText(name +" ���� ���ο� ä�ù��� ��������ϴ�");
				Main.alarm.setForeground(Color.yellow);
			
				aTread = new ThreadAlarm(); 
				aTread.start();
				break;
			}
		}
		isStart = false;
	}
	
}
