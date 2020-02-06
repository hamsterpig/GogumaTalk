package goguma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.rmi.ConnectException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PnlLogin extends PnlDefault implements ActionListener, KeyListener{

	JPanel pnl, pnl_n, pnl_s, pnl_c, pnl_w, pnl_e;
	JPanel pnl_c_n, pnl_c_s, pnl_c_c;
	JPanel pnlSpace, pnlSpace2;
	JPanel pnlIDLine, pnlPASSLine, pnlLoginLine, pnlForgetLine;
	JPanel pnlIDgroup, pnlPASSgroup;
	JLabel lbID, lbPASS, lbImg;
	static JLabel lbMSG;
	JLabel lbIDSave, lbPASSSave;
	static JTextField tfID;
	static JTextField tfPASS;
	JCheckBox checkID, checkPASS;
	static JButton btnLogin;
	static JButton btnSignUp;
	ImageIcon iconLogo;

	Dialog_SignUp dialog_SignUp;

	PnlLogin() {

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
		this.setSize(640, 1080 - 100);

		//
		pnlSpace2 = new JPanel();
		pnlSpace2.setPreferredSize(new Dimension(res.width, 50));
		pnl_c_n.add(pnlSpace2);

		iconLogo = new ImageIcon("img/logo.png");
		lbImg = new JLabel("Logo Img");
		lbImg.setOpaque(false);
		lbImg.setHorizontalAlignment(lbImg.CENTER);
		lbImg.setPreferredSize(new Dimension(250, 250));
		lbImg.setIcon(Function.imageSetSize(iconLogo, 250, 250));
		pnl_c_n.add(lbImg, BorderLayout.NORTH);
		pnl_c_n.setPreferredSize(new Dimension(640, 330));
		pnl_c.add(pnl_c_n, BorderLayout.NORTH);

		pnlSpace = new JPanel();
		pnlSpace.setPreferredSize(new Dimension(640, 60));
		pnlSpace.setBackground(colorManager.violet);
		pnl_c_c.add(pnlSpace, BorderLayout.CENTER);

		pnlIDLine = new JPanel();
		lbIDSave = new JLabel("(save)");
		lbIDSave.setFont(fontManager.CalibriPLAIN15);
		checkID = new JCheckBox();
		lbID = new JLabel("ID");
		lbID.setFont(fontManager.CalibriPLAIN30);
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
		pnlIDLine.setPreferredSize(new Dimension((640) - 50, 100));
		pnlIDLine.setLayout(new FlowLayout(FlowLayout.LEADING));
		pnl_c_c.add(pnlIDLine, BorderLayout.CENTER);

		pnlPASSLine = new JPanel();
		lbPASSSave = new JLabel("(save)");
		lbPASSSave.setFont(fontManager.CalibriPLAIN15);
		checkPASS = new JCheckBox();
		lbPASS = new JLabel("PW");
		lbPASS.setPreferredSize(new Dimension(130, 50));
		lbPASS.setFont(fontManager.CalibriPLAIN30);
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
		pnlPASSLine.setPreferredSize(new Dimension((640) - 50, 100));
		pnlPASSLine.setLayout(new FlowLayout(FlowLayout.LEADING));
		pnl_c_c.add(pnlPASSLine, BorderLayout.CENTER);

		pnlForgetLine = new JPanel();
		lbMSG = new JLabel("I'm Forget (ID / Password) ?");
		lbMSG.setFont(fontManager.CalibriUnder18);
		lbMSG.setForeground(Color.blue);
		pnlForgetLine.add(lbMSG);
		pnlForgetLine.setPreferredSize(new Dimension(640 - 50, 80));
		pnlForgetLine.setForeground(Color.blue);
		pnl_c_c.add(pnlForgetLine);

		pnlLoginLine = new JPanel();
		btnLogin = new JButton("Login");
		btnLogin.setPreferredSize(new Dimension(350, 80));
		btnLogin.addActionListener(this);
		btnLogin.setFont(fontManager.CalibriBOLD35);
		btnLogin.setForeground(Color.white);
		pnlLoginLine.add(btnLogin);
		pnlLoginLine.setPreferredSize(new Dimension(640 - 50, 100));
		pnl_c_c.add(pnlLoginLine, BorderLayout.SOUTH);
		
		btnSignUp = new JButton("SingUp");
		btnSignUp.addActionListener(this);
		btnSignUp.setBackground(new Color(50,50,255));
		btnSignUp.setPreferredSize(new Dimension(150,40));
		btnSignUp.setForeground(Color.white);
		btnSignUp.setFont(fontManager.CalibriBOLD20);
		pnl_c_c.add(btnSignUp);
		
		setTheme(Main.colorTheme); // Default Thema
		
		getSave_ID_PASS();
		
		tfID.addKeyListener(this);
		tfPASS.addKeyListener(this);
		pnl_c_c.addKeyListener(this);
		pnl_c_n.addKeyListener(this);
		pnl_c_s.addKeyListener(this);
		pnl_c.addKeyListener(this);
		checkID.addKeyListener(this);
		checkPASS.addKeyListener(this);
		
		
	}
	
	private void getSave_ID_PASS() {
		// TODO Auto-generated method stub
		String tempID, tempPASS;
		tempID = Static_FileInOutStream.fileRead("data/SaveID.txt");
		tempPASS = Static_FileInOutStream.fileRead("data/SavePASS.txt");
		
		if(!tempID.equals("") || tempID!=null){
			tempID = tempID.replace(" ", "");
			tfID.setText(tempID);
			checkID.setSelected(true);
		}
		if(!tempPASS.equals("") || tempPASS!=null){
			tempID = tempID.replace(" ", "");
			tfPASS.setText(tempPASS);
			checkPASS.setSelected(true);
		}
		
		if(!tfID.getText().equals("") && !tfPASS.getText().equals("")){
			btnLogin.setEnabled(true);
		} else {
			btnLogin.setEnabled(false);
		}
		
		if(tfID.getText().equals("")){
			checkID.setSelected(false);
		}
		if(tfPASS.getText().equals("")){
			checkPASS.setSelected(false);
		}
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
		btnLogin.setBackground(new Color(50,30,30));
		pnlLoginLine.setBackground(c);
		pnlSpace2.setBackground(c);
	}

	private void soketLoginTry(){
		Main.alarm.setText("Unable to connect to the server.");
		Main.alarm.setBackground(Color.red);

		Main.soket = ManagerSocket.getInstance();
		String tempID = tfID.getText().replace(" ", "");
		String tempPASS = tfPASS.getText().replace(" ", "");
		Main.soket.toServ.println("/log " + tempID + " " + tempPASS);
		Main.soket.toServ.flush();
		revalidate();
		repaint();

		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin){
			if(tfID.getText().equals("") || tfID.getText()==null){
				JOptionPane.showMessageDialog(null, "Please enter your ID.", "Warning", JOptionPane.WARNING_MESSAGE);
			} else if(tfPASS.getText().equals("") || tfPASS.getText()==null){
				JOptionPane.showMessageDialog(null, "Please enter your Password.", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				Main.soket = ManagerSocket.getInstance();
				soketLoginTry();
			}
			
			checkBox_ID_PASS();
			
			
		} else if(e.getSource()==btnSignUp){
			if(dialog_SignUp==null){
				enabled(false);
				dialog_SignUp = new Dialog_SignUp();
				dialog_SignUp.setVisible(true);
			} else{
				Dialog_SignUp.lbMSG.setText("Please Enter ID");
				Dialog_SignUp.lbMSG.setForeground(Color.gray);
				dialog_SignUp.setVisible(true);
				enabled(false);
			}
		}
	}

	static public void enabled(boolean b) {
		// TODO Auto-generated method stub
		tfID.setEnabled(b);
		tfPASS.setEnabled(b);
		btnLogin.setEnabled(b);
		btnSignUp.setEnabled(b);
	}

	private void checkBox_ID_PASS() {
		// TODO Auto-generated method stub
		if(checkID.isSelected()==true){
			String temp;
			temp = tfID.getText().replace(" ", "");
			Static_FileInOutStream.fileWrite("data/SaveID.txt", temp);
		}
		if(checkPASS.isSelected()==true){
			String temp;
			temp = tfPASS.getText().replace(" ", "");
			Static_FileInOutStream.fileWrite("data/SavePASS.txt", temp);
		}
		if(checkID.isSelected()==false){
			Static_FileInOutStream.fileWrite("data/SaveID.txt", "");
		}
		if(checkPASS.isSelected()==false){
			Static_FileInOutStream.fileWrite("data/SavePASS.txt", "");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		if(e.getKeyCode()==10){
			Main.soket = ManagerSocket.getInstance();
			soketLoginTry();
		}
		
		if(!tfID.getText().equals("")  && tfPASS.getText().equals("") 
				|| tfID != null && tfPASS.getText().equals("")){
			lbMSG.setText("Need -> Input Password");
			lbMSG.setForeground(Color.blue);
			btnLogin.setEnabled(false);
		} else if(!tfPASS.getText().equals("") || tfPASS != null){
			lbMSG.setText("Need -> Login Click or Enter Key");
			lbMSG.setForeground(Color.blue);
			btnLogin.setEnabled(true);
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
