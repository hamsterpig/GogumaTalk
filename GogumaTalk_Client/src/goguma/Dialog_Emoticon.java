package goguma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Dialog_Emoticon  extends JDialog implements ActionListener, MouseListener{
	JPanel pnl;
	
	JPanel pnlMsgField = new JPanel(new ModifiedFlowLayout());
	JButton btnOk;
	
	ImageIcon imgEmoticon1;
	String name = "";
	
	ManagerFont fontManeger = ManagerFont.getInstance();
	
	JFileChooser jfc;
	
	Dialog_Emoticon(){
		pnl = new JPanel(new BorderLayout());
		this.add(pnl, BorderLayout.CENTER);
		//pnlMsgField.setPreferredSize(new Dimension(500,700));
		
		
		pnlMsgField.setBackground(Color.white);
		JScrollPane sc = new JScrollPane(pnlMsgField);
		sc.setPreferredSize(new Dimension(300,300));
		
		pnl.add(sc, BorderLayout.CENTER);
		
		btnOk = new JButton("Exit");
		btnOk.setPreferredSize(new Dimension(200,50));
		btnOk.setBackground(new Color(255,200,200));
		btnOk.addActionListener(this);
		pnl.add(btnOk, BorderLayout.SOUTH);
		
		//
		imgEmoticon1 = new ImageIcon("emoticon/emoticon1.png");
		JLabel lbEmoticon1 = new JLabel();
		lbEmoticon1.setIcon(imgEmoticon1);
		lbEmoticon1.addMouseListener(this);
		pnlMsgField.add(lbEmoticon1);
		
		
		setTitle("Emoticon");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(700,50);
		setVisible(false);
		setResizable(false);
		pack();
		
		//setTheme(new Color(255,210,210));
		
	}
	



	private void setTheme(Color c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnOk){
			
/*			jfc = new JFileChooser();
			int returnVal = jfc.showSaveDialog(null);
	        if(returnVal == 0) {
	            File file = jfc.getSelectedFile();
	            try {
	            	Main.soket.toServ.println("/sendMSG " + file.getCanonicalPath() +" "+ Main.pnl_Profile.lbProfile.getText());
	    			Main.soket.toServ.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }*/
			
			this.setVisible(false);
		}
	}



	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		sendEmoticon("emoticon/emoticon1.png");
		
		
	}

	private void sendEmoticon(String emoticon) {
		// TODO Auto-generated method stub
		ImageIcon img = new ImageIcon(emoticon);			
		DataFormat df = new DataFormat(emoticon, img);
		
		try {
			Main.soket.toServ.println("/sendEmoticon "+name);
			Main.soket.toServ.flush();
			Main.soket.oos.writeObject(df);
			Main.soket.oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Main.pnl_ChatIn.myprivateEmoticon(Main.pnl_Profile.lbProfile.getText(), new ImageIcon(emoticon));
		
		this.setVisible(false);
	}




	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
