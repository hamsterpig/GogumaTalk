package goguma;

import javax.swing.*;

import java.awt.*;

public class PnlOpenChatUser extends JPanel{
	JPanel pnlUserChat,pnlUserImage,pnlUserName,pnlUser;
	JLabel lblUserChat,lblUserImage,lblUserName;
	JLabel lblUserChat2;
	
	String msg="                      "; //,image="img/img1.png"
	int pnlX=180,pnlY=180;
	int x=0,y=0,w=175,h=30;
	PnlOpenChat poc;
	
	PnlOpenChatUser(PnlOpenChat pnlopenchat,String name,String image){
		String chatting="";
		poc = pnlopenchat;
		lblUserChat = new JLabel(new ImageIcon("img/img2.png"));//∏ª«≥º±
		lblUserChat.setBackground(Color.WHITE);
		lblUserChat.setBounds(x,y,w,h);

		lblUserChat2 = new JLabel(name+"¥‘ ¿‘¿Â");
		lblUserChat2.setPreferredSize(new Dimension(130,40));
		lblUserChat2.setBounds(x,y,w,h);
		lblUserChat2.setBackground(Color.WHITE);
		lblUserChat2.setHorizontalAlignment(SwingConstants.CENTER);
		System.out.println("¡§∑ƒ : "+lblUserChat2.getHorizontalAlignment());
		
		lblUserImage = new JLabel(new ImageIcon(image));
		lblUserName = new JLabel(name);
		pnlUserChat = new JPanel();
		pnlUserChat.setLayout(new BorderLayout());
		pnlUserChat.setBackground(Color.WHITE);
		
		pnlUserChat.add(lblUserChat2);
		pnlUserChat.add(lblUserChat);
		pnlUserImage = new JPanel();
		pnlUserImage.setBackground(Color.WHITE);
		pnlUserImage.setPreferredSize(new Dimension(pnlX/4*3+40,pnlY/3*2-20));
		pnlUserImage.add(lblUserImage);
		pnlUserName = new JPanel();
		pnlUserName.setBackground(Color.WHITE);
		pnlUserName.add(lblUserName);
		
		
		pnlUser = new JPanel();
		pnlUser.setPreferredSize(new Dimension(pnlX,pnlY));
		pnlUser.setBackground(Color.WHITE);
		
		pnlUser.add(pnlUserChat);
		pnlUser.add(pnlUserImage);
		pnlUser.add(pnlUserName);
		this.setBackground(Color.WHITE);
		this.add(pnlUser);

		
	}

}
