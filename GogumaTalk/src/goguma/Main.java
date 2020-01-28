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

	JPanel pnlMenubar;
	static JPanel pnl;
	
	static Color colorTheme;

	Main() {
		FontManager.getInstance();
		ColorManager.getInstance();
		
		colorTheme = ColorManager.kakao;

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
		// ActionListener
		pnl_Login.btnLogin.addActionListener(this);
		pnl_Profile.btnLogout.addActionListener(this);
		pnl_Profile.btnChat.addActionListener(this);

		pnlMenubar = new JPanel();
		pnlMenubar.setPreferredSize(new Dimension(res.width / 3, 60));
		pnlMenubar.setBackground(Color.black);

		pnl.add(pnlMenubar, BorderLayout.NORTH);
		pnl.add(pnl_Login, BorderLayout.CENTER);
		this.setSize(res.width / 3, res.height - 100);
		this.add(pnl);
		
		this.setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
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
			System.out.println("dd");
		}
	}

}
