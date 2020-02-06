package goguma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Dialog_ViewAnn  extends JDialog implements ActionListener{
	JPanel pnl;
	
	static JPanel pnlMsgField = new JPanel(new ModifiedFlowLayout());
	JButton btnOk;
	
	ManagerSocket socket = ManagerSocket.getInstance();
	ManagerFont fontManeger = ManagerFont.getInstance();
	
	Dialog_ViewAnn(){
		pnl = new JPanel(new BorderLayout());
		this.add(pnl, BorderLayout.CENTER);
		//pnlMsgField.setPreferredSize(new Dimension(500,700));
		
		
		pnlMsgField.setBackground(Color.black);
		JScrollPane sc = new JScrollPane(pnlMsgField);
		sc.setPreferredSize(new Dimension(500,700));
		
		pnl.add(sc, BorderLayout.CENTER);
		
		btnOk = new JButton("Exit");
		btnOk.setPreferredSize(new Dimension(200,50));
		btnOk.setBackground(new Color(255,200,200));
		btnOk.addActionListener(this);
		btnOk.setFont(fontManeger.CalibriBOLD20);
		pnl.add(btnOk, BorderLayout.SOUTH);
		
		
		
		setTitle("Viewer");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(700,50);
		setVisible(false);
		setResizable(false);
		pack();
		
		//setTheme(new Color(255,210,210));
		
	}
	

	public static void addMsg(String s, Color c) {
		// TODO Auto-generated method stub
		JLabel lbtext = new JLabel();
		lbtext.setText(s);
		lbtext.setPreferredSize(new Dimension(430,29));
		lbtext.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		lbtext.setBackground(Color.black);
		lbtext.setOpaque(true);
		lbtext.setForeground(c);
		pnlMsgField.add(lbtext);
		pnlMsgField.revalidate();
		pnlMsgField.repaint();
	}


	private void setTheme(Color c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnOk){
			this.setVisible(false);
		}
	}


}
