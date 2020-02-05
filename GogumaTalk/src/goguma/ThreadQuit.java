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
		while(true){ // while 쓰레드 중복 실행 방지
			if(ThreadAlarm.Overlay == 0){

				try {
					for(int i=3; i>-1; i--){
						Main.alarm.setText(i +"초 후 시스템이 종료됩니다. ");
						Main.alarm.setForeground(Color.red);
						Thread.sleep(333);
						Main.alarm.setText(i +"초 후 시스템이 종료됩니다.. ");
						Main.alarm.setForeground(Color.red);
						Thread.sleep(333);
						Main.alarm.setText(i +"초 후 시스템이 종료됩니다... ");
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
