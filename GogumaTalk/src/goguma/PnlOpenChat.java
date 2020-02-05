package goguma;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class PnlOpenChat extends JPanel implements ActionListener,Runnable{
	
	private Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
	JPanel pnlBase,pnlUsers,pnlChatting;
	JPanel pnlUser,pnlUserChat,pnlUserImage,pnlUserName;
	JPanel pnlChatWindow,pnlButtons,pnlChatInput;
	JTextField tfInputChat;
	JButton btnEmoticon,btnKick,btnInvite,btnMandate,btnDeleteRoom,btnDelete,btnExit;
	JComboBox cbRange;
	JTextArea taChatWindow;
	
	Thread t1; //말풍선 사라지는 스레드
	int kickNum=0; // 퇴장당한 유저 넘버
	int chatNum =0; // 채팅친 유저의 배열 넘버
	ArrayList<PnlOpenChatUser> user; //유저 패널 arrayList
	int pnlX=150,pnlY=150 ,btnX=85,btnY=50,height = 710;
	int userCur=0; //방의 유저 수
	int userMax=6; //최대 유저수
	PnlMultiChat mtc;
	int userNum; //유저의 array.get(넘버)
	String master="",myId="";
	boolean state = true;
	
	static int staticNum = 0;
	int myNum = 0;
	PnlOpenChat(PnlMultiChat multichat,int arrayMax,int arraynum,String userId){ 
		
		myNum = staticNum;
		staticNum++;
		///////////////////////////유저 캐릭터 화면/////////////////////////////////
		
		mtc = multichat;
		userMax = arrayMax;
		userNum = arraynum;
		myId=userId;
		master=userId;
		
		user = new ArrayList<PnlOpenChatUser>();
		pnlUsers = new JPanel();
		pnlUsers.setLayout(new GridLayout(2,0));
		pnlUsers.setBackground(Color.WHITE);
		pnlUsers.setPreferredSize(new Dimension(res.width/3, height/2+50));

		////////////////////////////아래 채팅창///////////////////////////////
		
		
		taChatWindow = new JTextArea(10,45);
		pnlChatWindow = new JPanel();
		pnlChatWindow.add(taChatWindow);
		
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
		mtc.chatroom2.add(this);
		//this.setVisible(state);
		
		//addUser(myId,"image");
	}
	
	void addUser(String name,String image){//유저 추가
		if(userCur>=(userMax)){
		
		}else{
		
		image="img/img1.png"; //임시
		userCur = user.size();
		user.add(new PnlOpenChatUser(this,name,image));
		user.get(userCur).lblUserChat.setBounds(1100,1100,0,0);
		pnlUsers.add(user.get(userCur));
		pnlUsers.revalidate();
		pnlUsers.repaint();
		userCur++;
		mtc.chatroom.get(userNum).lblNumber.setText(userCur+" / "+userMax+"명");
		}
	}
	void setChat(String name,String msg){ //유저가 채팅침
		
		int tmp =-1;
		for(int i=0;i<user.size();i++){
			if((user.get(i).lblUserName.getText()).equals(name)){
				tmp=i;
			}
		}
		user.get(tmp).lblUserChat.setBounds(0, 0 ,176,34);
		user.get(tmp).lblUserChat2.setForeground(Color.black);
		user.get(tmp).lblUserChat2.setText(msg);
		chatNum=tmp;
		t1 = new Thread(this);
		t1.start();
	}
	void kickUser(String name){ //유저 강퇴
		
		for(int i=0;i<user.size();i++){
			if((user.get(i).lblUserName.getText()).equals(name)){
				kickNum=i;
			}
		}
			System.out.println("배열 사이즈 : "+user.size()+" kickNum값 : "+kickNum);
			pnlUsers.remove(user.get(kickNum));
		user.remove(kickNum);
			
			pnlUsers.revalidate();
			pnlUsers.repaint();
			userCur--;
	
	}
	void deleteThisRoom(){ //현재의 방 삭제
		
		mtc.pnlCenter.remove(mtc.chatroom.get(userNum));
		mtc.remove(mtc.chatroom.get(userNum));
		mtc.remove(mtc.chatroom2.get(userNum));
		this.setVisible(false);
		this.pnlBase.removeAll();
		mtc.pnl_c_c.add(mtc.pnlNorth);
		mtc.pnl_c_c.add(mtc.scrCenter);
		mtc.revalidate();
		mtc.repaint(); 
		
	}
	void masterBtn(Boolean check){
		btnKick.setVisible(check);
		btnInvite.setVisible(check);
		btnMandate.setVisible(check);
		btnDeleteRoom.setVisible(check);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnInvite){
			System.out.println("Invite");
			addUser(tfInputChat.getText(),"");
			
		}else if(e.getSource()==btnKick){
			
			System.out.println("KickUser");
			kickUser(tfInputChat.getText());
			
		}else if(e.getSource()==btnDelete){ 
		
		//	tfInputChat.setText(""); //채팅친거 삭제
			
			////
			
			String[] str = new String[3];
			str[0] = "/userIn";
			str[1] = "가현";
			str[2] = "image";
			
			processIn(str);

		}else if(e.getSource() == btnEmoticon){
			setChat("가현",tfInputChat.getText()); //myname			
			
		}else if(e.getSource() == btnExit){

			//Pnl_MultiChat.pnl_c_c.add(chatroom2.get(chatroom2.size()-1));
			
			
			//this.setVisible(false);
			
			Main.pnl_MultiChat.pnl_c_c.removeAll();
			//this.setVisible(false);

			mtc.pnl_c_c.add(mtc.pnlNorth);
			mtc.pnl_c_c.add(mtc.scrCenter);
			mtc.revalidate();
			mtc.repaint();
			
		}else if(e.getSource()==btnMandate){
			
			String userId = tfInputChat.getText();
			master = userId;
			System.out.println(master);
			
			if(!myId.equals(master)){
				masterBtn(false);
				
			}else{
				masterBtn(true);
			}

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
			processDeleteRoom();
		}
		
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
		String image = str[2];
		
		addUser(str[1],str[2]);
	}
	public void processMsg(String[] str){ //유저 메시지
		
		String id = str[1];
		String msg = str[2];
		
		setChat(str[1],str[2]);
	}
	
	// /o/quit tom (오픈채팅 tom이 나갔다)
	public void processOut(String[] str){ //유저 나감
		
		String id = str[1];
		
		kickUser(str[1]);
	}
	public void processDeleteRoom(){ //방 삭제
		
		deleteThisRoom();
	}
}
