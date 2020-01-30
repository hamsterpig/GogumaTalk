package goguma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Dialog_SignUp  extends JDialog implements ActionListener{
	JPanel pnl;
	JPanel pnl_c, pnl_n, pnl_s, pnl_w, p_e;
	
	JTextField txID_Check, txpass_Check, txNeed, txStateFeild;
	JLabel lbTotal, lbDis, lbInput, lbStateFeild;
	
	JButton btnY, btnN;
	
	SocketManager socket = SocketManager.getInstance();
	
	Dialog_SignUp(){
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
		
		JPanel pnl_c_Line01 = new JPanel();
		JPanel pnl_c_Line02 = new JPanel();
		JPanel pnl_c_Line03 = new JPanel();
		
		lbTotal = new JLabel("ID"); 
		lbDis = new JLabel("PW");
		
		txID_Check = new JTextField(12);
		txpass_Check = new JTextField(12);
		txNeed = new JTextField(12);
		
		btnY = new JButton("SignUp");
		btnY.setBackground(new Color(100,100,255));
		btnN = new JButton("Exit");
		btnN.setBackground(new Color(255,100,100));
		btnY.setPreferredSize(new Dimension(100,60));
		btnN.setPreferredSize(new Dimension(100,60));
		btnN.addActionListener(this);
		btnY.addActionListener(this);
		
		pnl_c_Line01.add(lbTotal);
		pnl_c_Line01.add(txID_Check);
		pnl_c_Line02.add(lbDis);
		pnl_c_Line02.add(txpass_Check);
		pnl_c_Line03.add(btnY);
		pnl_c_Line03.add(btnN);
		pnl_c.add(pnl_c_Line01);
		pnl_c.add(pnl_c_Line02);
		pnl_c.add(pnl_c_Line03);

		
		pnl_c.setBackground(Color.white);
		
		pnl_c.setPreferredSize(new Dimension(230,170));;
		
		setTitle("Sign Up");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(false);
		setResizable(false);
		pack();
		
		setTheme();
	}

	private void setTheme() {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnY){
			System.out.println("/reg " + txID_Check.getText() + " " + txpass_Check.getText());
			socket.toServ.println("/reg " + txID_Check.getText() + " " + txpass_Check.getText());
			socket.toServ.flush();
			txID_Check.setText("");
			txpass_Check.setText("");
			this.setVisible(false);
		} else if(e.getSource()==btnN){
			txID_Check.setText("");
			txpass_Check.setText("");
			this.setVisible(false);
		}
	}
}
