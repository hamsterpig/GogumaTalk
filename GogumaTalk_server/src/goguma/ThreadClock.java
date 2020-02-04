package goguma;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ThreadClock implements Runnable{
	
	JLabel label1;

	ThreadClock(){

		label1 = new JLabel("Date");
		
		
	}
	public void display(){
		
		Calendar cal=Calendar.getInstance();

        int y=cal.get(Calendar.YEAR);
        int m=cal.get(Calendar.MONTH)+1;
        int d=cal.get(Calendar.DATE);
        int h=cal.get(Calendar.HOUR_OF_DAY);
        int min=cal.get(Calendar.MINUTE);
        int sec=cal.get(Calendar.SECOND);
        int dow = cal.get(Calendar.DAY_OF_WEEK);
       
        String dow2="";
        switch(dow){
        	case 1 : dow2="Sun"; break;
        	case 2 : dow2="Mon"; break;
        	case 3 : dow2="Tue"; break;
        	case 4 : dow2="Wed"; break;
        	case 5 : dow2="Thu"; break;
        	case 6 : dow2="Fri"; break;
        	case 7 : dow2="Sat"; break;
        }
        
        String state;
        if(h>=12){
        	state = "PM";
        }else{
        	state = "AM";
        }
        label1.setText(y+"/"+m+"/"+d+"/("+dow2+")"+"  "+ state+" "+h+": "+min+": "+sec);	
	}
	
	@Override
	public void run() {
		while(true){
			display();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
