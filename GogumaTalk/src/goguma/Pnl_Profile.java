package goguma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Pnl_Profile extends Pnl_SideBar implements ActionListener {

	static JLabel lbMsg = new JLabel();
	JScrollPane scList;
	static int pCNT = 0;
	static JPanel pTest;
	static JPanel pnlList;
	Dialog_Friend dialog_AddFriend;
	JLabel isOnoff;
	JButton btnEditProfile;

	

	
	
	static ArrayList<Pnl_ProfilePerson> profilePerson = new ArrayList<Pnl_ProfilePerson>();

	Pnl_Profile() {
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		FontManager fontManager = FontManager.getInstance();
		// Frame Default Size
		this.setPreferredSize(new Dimension(640, 1080));
		this.setBackground(Color.yellow);

		pnl_North.setPreferredSize(new Dimension(640, 175));
		pnl_North.setLayout(new FlowLayout());
		pnl_North.setBackground(new Color(100, 100, 255));

		//lbSpace = new JLabel(" Friend State : ");
		lbSpace.setPreferredSize(new Dimension(430, 80));
		lbSpace.setFont(fontManager.GodicBOLD25);
		lbSpace.setOpaque(true);
		lbSpace.setForeground(new Color(50,50,255));

		pnlMenu = new JPanel(new FlowLayout(FlowLayout.LEADING));
		pnlMenu.setPreferredSize(new Dimension(640, 80));
		pnlMenu.setBackground(Color.green);
		pnlMenu.setOpaque(true);

		btnsearch = new JButton();
		btnsearch.setIcon(Function.lbImageSetSize("img/search.png", 60, 60));
		btnsearch.setPreferredSize(new Dimension(60, 60));

		btnaddPerson = new JButton();
		btnaddPerson.setIcon(Function.lbImageSetSize("img/addPerson.png",
				60, 60));
		btnaddPerson.setPreferredSize(new Dimension(60, 60));

		btnLogout = new JButton();
		btnLogout
				.setIcon(Function.lbImageSetSize("img/logout.png", 60, 60));
		btnLogout.setPreferredSize(new Dimension(60, 60));

		pnlMenu.setAlignmentY(pnlMenu.CENTER_ALIGNMENT);
		pnlMenu.add(lbSpace);
		pnlMenu.add(btnsearch);
		pnlMenu.add(btnaddPerson);
		pnlMenu.add(btnLogout);
		//pnlMenu.add(btnSetting);

		pnl_North.add(pnlMenu);
		
		lbProfile = new JLabel("Name");
		lbProfileImg = new JLabel("IMG");
		lbProfileImg.setIcon(Function.lbImageSetSize("img/person.png", 75,
				75));
		lbProfileImg.setPreferredSize(new Dimension(70, 70));
		lbProfile.setFont(fontManager.CalibriBOLD50);
		pnlProfileLine = new JPanel();
		pnlProfileLine.setPreferredSize(new Dimension(640, 80));
		pnlProfileLine.setBackground(Color.blue);
		pnlProfileLine.setOpaque(true);

		isOnoff = new JLabel(" ● ");
		isOnoff.setForeground(Color.green);
		isOnoff.setFont(fontManager.GodicBOLD25);
		
		btnEditProfile = new JButton("Edit");
		btnEditProfile.setFont(fontManager.CalibriBOLD20);
		btnEditProfile.setBackground(new Color(180,180,255));
		btnEditProfile.setPreferredSize(new Dimension(90,40));
		btnEditProfile.addActionListener(this);
		
		pnlProfileLine.setLayout(new FlowLayout(FlowLayout.LEADING));
		pnlProfileLine.add(lbProfileImg);
		pnlProfileLine.add(lbProfile);
		pnlProfileLine.add(isOnoff);
		pnlProfileLine.add(btnEditProfile);
		
		pnl_North.add(pnlProfileLine);

		pnl_Center.setBackground(Color.white);
		pnl_Center.setOpaque(true);
		pnlList = new JPanel();
		pnlList.setBackground(Color.white);
		pnlList.setLayout(new ModifiedFlowLayout());
		scList = new JScrollPane();
		scList.setViewportView(pnlList);
		scList.getViewport().setBackground(Color.white); // Scroll Color

		pnl_Center.add(scList);
		setTheme(Main.colorTheme);
		
		//add Panel
		btnLogout.addActionListener(this);
		btnaddPerson.addActionListener(this);
		
		/*addPerson("name", "true");
		addPerson("name2", "false");*/
		
		updatePerson();
		addPerson("name", "true");
		addPerson("name", "true");
		
	} // new 
	
	static public void addPerson(String name, String connect){
		profilePerson.add(new Pnl_ProfilePerson(name, connect));
		updatePerson();
	}
	
	

	static public void updatePerson() {
		// TODO Auto-generated method stub
		pTest = new JPanel();
		pnlList.removeAll();
		pnlList.revalidate();
		pnlList.repaint();
		
		
		if(profilePerson.size() == 0 || pCNT == 0){
			pTest.setBackground(Color.white);
			pTest.setPreferredSize(new Dimension(620,70));
			lbMsg = new JLabel("Please Add Friend");
			lbMsg.setFont(fontManager.CalibriBOLD35);
			lbMsg.setBackground(Color.white);
			lbMsg.setOpaque(true);
			pTest.add(lbMsg);
			pnlList.add(pTest);
		}
		
		
		for(int i=0; i<profilePerson.size(); i++){
			pnlList.add(profilePerson.get(i));
			pCNT++;
		}
		pnlList.revalidate();
		pnlList.repaint();
		
		updateFriendState();
	}

	private static void updateFriendState() {
		// TODO Auto-generated method stub
		if(Main.isLogin==true){ // 전체 친구 중 온/오프라인 수 표시
			int tempOnLine = 0;
			int tempOffLine = 0;
			for(int i=0; i<Pnl_Profile.profilePerson.size(); i++){
				if(Pnl_Profile.profilePerson.get(i).lbLastMsg.getText().equals("OnLine")){
					tempOnLine++;
				} else {
					tempOffLine++;
				}
				
			}
			
			lbSpace.setText(" Friend State : " + tempOnLine + " / " + Pnl_Profile.profilePerson.size());
			
		}
	}

	private void setTheme(Color c) { // TODO Auto-generated method stub
		this.setBackground(c);
		lbSpace.setBackground(c);
		pnlMenu.setBackground(c);
		pnlProfileLine.setBackground(new Color(255,255,230));
		btnProfile.setBackground(c);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnaddPerson){
			System.out.println("dd");
		}
		if(e.getSource()==btnaddPerson){
			if(dialog_AddFriend==null){
				dialog_AddFriend = new Dialog_Friend();
				dialog_AddFriend.setVisible(true);
			} else{
				Dialog_Friend.lbMSG.setText("Please Enter ID");
				Dialog_Friend.lbMSG.setForeground(Color.gray);
				dialog_AddFriend.setVisible(true);
			}
		}
	
	}
}
