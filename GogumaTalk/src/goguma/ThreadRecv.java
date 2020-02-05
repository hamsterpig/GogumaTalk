package goguma;

import java.io.IOException;

public class ThreadRecv extends Thread{
	boolean isOn = true;
	
	//SocketManager soket = SocketManager.getInstance();
	ServerMsg serverMsg;
	
	ThreadRecv(ServerMsg serverMsg){
		this.serverMsg = serverMsg;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isOn){
			try {
				String temp;
				temp = Main.soket.fromServ.readLine();
				System.out.println(temp);
				serverMsg.process(temp.split(" "), temp);
			} catch (IOException e) {
				
			} catch (Exception e){
				//soket.fromServ.close();
			}
		}
		
		isOn = true;
		System.out.println("Server Thread Exit...");
		
		
	}

}
