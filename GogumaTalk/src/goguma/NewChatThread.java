package goguma;

import java.awt.Color;

public class NewChatThread extends Thread{
	
	AlarmThread aTread;
	String name;
	
	NewChatThread(String name){
		this.name = name;
	}

	public void run(){
		
		while(true){ // while ������ �ߺ� ���� ����
			if(AlarmThread.Overlay == 0){

				Main.alarm.setText(name +" ���� ���ο� ä�ù��� ��������ϴ�");
				Main.alarm.setForeground(Color.yellow);
			
				aTread = new AlarmThread(); 
				aTread.start();
				break;
			}
		}
	}
	
}
