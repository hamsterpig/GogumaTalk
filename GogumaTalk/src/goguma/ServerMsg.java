package goguma;

import java.awt.Color;

import javax.swing.JPanel;

public class ServerMsg {
	String alarm;

	private Pnl_Chat pnl_Chat;
	
	public ServerMsg(Pnl_Chat pnl_Chat){
		this.pnl_Chat = pnl_Chat;
	}
	
	public void process(String[] split){
		
		if(split.length == 2){
			commandTwo(split);
		} else if (split.length == 3){
			commandThree(split);
		} else{
			if(split[0].equals(("/impossible"))){
				Main.quit();
				System.out.println("else ->" + split[0]);
			}
		}
		
	} // process
	
	private void commandTwo(String[] split){
		if(split[0].equals("/alarm")){
			System.out.println(split[1]);
			Main.alarm.setText(split[1]);
			Main.alarm.setForeground(Color.orange);
		}
		
		
	} // commandTwo
	
	private void commandThree(String[] split){
		
		String[] msg = split[1].split(" ");
		
		if(split[0].equals("/c/from")){
			pnl_Chat.update(msg[0], msg[1]);
		}
	} // commandThree
	

}
