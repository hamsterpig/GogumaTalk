package goguma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Pnl_Login extends Pnl_Default implements ActionListener{

	JPanel pnl, pnl_n, pnl_s, pnl_c, pnl_w, pnl_e;
	JPanel pnl_c_n, pnl_c_s, pnl_c_c;
	JPanel pnlSpace, pnlSpace2;

	JPanel pnlIDLine, pnlPASSLine, pnlLoginLine, pnlForgetLine;
	JPanel pnlIDgroup, pnlPASSgroup;

	JLabel lbID, lbPASS, lbImg, lbForget;
	JLabel lbIDSave, lbPASSSave;

	JTextField tfID, tfPASS;

	JCheckBox checkID, checkPASS;

	JButton btnLogin;

	ImageIcon iconLogo;

	Pnl_Login() {

		this.setLayout(new BorderLayout());
		pnl_s = new JPanel();
		pnl_c = new JPanel(new BorderLayout());
		pnl_w = new JPanel();
		pnl_e = new JPanel();
		pnl_c_n = new JPanel();
		pnl_c_s = new JPanel();
		pnl_c_c = new JPanel();

		this.add(pnl_c, BorderLayout.CENTER);

		pnl_c.add(pnl_c_s, BorderLayout.SOUTH);
		pnl_c.add(pnl_c_c, BorderLayout.CENTER);

		Dimension res = Toolkit.getDefaultToolkit().getScreenSize(); // Frame
																		// Default
																		// Size
		this.setSize(res.width / 3, res.height - 100);

		//
		pnlSpace2 = new JPanel();
		pnlSpace2.setPreferredSize(new Dimension(res.width, 50));
		pnl_c_n.add(pnlSpace2);

		iconLogo = new ImageIcon("src/img/logo.png");
		lbImg = new JLabel("Logo Img");
		lbImg.setOpaque(false);
		lbImg.setHorizontalAlignment(lbImg.CENTER);
		lbImg.setPreferredSize(new Dimension(250, 250));
		lbImg.setIcon(Function.imageSetSize(iconLogo, 250, 250));
		pnl_c_n.add(lbImg, BorderLayout.NORTH);
		pnl_c_n.setPreferredSize(new Dimension(res.width / 3, 330));
		pnl_c.add(pnl_c_n, BorderLayout.NORTH);

		pnlSpace = new JPanel();
		pnlSpace.setPreferredSize(new Dimension(res.width / 3, 60));
		pnlSpace.setBackground(ColorManager.violet);
		pnl_c_c.add(pnlSpace, BorderLayout.CENTER);

		pnlIDLine = new JPanel();
		lbIDSave = new JLabel("(save)");
		lbIDSave.setFont(FontManager.CalibriPLAIN15);
		checkID = new JCheckBox();
		lbID = new JLabel("ID");
		lbID.setFont(FontManager.CalibriPLAIN30);
		lbID.setPreferredSize(new Dimension(130, 50));
		tfID = new JTextField();
		tfID.setPreferredSize(new Dimension(310, 50));
		pnlIDgroup = new JPanel();
		pnlIDgroup.setPreferredSize(new Dimension(50, 50));
		pnlIDgroup.add(checkID);
		pnlIDgroup.add(lbIDSave);
		pnlIDLine.add(lbID);
		pnlIDLine.add(tfID);
		pnlIDLine.add(pnlIDgroup);
		pnlIDLine.setOpaque(true);
		pnlIDLine.setPreferredSize(new Dimension((res.width / 3) - 50, 100));
		pnlIDLine.setLayout(new FlowLayout(FlowLayout.LEADING));
		pnl_c_c.add(pnlIDLine, BorderLayout.CENTER);

		pnlPASSLine = new JPanel();
		lbPASSSave = new JLabel("(save)");
		lbPASSSave.setFont(FontManager.CalibriPLAIN15);
		checkPASS = new JCheckBox();
		lbPASS = new JLabel("PW");
		lbPASS.setPreferredSize(new Dimension(130, 50));
		lbPASS.setFont(FontManager.CalibriPLAIN30);
		tfPASS = new JTextField();
		tfPASS.setPreferredSize(new Dimension(310, 50));
		pnlPASSgroup = new JPanel();
		pnlPASSgroup.setPreferredSize(new Dimension(50, 50));
		pnlPASSgroup.add(checkPASS);
		pnlPASSgroup.add(lbPASSSave);
		pnlPASSLine.add(lbPASS);
		pnlPASSLine.add(tfPASS);
		pnlPASSLine.add(pnlPASSgroup);
		pnlPASSLine.setOpaque(true);
		pnlPASSLine.setPreferredSize(new Dimension((res.width / 3) - 50, 100));
		pnlPASSLine.setLayout(new FlowLayout(FlowLayout.LEADING));
		pnl_c_c.add(pnlPASSLine, BorderLayout.CENTER);

		pnlForgetLine = new JPanel();
		lbForget = new JLabel("I'mForget (ID / Password)");
		lbForget.setFont(FontManager.CalibriUnder18);
		lbForget.setForeground(Color.blue);
		pnlForgetLine.add(lbForget);
		pnlForgetLine.setPreferredSize(new Dimension(res.width / 3 - 50, 80));
		pnlForgetLine.setForeground(Color.blue);
		pnl_c_c.add(pnlForgetLine);

		pnlLoginLine = new JPanel();
		btnLogin = new JButton("Login");
		btnLogin.setPreferredSize(new Dimension(350, 80));
		btnLogin.addActionListener(this);
		pnlLoginLine.add(btnLogin);
		pnlLoginLine.setPreferredSize(new Dimension(res.width / 3 - 50, 100));
		pnl_c_c.add(pnlLoginLine, BorderLayout.SOUTH);
		
		setTheme(Main.colorTheme); // Default Thema
	}
	
	private void setTheme(Color c) { // TODO Auto-generated method stub
		this.setBackground(c);
		pnl_s.setBackground(c);
		pnl_c.setBackground(c);
		pnl_w.setBackground(c);
		pnl_e.setBackground(c);
		pnl_c_n.setBackground(c);
		pnl_c_s.setBackground(c);
		pnl_c_c.setBackground(c);
		pnlIDgroup.setBackground(c);
		pnlSpace.setBackground(c);
		pnlIDLine.setBackground(c);
		pnlPASSgroup.setBackground(c);
		pnlPASSLine.setBackground(c);
		pnlForgetLine.setBackground(c);
		btnLogin.setBackground(c);
		pnlLoginLine.setBackground(c);
		pnlSpace2.setBackground(c);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		
	}
}
