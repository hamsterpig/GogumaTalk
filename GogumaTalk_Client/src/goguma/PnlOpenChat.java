package goguma;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class PnlOpenChat extends JPanel implements ActionListener,Runnable,KeyListener{
	
	private Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
	JPanel pnlBase,pnlUsers,pnlChatting;
	JPanel pnlUser,pnlUserChat,pnlUserImage,pnlUserName;
	JPanel pnlChatWindow,pnlButtons,pnlChatInput;
	JTextField tfInputChat;
	JButton btnEmoticon,btnKick,btnInvite,btnMandate,btnDeleteRoom,btnDelete,btnExit;
	JComboBox cbRange;
	JTextArea taChatWindow;
	JScrollPane scrChatWindow;
	
	Thread t1; //말풍선 사라지는 스레드
	int kickNum=0; // 퇴장당한 유저 넘버
	int chatNum =0; // 채팅친 유저의 배열 넘버
	ArrayList<PnlOpenChatUser> user; //유저 패널 arrayList
	ArrayList<String[]>arruser;
	String chatting="";
	int curnum=0; //방의 유저 수
	int maxnum=6; //최대 유저수
	int pnlX=150,pnlY=150 ,btnX=85,btnY=50,height = 710;
	PnlMultiChat mtc;
	int arraynum; //유저의 array.get(넘버)
	String master="",myId=Main.pnl_Profile.lbProfile.getText(),userId="";
	String users;
	boolean state = true;
	static int staticNum = 0;
	int myNum = 0;
	
	PnlOpenChat(PnlMultiChat multichat,int arrayMax,int arrayNum,String masterId,ArrayList<String[]> arrUser){ 
		
		
		myNum = staticNum;
		staticNum++;
		mtc = multichat;
		maxnum = arrayMax;
		arraynum = arrayNum;
		master=Main.pnl_Profile.lbProfile.getText();
		user = new ArrayList<PnlOpenChatUser>();
		arruser=arrUser;
		curnum=user.size();
		
		
		
		///////////////////////////유저 캐릭터 화면/////////////////////////////////
		
	
		
		pnlUsers = new JPanel();
		pnlUsers.setLayout(new GridLayout(2,0));
		pnlUsers.setBackground(Color.WHITE);
		pnlUsers.setPreferredSize(new Dimension(res.width/3, height/2+50));

		////////////////////////////아래 채팅창///////////////////////////////
		
		
		taChatWindow = new JTextArea(10,45);

		scrChatWindow = new JScrollPane(taChatWindow,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		pnlChatWindow = new JPanel();
		pnlChatWindow.add(scrChatWindow);
		
		btnKick = new JButton("Kick");
		btnKick.addActionListener(this);
		btnKick.setPreferredSize(new Dimension(btnX,btnY));
		btnInvite = new JButton("Invite");
		btnInvite.setPreferredSize(new Dimension(btnX,btnY));
		btnInvite.addActionListener(this);
		btnMandate = new JButton("Mandate");
		btnMandate.setPreferredSize(new Dimension(btnX,btnY));
		btnMandate.addActionListener(this);
		btnDeleteRoom = new JButton("DeleteRoom");
		btnDeleteRoom.setPreferredSize(new Dimension(btnX,btnY));
		btnDeleteRoom.addActionListener(this);
		
		pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridLayout(0,1));
		pnlButtons.add(btnKick);
		pnlButtons.add(btnInvite);
		pnlButtons.add(btnMandate);
		pnlButtons.add(btnDeleteRoom);
		
		cbRange = new JComboBox();
		cbRange.addItem("All");
		cbRange.addItem("Whisper");
		cbRange.addItem("Friend");
		
		tfInputChat = new JTextField(26);
		tfInputChat.addKeyListener(this);
		btnEmoticon = new JButton("Emoticon");
		btnEmoticon.addActionListener(this);
		btnDelete = new JButton("X");
		btnDelete.addActionListener(this);
		btnExit = new JButton("Exit");
		btnExit.setPreferredSize(new Dimension(btnX,btnY-10));
		btnExit.addActionListener(this);
		
		pnlChatInput = new JPanel();
		pnlChatInput.setLayout(new FlowLayout(FlowLayout.LEADING,7,0));
		pnlChatInput.add(cbRange);
		pnlChatInput.add(tfInputChat);
		pnlChatInput.add(btnEmoticon);
		pnlChatInput.add(btnDelete);
		pnlChatInput.add(btnExit);
		
		
		pnlChatting = new JPanel();
		pnlChatting.add(pnlChatWindow);
		pnlChatting.add(pnlButtons);
		pnlChatting.add(pnlChatInput);
		pnlChatting.setPreferredSize(new Dimension(res.width/3, height/2));
		
		pnlBase = new JPanel();
		pnlBase.setPreferredSize(new Dimension(res.width/3,height));
		pnlBase.setBackground(Color.white);
		pnlBase.add(pnlUsers);
		pnlBase.add(pnlChatting);
		add(pnlBase,"Center");
		refresh();
		//addUser(myId,"image");
	}
	
	void addUser(String name,String image){//유저 추가
		if(curnum>=(maxnum)){
		
		}else{
		//image="img/img1.png"; //임시
		curnum=mtc.chatroom.get(arraynum).arruser.size();
		String[] str = new String[2];
		str[0]=name;
		str[1]=image;
		arruser.add(str);
		user.add(new PnlOpenChatUser(this,name,image));
		user.get(curnum).lblUserChat.setBounds(1100,1100,0,0);
		pnlUsers.add(user.get(curnum));
		pnlUsers.revalidate();
		pnlUsers.repaint();
		mtc.chatroom.get(arraynum).lblNumber.setText(curnum+" / "+maxnum+"명");

		}
	}
	void setChat(String name,String msg){ //유저가 채팅침
		
		int tmp =-1;
		System.out.println("유저사이즈: "+ user.size());
		for(int i=0;i<user.size();i++){
			
			if((user.get(i).lblUserName.getText()).equals(name)){
				tmp=i;
			}
		}
		System.out.println("msg : "+msg);
		user.get(tmp).lblUserChat.setBounds(0, 0 ,176,34);
		user.get(tmp).lblUserChat2.setForeground(Color.black);
		user.get(tmp).lblUserChat2.setText(msg);
		
		chatNum=tmp;
		System.out.println("chatNum : "+chatNum);
		chatting = chatting+name+" : "+msg+"\n";
		System.out.println("Chatting : "+chatting);
		taChatWindow.setText(chatting);
		System.out.println("채팅 : "+chatting);
		t1 = new Thread(this);
		t1.start();
		tfInputChat.setText("");
/*		Main.soket.toServ.println("/o/from " +name+" "+msg);
		Main.soket.toServ.flush();*/
	
	}
	void exitUser(String name){
		kickNum=-1;
		for(int i=0;i<user.size();i++){
			if((user.get(i).lblUserName.getText()).equals(name)){
				kickNum=i;
			}
		}
		if(kickNum==-1){
			
		}else{
		pnlUsers.remove(user.get(kickNum)); //패널에서 삭제
		user.remove(kickNum);	//패널 어레이리스트에서 삭제
		arruser.remove(kickNum); //1차원 스트링 어레이리스트에서 삭제
			
		pnlUsers.revalidate();
		pnlUsers.repaint();
		}
	}
	void deleteThisRoom(){ //현재의 방 삭제
		
		mtc.pnlCenter.remove(mtc.chatroom.get(arraynum));
		mtc.remove(mtc.chatroom.get(arraynum));
		this.setVisible(false);
		this.pnlBase.removeAll();
		mtc.pnl_c_c.add(mtc.pnlNorth);
		mtc.pnl_c_c.add(mtc.scrCenter);
		mtc.revalidate();
		mtc.repaint();
		///
		
		
	}
	void masterBtn(Boolean check){
		btnKick.setVisible(check);
		btnInvite.setVisible(check);
		btnMandate.setVisible(check);
		btnDeleteRoom.setVisible(check);
	}
	void refresh(){

		curnum=mtc.chatroom.get(arraynum).arruser.size();
		master=mtc.chatroom.get(arraynum).masterid;
		for(int i=0;i<curnum;i++){
		user.add(new PnlOpenChatUser(this,arruser.get(i)[0],arruser.get(i)[1]));
		user.get(i).lblUserChat.setBounds(1100,1100,0,0);
		pnlUsers.add(user.get(i));
		}
		addUser(myId,"img/img1.png");
		
		int masterNum=0;
		
		for(int i=0;i<user.size();i++){
			if((user.get(i).lblUserName.getText()).equals(master)){
				masterNum=i;
			}		
		}
		user.get(masterNum).lblUserName.setForeground(Color.ORANGE);
		
		authority();
		pnlUsers.revalidate();
		pnlUsers.repaint();

	}
	void authority(){
		System.out.println("myId : "+myId+" masterId : "+master);
		if(!myId.equals(master)){
			masterBtn(false);
		}else if(myId.equals(master)){
			masterBtn(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnInvite){
			
			if(!tfInputChat.getText().equals("")){
			
			addUser(tfInputChat.getText(),"img/img1.png");
			}
		}else if(e.getSource()==btnKick){
			
			System.out.println("KickUser");
			exitUser(tfInputChat.getText());
			
		}else if(e.getSource()==btnDelete){ 
		
			tfInputChat.setText(""); //채팅친거 삭제
			System.out.println("arraymax : "+maxnum+" arraynum : "+arraynum+" masterid : "+master);
			
			/*
			String[] str = new String[3];
			str[0] = "/userIn";
			str[1] = "가현";
			str[2] = "image";
			
			processIn(str);*/

		}else if(e.getSource() == btnEmoticon){
			//setChat(myId,"안녕하세요"); //myname
			
	
			//processMsg(str);
			

			
		}else if(e.getSource() == btnExit){
			
			exitUser(myId);
			mtc.chatroom.get(arraynum).masterid=master;
			mtc.chatroom.get(arraynum).arraynum=arraynum;
			mtc.chatroom.get(arraynum).arruser=arruser;
			mtc.chatroom.get(arraynum).curnum=arruser.size();
			
			Main.pnl_MultiChat.pnl_c_c.removeAll();
			mtc.chatroom.get(arraynum).lblNumber.setText(arruser.size()+" / "+maxnum+"명");
			mtc.chatroom.get(arraynum).lblNumber.revalidate();
			mtc.chatroom.get(arraynum).lblNumber.repaint();
			mtc.pnl_c_c.add(mtc.pnlNorth);
			mtc.pnl_c_c.add(mtc.scrCenter);

			System.out.println(mtc.chatroom.get(arraynum).lblNumber.getText());
			mtc.revalidate();
			mtc.repaint();
			Main.soket.toServ.println("/out/room "+mtc.chatroom.get(arraynum).roomTitle);
			Main.soket.toServ.flush();
			
		}else if(e.getSource()==btnMandate){
			
			String userId = tfInputChat.getText();
			int masterNum=0;
			for(int j=0;j<user.size();j++){
				
				if(userId.equals(user.get(j).lblUserName.getText())){
					master = userId;
					masterNum=j;
					user.get(masterNum).lblUserName.setForeground(Color.ORANGE);
				}else{
					
					user.get(j).lblUserName.setForeground(Color.BLACK);
				}
				user.get(j).lblUserName.revalidate();
				user.get(j).lblUserName.repaint();
			}
			authority();			
			mtc.chatroom.get(arraynum).masterid=master;
			/*String[] str = new String[3];
			str[0] = "/userChat";
			str[1] = "가현";
			str[2] = "안녕하세요";
			
			processMsg(str);*/
		}else if(e.getSource()==btnDeleteRoom){
			/*
			String[] str = new String[2];
			str[0] = "/userOut";
			str[1] = "가현";
			
			processOut(str);
			*/
			Main.soket.toServ.println("/delete/room " + mtc.chatroom.get(arraynum).roomTitle);
			Main.soket.toServ.flush();
			processDeleteRoom();
		}
		
		tfInputChat.setText("");
		
	}

	@Override
	public void run() {  //말풍선 사라지는 스레드
		int x = 100;
		while(x>=0){
		try {
			
			Thread.sleep(100);
			
			x=x-5;
			
			if(x<=35&&x>=0){

				user.get(chatNum).lblUserChat2.setForeground(new Color(0,0,0,x));

			}
				

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
		user.get(chatNum).lblUserChat.setBounds(0,0,0,0);
		
		
	}
	/* /o/from tom hi (오픈채팅 tom에게서 hi가 왔다)

	  /o/quit tom (오픈채팅 tom이 나갔다)
*/
	public void processIn(String[] str){ //유저들어옴
		
		String id = str[1];
		String msg = str[2];
		
		addUser(str[1],str[2]);
	}
	public void processMsg(String str){ //유저 메시지

		System.out.println("유저 메시지 메소드");
		String[] line = str.split(" ",3);
		String id = line[1];
		String msg =line[2];
		
		setChat(line[1],line[2]);
	}
	
	// /o/quit tom (오픈채팅 tom이 나갔다)
	public void processOut(String[] str){ //유저 나감
		
		String id = str[1];
		
		exitUser(str[1]);
	}
	public void processDeleteRoom(){ //방 삭제
		
		deleteThisRoom();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==10){
			
			Main.soket.toServ.println("/o/sendMsg㈛" + mtc.chatroom.get(arraynum).roomTitle+"㈛"+tfInputChat.getText());
			Main.soket.toServ.flush();
			//setChat(myId, tfInputChat.getText());
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
