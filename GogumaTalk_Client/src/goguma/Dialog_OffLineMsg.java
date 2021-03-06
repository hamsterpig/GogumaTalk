package goguma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Dialog_OffLineMsg  extends JDialog implements ActionListener, KeyListener{
	JPanel pnl;
	JPanel pnl_c, pnl_n, pnl_s, pnl_w, p_e;
	
	JTextField txID_Check, txpass_Check, txNeed, txStateFeild;
	JLabel lbTotal, lbDis, lbInput, lbStateFeild;
	static JLabel lbMSG;
	JPanel pnl_c_Line01, pnl_c_Line02, pnl_c_Line03, pnl_c_Line04;
	
	JButton btnY, btnN;
	
	ManagerSocket socket = ManagerSocket.getInstance();
	ManagerFont fontManeger = ManagerFont.getInstance();
	
	Dialog_OffLineMsg(){
		
		pnl = new JPanel(new BorderLayout());
		pnl_c = new JPanel();
		pnl_n = new JPanel();
		pnl_s = new JPanel();
		pnl_w = new JPanel();
		p_e = new JPanel();
		
		this.add(pnl, BorderLayout.CENTER);
		pnl.add(pnl_c, BorderLayout.CENTER);
		pnl.add(pnl_n, BorderLayout.PAGE_START);
		pnl.add(pnl_s, BorderLayout.PAGE_END);
		pnl.add(pnl_w, BorderLayout.LINE_START);
		pnl.add(p_e, BorderLayout.LINE_END);
		
		
		
		lbTotal = new JLabel("ID"); 
		lbDis = new JLabel("PW");
		
		txID_Check = new JTextField(12);
		txpass_Check = new JTextField(12);
		txNeed = new JTextField(12);
		
		btnY = new JButton("Add");
		btnY.setBackground(new Color(100,100,255));
		btnY.setPreferredSize(new Dimension(100,60));
		btnY.addActionListener(this);
		
		btnN = new JButton("Exit");
		btnN.setBackground(new Color(255,100,100));
		btnN.setPreferredSize(new Dimension(190,30));
		btnN.addActionListener(this);
		
		lbMSG = new JLabel();
		lbMSG.setPreferredSize(new Dimension(360,25));
		lbMSG.setOpaque(true);
		lbMSG.setHorizontalAlignment(lbMSG.CENTER);
		//lbMSG.setFont(fontManeger.GodicITALIC15);
		lbMSG.setText("You Cannot Talk Because The Other Party Is Offline.");
		lbMSG.setFont(fontManeger.CalibriBOLD15);
		lbMSG.setForeground(Color.red);
		
		pnl_c_Line01 = new JPanel();
		pnl_c_Line02 = new JPanel();
		pnl_c_Line03 = new JPanel();
		pnl_c_Line04 = new JPanel();
		
		//pnl_c_Line01.add(lbTotal);
		//pnl_c_Line01.add(txID_Check);
		//pnl_c_Line02.add(lbDis);
		//pnl_c_Line02.add(txpass_Check);
		
		pnl_c_Line03.add(lbMSG);
		
		//pnl_c_Line04.add(btnY);
		pnl_c_Line04.add(btnN);
		pnl_c.add(pnl_c_Line01);
		pnl_c.add(pnl_c_Line02);
		pnl_c.add(pnl_c_Line03);
		pnl_c.add(pnl_c_Line04);

		
		pnl_c.setBackground(Color.white);
		pnl_c.setPreferredSize(new Dimension(370,100));;
		
		setTitle("Cannot Talk");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(false);
		setResizable(false);
		pack();
		
		setTheme(new Color(255,210,210));
		
		txID_Check.addKeyListener(this);
		txpass_Check.addKeyListener(this);
		
		
	}
	

	private void setTheme(Color c) {
		// TODO Auto-generated method stub
		pnl.setBackground(c);
		pnl_c.setBackground(c);
		pnl_n.setBackground(c);
		pnl_s.setBackground(c);
		pnl_w.setBackground(c);
		p_e.setBackground(c);
		pnl_c_Line01.setBackground(c);
		pnl_c_Line02.setBackground(c);
		pnl_c_Line03.setBackground(c);
		pnl_c_Line04.setBackground(c);
		lbMSG.setBackground(new Color(150,255,150));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnY){
			
		} else if(e.getSource()==btnN){
			txID_Check.setText("");
			txpass_Check.setText("");
			this.setVisible(false);
			Main.changePnl(Main.pnl_Chat, Main.pnl_Profile);
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(!txID_Check.getText().equals("") && txpass_Check.getText().equals("")){

		} else if(!txpass_Check.getText().equals("")){

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
