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
		
		while(isStart == false){ // while ������ �ߺ� ���� ����
			isStart = true;
			if(ThreadAlarm.Overlay == 0){
				if(isOnline.equals("true")){
					Main.alarm.setText("ģ��" + name +" ���� "+"�����ϼ̽��ϴ�.");
					Main.alarm.setForeground(Color.green);
				} else if(isOnline.equals("false")){
					Main.alarm.setText("ģ��" + name +" ���� "+"�����ϼ̽��ϴ�.");
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
