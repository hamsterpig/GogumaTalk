package goguma;

public class ServerMsg {
	private static ServerMsg instance;
	
	String alarm;
	
	ServerMsg(){
		
	}
	
	public void process(String[] split){
		if(split[0].equals("/alarm")){
			System.out.println(split[1]);
			//alarm = alarm.concat(split[1]+"\n"); // alarm setting
			Main.alarm.setText(split[1]);
		}
	}
	
	
	
	public static ServerMsg getInstance(){
		if(instance == null){
			instance = new ServerMsg();
		}
		return instance;
	
	}
}
