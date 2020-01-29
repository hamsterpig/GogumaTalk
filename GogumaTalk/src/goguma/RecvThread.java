package goguma;

import java.io.IOException;

public class RecvThread extends Thread{
	boolean isOn = true;
	
	SocketManager soket = SocketManager.getInstance();
	ServerMsg serverMsg = ServerMsg.getInstance();
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("dd");
		while(isOn){
			try {
				String temp;
				
				Thread.sleep(1000);
				temp = soket.fromServ.readLine();
				
				serverMsg.process(temp.split(" ", 2));

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("Server Thread Exit");
		
	}

}
