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
	
	Thread t1; //��ǳ�� ������� ������
	int kickNum=0; // ������� ���� �ѹ�
	int chatNum =0; // ä��ģ ������ �迭 �ѹ�
	ArrayList<PnlOpenChatUser> user; //���� �г� arrayList
	int pnlX=150,pnlY=150 ,btnX=85,btnY=50,height = 710;
	int userCur=0; //���� ���� ��
	int userMax=6; //�ִ� ������
	PnlMultiChat mtc;
	int userNum; //������ array.get(�ѹ�)
	String master="",myId="";
	boolean state = true;
	
	static int staticNum = 0;
	int myNum = 0;
	PnlOpenChat(PnlMultiChat multichat,int arrayMax,int arraynum,String userId){ 
		
		myNum = staticNum;
		staticNum++;
		///////////////////////////���� ĳ���� ȭ��/////////////////////////////////
		
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

		////////////////////////////�Ʒ� ä��â///////////////////////////////
		
		
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
	
	void addUser(String name,String image){//���� �߰�
		if(userCur>=(userMax)){
		
		}else{
		
		image="img/img1.png"; //�ӽ�
		userCur = user.size();
		user.add(new PnlOpenChatUser(this,name,image));
		user.get(userCur).lblUserChat.setBounds(1100,1100,0,0);
		pnlUsers.add(user.get(userCur));
		pnlUsers.revalidate();
		pnlUsers.repaint();
		userCur++;
		mtc.chatroom.get(userNum).lblNumber.setText(userCur+" / "+userMax+"��");
		}
	}
	void setChat(String name,String msg){ //������ ä��ħ
		
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
	void kickUser(String name){ //���� ����
		
		for(int i=0;i<user.size();i++){
			if((user.get(i).lblUserName.getText()).equals(name)){
				kickNum=i;
			}
		}
			System.out.println("�迭 ������ : "+user.size()+" kickNum�� : "+kickNum);
			pnlUsers.remove(user.get(kickNum));
		user.remove(kickNum);
			
			pnlUsers.revalidate();
			pnlUsers.repaint();
			userCur--;
	
	}
	void deleteThisRoom(){ //������ �� ����
		
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
		
		//	tfInputChat.setText(""); //ä��ģ�� ����
			
			////
			
			String[] str = new String[3];
			str[0] = "/userIn";
			str[1] = "����";
			str[2] = "image";
			
			processIn(str);

		}else if(e.getSource() == btnEmoticon){
			setChat("����",tfInputChat.getText()); //myname			
			
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
			str[1] = "����";
			str[2] = "�ȳ��ϼ���";
			
			processMsg(str);*/
			
		}else if(e.getSource()==btnDeleteRoom){
			/*
			String[] str = new String[2];
			str[0] = "/userOut";
			str[1] = "����";
			
			processOut(str);
			*/
			processDeleteRoom();
		}
		
	}

	@Override
	public void run() {  //��ǳ�� ������� ������
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
	/* /o/from tom hi (����ä�� tom���Լ� hi�� �Դ�)

	  /o/quit tom (����ä�� tom�� ������)
*/
	public void processIn(String[] str){ //��������
		
		String id = str[1];
		String image = str[2];
		
		addUser(str[1],str[2]);
	}
	public void processMsg(String[] str){ //���� �޽���
		
		String id = str[1];
		String msg = str[2];
		
		setChat(str[1],str[2]);
	}
	
	// /o/quit tom (����ä�� tom�� ������)
	public void processOut(String[] str){ //���� ����
		
		String id = str[1];
		
		kickUser(str[1]);
	}
	public void processDeleteRoom(){ //�� ����
		
		deleteThisRoom();
	}
}
