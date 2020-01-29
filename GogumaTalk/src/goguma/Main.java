package goguma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Main extends JFrame implements ActionListener {

	Pnl_Profile pnl_Profile;
	Pnl_Login pnl_Login;
	Pnl_Chat pnl_Chat;
	Pnl_SideBar pnl_SideBar;
	Pnl_MultiChat pnl_MultiChat;
	Pnl_Setting pnl_Setting;

	JPanel pnlMenubar;
	static JLabel alarm;
	static JPanel pnl;
	
	static Color colorTheme;

	Main() {
		FontManager fontManager = FontManager.getInstance();
		ColorManager colorManager = ColorManager.getInstance();
		
		colorTheme = colorManager.kakao;

		Dimension res = Toolkit.getDefaultToolkit().getScreenSize(); // Frame
		// Default
		// Size
		pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		
		pnl.setPreferredSize(new Dimension(res.width / 3, res.height - 100));
		pnl.setLocation((res.width / 2) - (this.getWidth() / 2),
				(res.height / 2) - (this.getHeight() / 2));

		//add Panel
		pnl_Login = new Pnl_Login();
		pnl_Profile = new Pnl_Profile();
		pnl_Chat = new Pnl_Chat();
		pnl_MultiChat = new Pnl_MultiChat();
		pnl_Setting = new Pnl_Setting();
		
		// ActionListener
		pnl_Login.btnLogin.addActionListener(this);
		pnl_Profile.btnLogout.addActionListener(this);
		pnl_Profile.btnChat.addActionListener(this);
		pnl_Profile.btnMultiChat.addActionListener(this);
		pnl_Profile.btnSetting.addActionListener(this); //
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
		pnlMenubar.setPreferredSize(new Dimension(res.width / 3, 60));
		pnlMenubar.setBackground(Color.black);
		
		alarm = new JLabel("");
		alarm.setForeground(new Color(255,115,0));
		alarm.setPreferredSize(new Dimension(res.width / 3 - 100,50));
		alarm.setFont(fontManager.GodicBOLD25);
		pnlMenubar.add(alarm);

		pnl.add(pnlMenubar, BorderLayout.NORTH);
		pnl.add(pnl_Login, BorderLayout.CENTER);
		this.setSize(res.width / 3, res.height - 100);
		this.add(pnl);
		
		this.setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		RecvThread recv = new RecvThread();
		recv.start();
		
		
		
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		new Main();
	}
	
	private void changePnl(JPanel p1, JPanel p2){
		Main.pnl.remove(p1);
		Main.pnl.add(p2, BorderLayout.CENTER);
		revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == pnl_Login.btnLogin){
			changePnl(pnl_Login, pnl_Profile);
		} else if(e.getSource() == pnl_Profile.btnLogout){
			changePnl(pnl_Profile, pnl_Login);
		} else if(e.getSource() == pnl_Profile.btnChat){
			changePnl(pnl_Profile, pnl_Chat);
		} else if(e.getSource() == pnl_Profile.btnMultiChat){
			changePnl(pnl_Profile, pnl_MultiChat);
		} else if(e.getSource() == pnl_Profile.btnProfile){ //
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

}
