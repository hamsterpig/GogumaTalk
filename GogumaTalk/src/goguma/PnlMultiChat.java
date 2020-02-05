package goguma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PnlMultiChat extends PnlSideBar implements ActionListener,MouseListener{
	JLabel lbTitle;
	JPanel pnlNorth;
	static JPanel pnlCenter;
	JButton btnFind,btnAdd;
	JTextField tfFind;
	ArrayList<PnlMultiChatRoom> chatroom;
	static ArrayList<PnlOpenChat> chatroom2 = new ArrayList<PnlOpenChat>();
	JScrollPane scrCenter;
	String userId="id";
	int arrayMax=8; // 방 최대 인원수
	static int exitNum=0;

	PnlMultiChat(){
		
		btnMultiChat.setBackground(Main.colorTheme);

		lbTitle = new JLabel();
		lbTitle.setText("Multi Chat");
		lbTitle.setFont(fontManager.CalibriBOLD50);
		pnl_North.add(lbTitle);
		
		pnl_c_c.setBackground(Color.white);
		chatroom = new ArrayList<PnlMultiChatRoom>();
		
		
		for(int i=0;i<chatroom.size();i++){
			chatroom.get(i).addMouseListener(this);
		}

		btnAdd = new JButton("Add room");
		btnAdd.addActionListener(this);
		tfFind = new JTextField(39);
		btnFind = new JButton("Find room");
		btnFind.addActionListener(this);
		
		pnlNorth = new JPanel();
		pnlNorth.setBackground(Color.WHITE);	
		pnlNorth.add(tfFind);
		pnlNorth.add(btnFind);
		pnlNorth.add(btnAdd);
		
		pnlCenter = new JPanel();//
		pnlCenter.setLayout(new ModifiedFlowLayout());
		scrCenter = new JScrollPane(pnlCenter);
		scrCenter.getViewport().setBackground(Color.white);
		scrCenter.setPreferredSize(new Dimension(640, 650));
		JPanel p = new JPanel(new BorderLayout());
		pnl_c_c.add(pnlNorth,"North");
		pnl_c_c.add(scrCenter,"Center");
		
		
		
	}
	

	
	public void addRoom(String title,String image,String password, String contents, int maxnum,int curnum){
		//방 추가 메소드
		pnlCenter.add(new PnlMultiChatRoom(this,title,image,password,contents,maxnum,curnum));
		pnlCenter.revalidate();
		pnlCenter.repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnAdd){ //방 추가
			
			Dialog_AddRoom d = new Dialog_AddRoom(this,userId);
			d.setVisible(true);

		}else if(e.getSource()==btnFind){
			String[] str = new String[4];
			str[0] = "/roomInfo";
			str[1] = "roomName";
			str[2] = "roomPW";
			str[3] = "4";
			processMsg(str);
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		//로비의 해당 방의 panel을 누르면 오픈챗방으로 들어가진다
		
		
		int j=0;
		String pw="";
		
		for(int i=0;i<chatroom.size();i++){
			
			if(e.getSource() == chatroom.get(i)){
				System.out.println(chatroom.get(i).lblTitle.getText()+" / "+chatroom.get(i).roomPassword);
				j=i;
			}
		}
		if(chatroom.size()>chatroom2.size()){
			if(chatroom.get(j).passwordCheck.equals("Open")){//chatroom.get(j).roomPassword.equals("")
			Main.pnl_MultiChat.pnl_c_c.removeAll();
			chatroom2.add(new PnlOpenChat(this,arrayMax,j,userId));
			Main.pnl_MultiChat.pnl_c_c.add(chatroom2.get(chatroom2.size()-1));
			Main.pnl_MultiChat.pnl_c_c.revalidate();
			Main.pnl_MultiChat.pnl_c_c.repaint();
			Main.soket.toServ.println("/access/room " + chatroom.get(j).roomTitle + " null");
			Main.soket.toServ.flush();
			}else{ //비밀번호가 있을경우 비밀번호dialog
				System.out.println(chatroom.get(j).roomPassword);
				pw=chatroom.get(j).roomPassword;
				Dialog_PassWord p = new Dialog_PassWord(this,pw,j,userId);
				p.setVisible(true);
				
			}
		}else{
			
			Main.pnl_MultiChat.pnl_c_c.removeAll();
			/*chatroom2.get(j).pnlBase.add(chatroom2.get(j).pnlUsers);
			chatroom2.get(j).pnlBase.add(chatroom2.get(j).pnlChatting);*/
			Main.pnl_MultiChat.pnl_c_c.add(chatroom2.get(j).pnlBase);
			Main.pnl_MultiChat.pnl_c_c.revalidate();
			Main.pnl_MultiChat.pnl_c_c.repaint();
			
			if(chatroom.get(j).roomPassword.equals("")){
			Main.soket.toServ.println("/access/room " + chatroom.get(j).roomTitle + " " + null);
			Main.soket.toServ.flush();
			}
			
		}
		
		System.out.println("chatroom : "+chatroom.size()+" chatroom2 : "+chatroom2.size());
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO 자동 생성된 메소드 스텁
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO 자동 생성된 메소드 스텁
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO 자동 생성된 메소드 스텁
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO 자동 생성된 메소드 스텁
		
	}
	
	// 서버로  부터  메세지가 온다 /roomInfo name pw 3 5 (클라에게 방 정보를 보낸다)
	public void processMsg(String[] str){
		
		String roomName = str[1];
		String roomPW = str[2];
		String maxPerson = str[3];
		
		String image ="image";
		String contents = "contents";
		int curPerson = 1;
		
		/*String roomName = str[1];
		String roomImage = str[2];
		String roomPW = str[3];
		String roomContents = str[4];
		String curPerson = str[5];
		String maxPerson = str[6];*/
		
		for(int i = 0; i < str.length; i++){
			System.out.println(str[i]);
			
		}
		addRoom(str[1],image,str[2],contents,Integer.valueOf(str[3]),curPerson);

	}
}
