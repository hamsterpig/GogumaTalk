package goguma;

import java.awt.Color;

public class ThreadQuit extends Thread{
	
	ThreadAlarm aTread;

	public void run(){
		try {
			Thread.sleep(1380);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true){ // while ������ �ߺ� ���� ����
			if(ThreadAlarm.Overlay == 0){

				try {
					for(int i=3; i>-1; i--){
						Main.alarm.setText(i +"�� �� �ý����� ����˴ϴ�. ");
						Main.alarm.setForeground(Color.red);
						Thread.sleep(333);
						Main.alarm.setText(i +"�� �� �ý����� ����˴ϴ�.. ");
						Main.alarm.setForeground(Color.red);
						Thread.sleep(333);
						Main.alarm.setText(i +"�� �� �ý����� ����˴ϴ�... ");
						Main.alarm.setForeground(Color.red);
						Thread.sleep(333);
					}
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					System.exit(-1);
				}
				break;
			}
		}
	}
	
}
