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
		
		while(true){ // while ������ �ߺ� ���� ����
			if(AlarmThread.Overlay == 0){
				if(isOnline.equals("true")){
					Main.alarm.setText("ģ��" + name +" ���� "+"�����ϼ̽��ϴ�.");
					Main.alarm.setForeground(Color.green);
				} else if(isOnline.equals("false")){
					Main.alarm.setText("ģ��" + name +" ���� "+"�����ϼ̽��ϴ�.");
					Main.alarm.setForeground(Color.white);
				}
				aTread = new AlarmThread(); 
				aTread.start();
				break;
			}
		}
	}
	
}
