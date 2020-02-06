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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Main extends JFrame implements ActionListener, MouseListener {
	static PnlChatRoom pnl_ChatIn;
	static PnlProfile pnl_Profile;
	static PnlLogin pnl_Login;
	static PnlChat pnl_Chat;
	PnlSideBar pnl_SideBar;
	static PnlMultiChat pnl_MultiChat;
	static PnlSetting pnl_Setting;

	static JPanel pnlMenubar;
	static JLabel alarm, alarmSpace;
	static JPanel pnl;
	static Color colorTheme;
	static ThreadRecv recv;
	static boolean isLogin = false;
	
	static ManagerSocket soket;
	
	
	Main() {
		ManagerFont fontManager = ManagerFont.getInstance();
		ManagerColor colorManager = ManagerColor.getInstance();
		
		pnl_ChatIn = new PnlChatRoom();
		
		colorTheme = colorManager.kakao; // setting Theme

		// Default
		// Size
		pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		
		pnl.setPreferredSize(new Dimension(640, 980));
		pnl.setLocation(960 - (this.getWidth() / 2),
				(1080 / 2) - (this.getHeight() / 2));

		//add Panel
		pnl_Login = new PnlLogin();
		pnl_Profile = new PnlProfile();
		pnl_Chat = new PnlChat();
		pnl_MultiChat = new PnlMultiChat();
		pnl_Setting = new PnlSetting();
		
		// ActionListener
		pnl_Login.btnLogin.addActionListener(this);
		pnl_Profile.btnLogout.addActionListener(this);
		pnl_Profile.btnChat.addActionListener(this);
		pnl_Profile.btnMultiChat.addActionListener(this);
		pnl_Profile.btnSetting.addActionListener(this);
		pnl_Chat.btnProfile.addActionListener(this);
		pnl_Chat.btnChat.addActionListener(this);
		pnl_Chat.btnMultiChat.addActionListener(this);
		pnl_Chat.btnSetting.addActionListener(this);
		pnl_MultiChat.btnProfile.addActionListener(this);
		pnl_MultiChat.btnChat.addActionListener(this);
		pnl_MultiChat.btnMultiChat.addActionListener(this);
		pnl_MultiChat.btnSetting.addActionListener(this);
		pnl_Setting.btnProfile.addActionListener(this);
		pnl_Setting.btnChat.addActionListener(this);
		pnl_Setting.btnMultiChat.addActionListener(this);
		pnl_Setting.btnSetting.addActionListener(this);

		pnlMenubar = new JPanel();
		pnlMenubar.setPreferredSize(new Dimension(640, 60));
		pnlMenubar.setBackground(Color.black);
		pnlMenubar.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		alarmSpace = new JLabel();
		alarmSpace.setPreferredSize(new Dimension(20 ,50));
		//alarmSpace.setBackground(Color.red);
	//	alarmSpace.setOpaque(true);
		pnlMenubar.add(alarmSpace);
		
		alarm = new JLabel("");
		alarm.setForeground(new Color(255,115,0));
		alarm.setPreferredSize(new Dimension(540,50));
		alarm.setFont(fontManager.GodicBOLD25);
	//	alarm.setBackground(Color.yellow);
	//	alarm.setOpaque(true);
		pnlMenubar.add(alarm);

		pnl.add(pnlMenubar, BorderLayout.NORTH);
		pnl.add(pnl_Login, BorderLayout.CENTER);
		this.setSize(640, 980);
		this.add(pnl);
		
		this.setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		recv = new ThreadRecv(new ServerMsg(pnl_Chat));
		recv.start();

		pnlMenubar.addMouseListener(this);
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		new Main();
	}
	
	static void changePnl(JPanel p1, JPanel p2){
		soket = ManagerSocket.getInstance();
		PnlProfile.profilePerson.clear();
		PnlProfilePerson.personNum = 0;
		
		if(p2==pnl_Profile && isLogin==true){
			getUserList(); // 리스트 재요청
		}
		
		if(p2==pnl_ChatIn){
			PnlChatRoom.lbFriendName.setText(PnlChat.chatName);
			System.out.println(PnlChat.chatName);
			PnlChatRoom.lbFriendName.revalidate();
			PnlChatRoom.lbFriendName.repaint();
		}
		
		if(p2==pnl_Chat){
			pnl_Profile.btnChat.setIcon(Function.lbImageSetSize("img/chatting.png", 60, 60)); // img chage
			pnl_MultiChat.btnChat.setIcon(Function.lbImageSetSize("img/chatting.png", 60, 60)); // img chage
			pnl_Setting.btnChat.setIcon(Function.lbImageSetSize("img/chatting.png", 60, 60)); // img chage
		}
		
		Main.pnl.remove(p1);
		Main.pnl.add(p2, BorderLayout.CENTER);
		Main.pnl.revalidate();
		Main.pnl.repaint();
	}
	
	static public void getUserList(){
		soket.toServ.println("/req/user");
		soket.toServ.flush();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == pnl_Login.btnLogin && isLogin==true){ // Login
			changePnl(pnl_Login, pnl_Profile);
		} else if(e.getSource() == pnl_Profile.btnLogout){
			changePnl(pnl_Profile, pnl_Login);
			Main.isLogin = false;
			soket.toServ.println("/Logout "+pnl_Profile.lbProfile.getText());
			soket.toServ.flush();
		} else if(e.getSource() == pnl_Profile.btnChat){
			changePnl(pnl_Profile, pnl_Chat);
		} else if(e.getSource() == pnl_Profile.btnMultiChat){
			changePnl(pnl_Profile, pnl_MultiChat);
		} else if(e.getSource() == pnl_Profile.btnSetting){ //
			changePnl(pnl_Profile, pnl_Setting);
		} else if(e.getSource() == pnl_Chat.btnProfile){
			changePnl(pnl_Chat, pnl_Profile);
		} else if(e.getSource() == pnl_Chat.btnChat){
			changePnl(pnl_Chat, pnl_Chat);
		} else if(e.getSource() == pnl_Chat.btnMultiChat){
			changePnl(pnl_Chat, pnl_MultiChat);
		} else if(e.getSource() == pnl_Chat.btnSetting){ //
			changePnl(pnl_Chat, pnl_Setting);
		} else if(e.getSource() == pnl_MultiChat.btnProfile){
			changePnl(pnl_MultiChat, pnl_Profile); 
		} else if(e.getSource() == pnl_MultiChat.btnChat){
			changePnl(pnl_MultiChat, pnl_Chat);
		} else if(e.getSource() == pnl_MultiChat.btnMultiChat){
			changePnl(pnl_MultiChat, pnl_MultiChat);
		} else if(e.getSource() == pnl_MultiChat.btnSetting){ //
			changePnl(pnl_MultiChat, pnl_Setting);
		} else if(e.getSource() == pnl_Setting.btnProfile){
			changePnl(pnl_Setting, pnl_Profile); 
		} else if(e.getSource() == pnl_Setting.btnChat){
			changePnl(pnl_Setting, pnl_Chat);
		} else if(e.getSource() == pnl_Setting.btnMultiChat){
			changePnl(pnl_Setting, pnl_MultiChat);
		} else if(e.getSource() == pnl_Setting.btnSetting){ //
			changePnl(pnl_Setting, pnl_Setting);
		} 
		
	}



	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(pnl_Setting.dialog_ViewAnn==null){
			pnl_Setting.dialog_ViewAnn = new Dialog_ViewAnn();
			pnl_Setting.dialog_ViewAnn.setVisible(true);
		} else{
			pnl_Setting.dialog_ViewAnn.setVisible(true);
		}
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
