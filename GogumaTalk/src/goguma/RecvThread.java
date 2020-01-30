package goguma;

import java.io.IOException;

public class RecvThread extends Thread{
	boolean isOn = true;
	
	SocketManager soket = SocketManager.getInstance();
	ServerMsg serverMsg;
	
	RecvThread(ServerMsg serverMsg){
		this.serverMsg = serverMsg;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isOn){
			try {
				String temp;
				temp = soket.fromServ.readLine();
				System.out.println(temp);
				serverMsg.process(temp.split(" "));
			} catch (IOException e) {
				
			} catch (Exception e){
				//soket.fromServ.close();
			}
		}
		
		isOn = true;
		System.out.println("Server Thread Exit");
		
	}

}
