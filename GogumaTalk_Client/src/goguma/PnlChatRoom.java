package goguma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PnlChatRoom extends JPanel implements ActionListener,KeyListener{
	ManagerFont fontManager = ManagerFont.getInstance();
	
	static JPanel pnl_ChatIn_c;

	JPanel pnl_ChatIn_n;

	JPanel pnl_ChatIn_s;

	JPanel pnlMyChat1;

	JPanel pnlMyChat2;
	static JPanel pnlYourChat;

	static JPanel pnlYourChat_L;

	static JPanel pnlYourChat_R;

	static JPanel pnlYourName;

	static JPanel pnlYourChat1;

	static JPanel pnlYourChat2;
	JButton btnExit,btnDelete,btnFunction;
	static JTextField tfChat;
	JLabel lblMyChat;

	static JLabel lblYourChat;

	static JLabel lblYourName;

	static JLabel lblYourImage;
	static String tmp="";
	static JLabel lbFriendName, lbOnOff;
	static Color bg;
	static int n=39;
	static JScrollPane scr_center;
	
	Dialog_Emoticon dialog_Emoticon;
	
	PnlChatRoom(){
		bg =new Color(102,153,255);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.black);
		pnl_ChatIn_c = new JPanel();
		pnl_ChatIn_c.setBackground(new Color(102,153,255));
		//pnl_ChatIn_c.setPreferredSize(new Dimension(720, 880));
		pnl_ChatIn_c.setLayout(new ModifiedFlowLayout());
		
		scr_center = new JScrollPane(pnl_ChatIn_c,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scr_center.setPreferredSize(new Dimension(720, 880));

		pnl_ChatIn_n = new JPanel(new FlowLayout(FlowLayout.LEADING));
		pnl_ChatIn_n.setBackground(new Color(125,165,255));
		pnl_ChatIn_n.setPreferredSize(new Dimension(720, 60));
		
		lbFriendName = new JLabel();
		lbFriendName = new JLabel();
		lbFriendName.setText(PnlChat.chatName);
		lbFriendName.setFont(fontManager.GodicBOLD25);
		lbFriendName.setPreferredSize(new Dimension(500,60));
		
		lbOnOff = new JLabel();
		lbOnOff.setText(" ● ");
		lbOnOff.setFont(fontManager.GodicBOLD25);
		lbOnOff.setForeground(Color.green);
		
		
		btnExit = new JButton("Exit");
		btnExit.setPreferredSize(new Dimension(60,40));
		btnExit.setFont(fontManager.CalibriBOLD15);
		btnExit.setBackground(new Color(255,180,180));
		btnExit.addActionListener(this);
		
		btnFunction = new JButton("+");
		btnFunction.setPreferredSize(new Dimension(40,40));
		btnFunction.addActionListener(this);
		
		btnDelete = new JButton("X");
		btnDelete.setPreferredSize(new Dimension(40,40));
		btnDelete.addActionListener(this);
		
		tfChat = new JTextField(47);
		tfChat.setPreferredSize(new Dimension(40,40));
		tfChat.addKeyListener(this);
		
		pnl_ChatIn_s = new JPanel();
		pnl_ChatIn_s.setBackground(bg);
		pnl_ChatIn_s.setPreferredSize(new Dimension(720, 60));
		pnl_ChatIn_s.add(btnFunction);
		pnl_ChatIn_s.add(tfChat);
		pnl_ChatIn_s.add(btnDelete);
		
		pnl_ChatIn_n.add(lbOnOff);
		pnl_ChatIn_n.add(lbFriendName);
		pnl_ChatIn_n.add(btnExit);

		this.add(pnl_ChatIn_n, BorderLayout.NORTH);
		this.add(scr_center, BorderLayout.CENTER);
		this.add(pnl_ChatIn_s,"South");
		updateState();
	}
	void myChat(String id,String msg,int num){
		
		lblMyChat = new JLabel(msg);		
		lblMyChat.setHorizontalAlignment(JLabel.RIGHT);
		pnlMyChat1 = new JPanel();
		pnlMyChat1.setBackground(Color.ORANGE);
		pnlMyChat1.add(lblMyChat);
		
		pnlMyChat2 = new JPanel();
		pnlMyChat2.setLayout(new FlowLayout(FlowLayout.TRAILING));
		pnlMyChat2.setPreferredSize(new Dimension(555,37+18*(num)));//555,47
		pnlMyChat2.setBackground(bg);
		pnlMyChat2.add(pnlMyChat1);
		
		pnl_ChatIn_c.add(pnlMyChat2);
		
		tmp=id;
	}
	static void yourChat(String id,String image,String msg,int num){
		//왼쪽
		lblYourImage = new JLabel(new ImageIcon("img/img5.png"));
		lblYourImage.setPreferredSize(new Dimension(40,40));
		lblYourImage.setBounds(3,3,35,35);
		pnlYourChat_L = new JPanel();
		pnlYourChat_L.setPreferredSize(new Dimension(40,40));
		pnlYourChat_L.setBackground(bg);
		pnlYourChat_L.setLayout(new BorderLayout());
		
		///오른쪽
		lblYourName = new JLabel(id);
		lblYourName.setHorizontalAlignment(JLabel.LEFT);
		pnlYourName = new JPanel(new FlowLayout(FlowLayout.LEADING));
		pnlYourName.setPreferredSize(new Dimension(500,30));
		pnlYourName.setBackground(bg);
		pnlYourName.add(lblYourName);
		//채팅(오른쪽)
		lblYourChat = new JLabel(msg);
		lblYourChat.setHorizontalAlignment(JLabel.LEFT);
		pnlYourChat1 = new JPanel();
		pnlYourChat1.setBackground(Color.white);
		pnlYourChat1.add(lblYourChat);
		pnlYourChat2 = new JPanel();
		pnlYourChat2.setLayout(new FlowLayout(FlowLayout.LEADING));
		pnlYourChat2.setBackground(bg);
		pnlYourChat2.setPreferredSize(new Dimension(500,33+18*(num)));
		pnlYourChat2.add(pnlYourChat1);
		pnlYourChat_R = new JPanel();
		pnlYourChat_R.setLayout(new BoxLayout(pnlYourChat_R,BoxLayout.Y_AXIS));		
		
		pnlYourChat = new JPanel();
		pnlYourChat.setBackground(bg);
		
		if(tmp.equals(id)){
		lblYourImage.setText("");
		pnlYourChat_R.add(pnlYourChat2);
		pnlYourChat.add(pnlYourChat_L);

		}else{
		pnlYourChat_R.add(pnlYourName);
		pnlYourChat_R.add(pnlYourChat2);
		pnlYourChat_L.add(lblYourImage);
		pnlYourChat.add(pnlYourChat_L);

		}
		pnlYourChat.add(pnlYourChat_R);
		pnl_ChatIn_c.add(pnlYourChat);	
		
		tmp=id;		
	}
	public void myprivateEmoticon(String id,ImageIcon img){
		
		lblMyChat = new JLabel(Function.imageSetSize(img, 100, 100));		
		lblMyChat.setHorizontalAlignment(JLabel.RIGHT);
		pnlMyChat1 = new JPanel();
		pnlMyChat1.setBackground(bg);
		pnlMyChat1.add(lblMyChat);
		
		pnlMyChat2 = new JPanel();
		pnlMyChat2.setLayout(new FlowLayout(FlowLayout.TRAILING));
		pnlMyChat2.setPreferredSize(new Dimension(554,120));//555,47
		pnlMyChat2.setBackground(bg);
		pnlMyChat2.add(pnlMyChat1);
		
		pnl_ChatIn_c.add(pnlMyChat2);
		pnl_ChatIn_c.repaint();
		pnl_ChatIn_c.revalidate();
		
		tmp=id;
	}
	static void privateEmoticon(String id, ImageIcon image){
		//왼쪽
		lblYourImage = new JLabel(new ImageIcon("img/img5.png"));
		lblYourImage.setPreferredSize(new Dimension(40,40));
		lblYourImage.setBounds(3,3,35,35);
		pnlYourChat_L = new JPanel();
		pnlYourChat_L.setPreferredSize(new Dimension(40,40));
		pnlYourChat_L.setBackground(bg);
		pnlYourChat_L.setLayout(new BorderLayout());
		
		///오른쪽
		lblYourName = new JLabel();
		lblYourName.setText(id);
		lblYourName.setHorizontalAlignment(JLabel.LEFT);
		pnlYourName = new JPanel(new FlowLayout(FlowLayout.LEADING));
		pnlYourName.setPreferredSize(new Dimension(500,30));
		pnlYourName.setBackground(bg);
		pnlYourName.add(lblYourName);
		//채팅(오른쪽)
		lblYourChat = new JLabel();
		lblYourChat.setIcon(Function.imageSetSize(image, 100, 100));
		lblYourChat.setHorizontalAlignment(JLabel.LEFT);
		pnlYourChat1 = new JPanel();
		pnlYourChat1.setBackground(bg);
		pnlYourChat1.add(lblYourChat);
		pnlYourChat2 = new JPanel();
		pnlYourChat2.setLayout(new FlowLayout(FlowLayout.LEADING));
		pnlYourChat2.setBackground(bg);
		pnlYourChat2.setPreferredSize(new Dimension(100,100));
		pnlYourChat2.add(pnlYourChat1);
		pnlYourChat_R = new JPanel();
		pnlYourChat_R.setLayout(new BoxLayout(pnlYourChat_R,BoxLayout.Y_AXIS));		
		
		pnlYourChat = new JPanel();
		pnlYourChat.setBackground(bg);
		
		
		if(tmp.equals(id)){
		lblYourImage.setText("");
		pnlYourChat_R.add(pnlYourChat2);
		pnlYourChat.add(pnlYourChat_L);
		pnlYourChat.setPreferredSize(new Dimension(554,125));
		pnlYourChat2.setPreferredSize(new Dimension(120,130));
		pnlYourChat.setLayout(new FlowLayout(FlowLayout.LEADING));
		}else{
		pnlYourChat_R.add(pnlYourName);
		pnlYourChat_R.add(pnlYourChat2);
		pnlYourChat_L.add(lblYourImage);
		pnlYourChat.add(pnlYourChat_L);
		pnlYourChat.setPreferredSize(new Dimension(554,160));
		pnlYourChat2.setPreferredSize(new Dimension(120,130));

		}
		pnlYourChat.add(pnlYourChat_R);
		pnl_ChatIn_c.add(pnlYourChat);	
		
		tmp=id;		
		
		pnl_ChatIn_c.revalidate();
		pnl_ChatIn_c.repaint();
	}

	private void updateState() {
		// TODO Auto-generated method stub
	}
	public static void privateChat(String msg){
		String str = new String(msg);
		int num = str.length();
		String str2 = new String("");
		if(num/n>=1){
			for(int i =1;i<=num/n;i++){
				str2=str2+str.substring(n*(i-1),i*n)+"<br>";
				if(i==num/n){
					str2=str2+str.substring(i*n,str.length());
				}
			}
		}else{
			str2=str;
		}
		yourChat(lbFriendName.getText(),"image","<html>"+str2+"</html>",num/n);
		pnl_ChatIn_c.revalidate();
		pnl_ChatIn_c.repaint();
		scr_center.revalidate();
		scr_center.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(btnExit==e.getSource()){
			Main.changePnl(this, Main.pnl_Chat);
						
		}else if(btnFunction==e.getSource()){
/*			String str = new String(tfChat.getText()); // 상대로부터 받는 테스트 코드
			int num = str.length();
			String str2 = new String("");
			if(num/n>=1){
				for(int i =1;i<=num/n;i++){
					str2=str2+str.substring(n*(i-1),i*n)+"<br>";
					if(i==num/n){
						str2=str2+str.substring(i*n,str.length());
					}
				}
			}else{
				str2=str;
			}
			yourChat(lbFriendName.getText(),"image","<html>"+str2+"</html>",num/n);*/
			
			if(dialog_Emoticon==null){
				dialog_Emoticon = new Dialog_Emoticon();
				dialog_Emoticon.name = lbFriendName.getText();
				dialog_Emoticon.setVisible(true);
			} else{
				dialog_Emoticon.name = lbFriendName.getText();
				dialog_Emoticon.setVisible(true);
			}
			
			
		}else if(btnDelete == e.getSource()){
			tfChat.setText("");
		}
		
		scr_center.getVerticalScrollBar().setValue(scr_center.getVerticalScrollBar().getMaximum());
		pnl_ChatIn_c.revalidate();
		pnl_ChatIn_c.repaint();
		scr_center.revalidate();
		scr_center.repaint();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==10){
			Main.soket.toServ.println("/sendMSG " + lbFriendName.getText() +" "+ tfChat.getText());
			Main.soket.toServ.flush();

			String str = new String(tfChat.getText());
			int num = str.length();
			String str2 = new String("");
			if(num/n>=1){
				
				for(int i =1;i<=num/n;i++){
					
					str2=str2+str.substring(n*(i-1),i*n)+"<br>";
					
					if(i==num/n){
						str2=str2+str.substring(i*n,str.length());
					}
				}
				
			}else{
				str2=str;
			}
			myChat("myid","<html>"+str2+"</html>",num/n);	
			
			
			tfChat.setText("");
		}
		scr_center.getVerticalScrollBar().setValue(scr_center.getVerticalScrollBar().getMaximum());
		pnl_ChatIn_c.revalidate();
		pnl_ChatIn_c.repaint();
/*		scr_center.revalidate();
		scr_center.repaint(); */
	}
	@Override
	public void keyReleased(KeyEvent arg0) {

		scr_center.getVerticalScrollBar().setValue(scr_center.getVerticalScrollBar().getMaximum());
		pnl_ChatIn_c.revalidate();
		pnl_ChatIn_c.repaint();
		scr_center.revalidate();
		scr_center.repaint();
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
