package goguma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pnl_SideBar extends Pnl_Default{
	JPanel pnl, pnlProfileLine, pnlMenu, pnlUnderMenu, pnlList;
	JLabel lbProfile, lbProfileImg;
	JButton btnLogout, btnsearch, btnaddPerson;
	JButton btnProfile, btnMultiChat, btnChat, btnSetting; // underMenu btn
	JLabel lbSpace;
	
	Main main;
	Pnl_Profile pnl_Profile;
	Pnl_Login pnl_Login;
	Pnl_Chat pnl_Chat;
	Pnl_SideBar pnl_SideBar;
	
	Pnl_SideBar(){
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		
		pnlUnderMenu = new JPanel(new GridLayout(1,0));
		pnlUnderMenu.setPreferredSize(new Dimension(res.width/3, 100));
		pnl_South.add(pnlUnderMenu);
		
		btnProfile = new JButton();
		btnProfile.setIcon(Function.lbImageSetSize("src/img/person.png", 60, 60));
		btnProfile.setPreferredSize(new Dimension(60,60));
		
		btnChat = new JButton();
		btnChat.setIcon(Function.lbImageSetSize("src/img/chatting.png", 60, 60));
		btnChat.setPreferredSize(new Dimension(60,60));
		
		btnSetting = new JButton();
		btnSetting.setIcon(Function.lbImageSetSize("src/img/setting.png", 60, 60));
		btnSetting.setPreferredSize(new Dimension(60,60));
		
		btnMultiChat = new JButton();
		btnMultiChat.setIcon(Function.lbImageSetSize("src/img/multiChat.png", 60, 60));
		btnMultiChat.setPreferredSize(new Dimension(60,60));
		
		pnlUnderMenu.setBackground(new Color(255,180,180));
		pnlUnderMenu.add(btnProfile);
		pnlUnderMenu.add(btnChat);
		pnlUnderMenu.add(btnMultiChat);
		pnlUnderMenu.add(btnSetting);
		
		
		
	}
	
}
