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
				serverMsg.process(temp.split(" ", 2));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e){
				try {
					soket.fromServ.close();
					isOn = false;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					isOn = false;
				}
			}
			
		}
		System.out.println("Server Thread Exit");
		
	}

}
