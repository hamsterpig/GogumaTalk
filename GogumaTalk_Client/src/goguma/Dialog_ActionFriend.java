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

public class Dialog_ActionFriend  extends JDialog implements ActionListener, KeyListener{
	JPanel pnl;
	JPanel pnl_c, pnl_n, pnl_s, pnl_w, p_e;
	
	JTextField txID_Check, txpass_Check, txNeed, txStateFeild;
	JLabel lbTotal, lbDis, lbInput, lbStateFeild;
	JLabel lbMSG;
	JPanel pnl_c_Line01, pnl_c_Line02, pnl_c_Line03, pnl_c_Line04, pnl_c_Line05;
	
	JButton btnDel, btnChat, btnN;
	
	static String targetName;
	
	ManagerSocket socket = ManagerSocket.getInstance();
	ManagerFont fontManeger = ManagerFont.getInstance();
	
	Dialog_ActionFriend(){

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
		
		txID_Check = new JTextField();
		txID_Check.setPreferredSize(new Dimension(170,40));
		txID_Check.setHorizontalAlignment(txID_Check.CENTER);
		txpass_Check = new JTextField(12);
		txNeed = new JTextField(12);
		txID_Check.setEditable(false);
		
		btnDel = new JButton("Del");
		btnDel.setBackground(new Color(100,100,255));
		btnDel.setPreferredSize(new Dimension(190,35));
		btnDel.addActionListener(this);
		btnChat = new JButton("Send To");
		btnChat.setBackground(new Color(255,255,100));
		btnChat.setPreferredSize(new Dimension(190,35));
		btnChat.addActionListener(this);
		btnN = new JButton("Exit");
		btnN.setBackground(new Color(255,100,100));
		btnN.setPreferredSize(new Dimension(190,35));
		btnN.addActionListener(this);
		
		
		lbMSG = new JLabel();
		lbMSG.setPreferredSize(new Dimension(200,25));
		lbMSG.setOpaque(true);
		lbMSG.setHorizontalAlignment(lbMSG.CENTER);
		//lbMSG.setFont(fontManeger.GodicITALIC15);
		lbMSG.setText("What do you want to do?");
		lbMSG.setForeground(Color.gray);
		
		pnl_c_Line01 = new JPanel();
		pnl_c_Line02 = new JPanel();
		pnl_c_Line03 = new JPanel();
		pnl_c_Line04 = new JPanel();
		pnl_c_Line05 = new JPanel();
		
		//pnl_c_Line01.add(lbTotal);
		pnl_c_Line01.add(txID_Check);
		pnl_c_Line02.add(lbMSG);
		
		pnl_c_Line03.add(btnChat);
		pnl_c_Line04.add(btnDel);
		pnl_c_Line05.add(btnN);
		pnl_c.add(pnl_c_Line01);
		pnl_c.add(pnl_c_Line02);
		pnl_c.add(pnl_c_Line03);
		pnl_c.add(pnl_c_Line04);
		pnl_c.add(pnl_c_Line05);

		
		pnl_c.setBackground(Color.white);
		pnl_c.setPreferredSize(new Dimension(230,250));;
		
		setTitle("Action Friend");
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
		if(e.getSource()==btnDel){
			if(txID_Check.getText().equals("") || txID_Check.getText() == null){
				lbMSG.setText("Do not enter ID !");
				lbMSG.setForeground(Color.red);
			} else {
				System.out.println("/del/friend " + txID_Check.getText());
				socket.toServ.println("/del/friend " + txID_Check.getText());
				socket.toServ.flush();
				txID_Check.setText("");
				this.setVisible(false);
			}
		} else if(e.getSource()==btnChat){
			if(txID_Check.getText().equals("") || txID_Check.getText() == null){
				lbMSG.setText("Do not enter ID !");
				lbMSG.setForeground(Color.red);
			} else {
				int tempLocation = 0;
				System.out.println("/make/privateChat " + txID_Check.getText());
				socket.toServ.println("/make/privateChat " + txID_Check.getText());
				socket.toServ.flush();
				
				PnlChat.chatName = txID_Check.getText();
				Main.changePnl(Main.pnl_Profile, Main.pnl_Chat);
				Main.changePnl(Main.pnl_Chat, Main.pnl_ChatIn);
/*				for(int i=0; i<PnlProfile.profilePerson.size(); i++){
					if(PnlProfile.profilePerson.get(i).equals(txID_Check.getText())){
						tempLocation = i;
						break;
					}
					i++;
				}*/
				
				
				txID_Check.setText("");
				this.setVisible(false);
			}
		}else if(e.getSource()==btnN){
			txID_Check.setText("");
			txpass_Check.setText("");
			this.setVisible(false);
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
