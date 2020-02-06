package goguma;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class Dialog_PassWord extends JDialog implements ActionListener{
	//��й�ȣ�� ������ �濡 ���� �ߴ� dialog
	JPanel pnlBase,pnlBtn;
	JLabel lblPassword,lblCheck;
	JTextField tfPassword;
	JButton btnCheck,btnCancel;
	String pw="123";
	static PnlMultiChat mtc;
	int pnlNum;
	String userId;
	
	public Dialog_PassWord(PnlMultiChat multichat,String password,int arrayNum,String id){//�����г�,���,���� arrayNumber
		setSize(250,200);
		setLocationRelativeTo(null);
		mtc=multichat;
		pw=password;
		pnlNum = arrayNum;
		userId = id;
		
		lblPassword = new JLabel("��й�ȣ�� �Է��ϼ���.");
		tfPassword = new JTextField(18);
		lblCheck = new JLabel("");		
		lblCheck.setHorizontalAlignment(JLabel.CENTER);
		lblCheck.setPreferredSize(new Dimension(200,35));
		btnCheck = new JButton("Ȯ��");
		btnCheck.addActionListener(this);
		btnCancel=new JButton("���");
		btnCancel.addActionListener(this);
		
		pnlBtn = new JPanel();
		pnlBtn.add(btnCheck);
		pnlBtn.add(btnCancel);
		
		pnlBase = new JPanel();
		pnlBase.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlBase.add(lblPassword);
		pnlBase.add(tfPassword);
		pnlBase.add(lblCheck);
		pnlBase.add(pnlBtn);
		
		add(pnlBase);
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnCheck){
			Main.soket.toServ.println("/access/room " + mtc.chatroom.get(pnlNum).lblTitle.getText() + " " + tfPassword.getText());
			Main.soket.toServ.flush();
			System.out.println(tfPassword.getText());
			if(!pw.equals(tfPassword.getText())){
				lblCheck.setText("��й�ȣ�� �ٽ� �Է����ּ���.");
				lblCheck.setForeground(Color.RED);
			}
			else{
				lblCheck.setText("OK!");
				lblCheck.setForeground(Color.GREEN);				
				mtc.pnl_c_c.removeAll();
				mtc.pnl_c_c.add(new PnlOpenChat(mtc,mtc.arrayMax,pnlNum,"����",mtc.chatroom.get(pnlNum).arruser));
				mtc.pnl_c_c.revalidate();
				mtc.pnl_c_c.repaint();
				this.setVisible(false);
			}
		}else if(e.getSource()==btnCancel){
			this.setVisible(false);
		}
		
	}

}
