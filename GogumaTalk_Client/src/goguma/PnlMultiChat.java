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
	JScrollPane scrCenter;
	String userId=Main.pnl_Profile.lbProfile.getText();
	int arrayMax=8; // 방 최대 인원수
	static int exitNum=0;
	PnlOpenChat openChat;

	PnlMultiChat(){

		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
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
		pnlCenter.setLayout(new FlowLayout(FlowLayout.LEADING));
		pnlCenter.setPreferredSize(new Dimension(res.width/3,1200));
		scrCenter = new JScrollPane(pnlCenter,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrCenter.getViewport().setBackground(Color.white);
		scrCenter.setPreferredSize(new Dimension(res.width/3, 650));
		JPanel p = new JPanel(new BorderLayout());
		pnl_c_c.add(pnlNorth,"North");
		pnl_c_c.add(scrCenter,"Center");
		
		
		
	}
	

	
	public void addRoom(String title,String image,String password, String contents, int maxnum,int curnum){
		//방 추가 메소드
		pnlCenter.add(new PnlMultiChatRoom(this,title,image,password,contents,maxnum,curnum));
		arrayMax =maxnum;
		pnlCenter.revalidate();
		pnlCenter.repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnAdd){ //방 추가
			
			Dialog_AddRoom d = new Dialog_AddRoom(this,userId);
			d.setVisible(true);

		}else if(e.getSource()==btnFind){
			/*String[] str = new String[4];
			str[0] = "/roomInfo";
			str[1] = "roomName";
			str[2] = "roomPW";
			str[3] = "4";
			processMsg(str);*/
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		//로비의 해당 방의 panel을 누르면 오픈챗방으로 들어가진다
		
		
		int j=0;
		String pw="";
		
		for(int i=0;i<chatroom.size();i++){
			
			if(e.getSource() == chatroom.get(i)){
				
				j=i;
				System.out.println(chatroom.get(i).lblTitle.getText()+" / "+chatroom.get(i).roomPassword+" / j : "+ j);
			}
		}

			if(chatroom.get(j).passwordCheck.equals("Open")){//비번이 없을 경우//||chatroom.get(j).roomPassword.equals(null)
				Main.soket.toServ.println("/access/room " + chatroom.get(j).roomTitle + " null");
				Main.soket.toServ.flush();
				///
				Main.pnl_MultiChat.pnl_c_c.removeAll();
				System.out.println("multichatroom/curnum : "+chatroom.get(j).curnum+" arraynum = "+chatroom.get(j).arraynum);
				Main.pnl_MultiChat.pnl_c_c.add(openChat = new PnlOpenChat(this,arrayMax,j,userId,chatroom.get(j).arruser));
				Main.pnl_MultiChat.pnl_c_c.revalidate();
				Main.pnl_MultiChat.pnl_c_c.repaint();
				
				
			}
			else{ //비밀번호가 있을경우 비밀번호dialog
				System.out.println(chatroom.get(j).roomPassword);
				pw=chatroom.get(j).roomPassword;
				Dialog_PassWord p = new Dialog_PassWord(this,pw,j,userId);
				p.setVisible(true);
				
			}

		
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
	public void processMsg(String str){//static
		// ㈛제목㈛이미지번호㈛비밀번호㈛최대인원수㈛해시태그

		
		String[] roomInfo=str.split("㈛", 6);
				
		for(int i=0;i<roomInfo.length;i++){
			System.out.println(i+": "+roomInfo[i]);
		}
				
		System.out.println(str);
	

		addRoom(roomInfo[1],roomInfo[2],roomInfo[3], roomInfo[5],Integer.parseInt(roomInfo[4]),0);

	}
	public void processIn(String s){
		
		
	}
}
