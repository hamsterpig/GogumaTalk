package goguma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PnlMultiChatRoom extends JPanel{
	///Dialog_AddRoom에서 받아온 정보를 통해 로비에 방panel을 만들어 추가
	JPanel pnlRoomBase,pnlTitle,pnlLabel,pnlNumber,pnlImage,pnlLeft,pnlRight;
	JLabel lblTitle,lblIntro,lblNumber,lblPassword,lblImage;
	int x=550,y=100; // 방 패널의 크기
	String passwordCheck="",roomTitle,roomPassword; // 방 비밀번호유무/제목/비번
	PnlMultiChat mtc;
	int myNum=0;
	int maxnum;
	int arraynum;
	String masterid;
	String userid="";
	int curnum;
	ArrayList<String[]> arruser;
	
	
	
	PnlMultiChatRoom(PnlMultiChat multiChat,String title,String image,String password, String contents, int maxNum,int curNum){
		
		
		mtc = multiChat;
		maxnum=maxNum;
		curnum=curNum;
		arraynum=myNum;
		userid=Main.pnl_Profile.lbProfile.getText();
		masterid=userid;
		arruser = new ArrayList<String[]>();
		
		if(password.equals("")||password.equals("null")){
			passwordCheck="Open";
		}else{passwordCheck="Private";}
		roomTitle = title;
		roomPassword = password;
		lblTitle = new JLabel(title);
		lblIntro = new JLabel(contents);
		if(contents.equals("null")){
			lblIntro.setText("");
		}
		lblNumber = new JLabel(curNum+" / "+maxNum+"명");
		lblImage = new JLabel(new ImageIcon(image));
		//lblImage.setPreferredSize(new Dimension(x/5,y-20));
		//lblImage.setPreferredSize(new Dimension(100,80));
		lblPassword = new JLabel(passwordCheck);
		lblPassword.setHorizontalAlignment(JLabel.RIGHT);
		
		lblTitle.setFont(new Font("나눔고딕",Font.BOLD,16));
		lblIntro.setFont(new Font("나눔고딕",Font.PLAIN,14));
		lblIntro.setForeground(new Color(195,195,195));//임시로 해놓은폰트
		
		
		pnlTitle = new JPanel(new FlowLayout(FlowLayout.LEADING,25,0));
		pnlTitle.setBackground(Color.WHITE);
		pnlTitle.add(lblTitle);
		//pnlTitle.add(lblPassword);
		pnlLabel = new JPanel(new FlowLayout(FlowLayout.LEADING,25,0));
		pnlLabel.add(lblIntro);
		pnlLabel.setBackground(Color.WHITE);
		pnlNumber = new JPanel(new FlowLayout(FlowLayout.LEADING,25,0));
		pnlNumber.add(lblNumber);
		pnlNumber.setBackground(Color.WHITE);
		pnlImage = new JPanel();
		pnlImage.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
		pnlImage.setBackground(Color.WHITE);
		pnlImage.setPreferredSize(new Dimension(x/5,y-15));
		pnlImage.setBackground(Color.WHITE);
		pnlImage.add(lblImage);
		
		pnlLeft = new JPanel(new GridLayout(0,1,0,2));
		pnlLeft.setBackground(Color.WHITE);
		pnlLeft.setPreferredSize(new Dimension(x/3*2,y-20));
		pnlLeft.add(lblPassword);
		pnlLeft.add(pnlTitle);
		pnlLeft.add(pnlLabel);
		pnlLeft.add(pnlNumber);
		
		pnlRight = new JPanel();
		pnlRight.setBackground(Color.WHITE);
		pnlRight.setPreferredSize(new Dimension(x/5,y-15));
		pnlRight.add(pnlImage);
		
		pnlRoomBase = new JPanel();
		pnlRoomBase.setBackground(Color.WHITE);
		pnlRoomBase.setPreferredSize(new Dimension(x,y));
		
		pnlRoomBase.add(pnlRight);
		pnlRoomBase.add(pnlLeft);
		add(pnlRoomBase);
		
		mtc.chatroom.add(this);
		for(int i=0;i<mtc.chatroom.size();i++){
		mtc.chatroom.get(i).addMouseListener(mtc);
		
		}
		
		myNum++;

	}
}
