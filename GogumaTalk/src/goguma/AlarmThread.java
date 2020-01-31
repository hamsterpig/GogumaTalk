package goguma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class AlarmThread extends Thread{
	Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
	boolean stop = false;
	
	AlarmThread(){
		int alarmSpaceSize = 100;
		int alarmSize = 610;
		while(alarmSize > 100){
			Main.alarmSpace.setPreferredSize(new Dimension(640 - alarmSpaceSize, 50));
			Main.alarm.setPreferredSize(new Dimension(640 - alarmSize, 50));
			Main.pnlMenubar.revalidate();
			Main.pnlMenubar.repaint();
			try {
				Thread.sleep(7);
				if(stop == true){
					return;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			alarmSpaceSize = alarmSpaceSize+1;
			alarmSize = alarmSize-1;

		}
		try {
			Thread.sleep(350);
			if(stop == true){
				return;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=150; i>1; i--){
			try {
				Thread.sleep(35);
				if(stop == true){
					return;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Main.alarm.setForeground(new Color(i,i,i));
		}
		
	}
	
	
}
