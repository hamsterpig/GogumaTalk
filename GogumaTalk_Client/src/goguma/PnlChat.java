package goguma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PnlChat extends PnlSideBar{
	
	JLabel lbTitle;

	static JLabel lbMsg;
	
	JScrollPane scList;
	static ArrayList<PnlChatFreind> chatRoom;
	static JPanel pnlList;
	
	static int rCNT = 0;
	
	static String chatName = "";
	static int chatNum = 0;

	PnlChat(){
		
		btnChat.setBackground(Main.colorTheme);
		
		lbTitle = new JLabel();
		lbTitle.setText("1:1 Chat");
		lbTitle.setFont(fontManager.CalibriBOLD50);
		pnl_North.add(lbTitle);
		
		pnlList = new JPanel();
		pnlList.setBackground(Color.white);
		pnlList.setLayout(new ModifiedFlowLayout());
		scList = new JScrollPane();
		scList.getViewport().setBackground(Color.white);
		scList.setPreferredSize(new Dimension(640, 710));
		chatRoom = new ArrayList<PnlChatFreind>();
		
		/*for(int i=0; i<2; i++){ // test Room
			chatRoom.add(new Pnl_ChatFreind("Test"+i));
		}*/
		//updateRoom();
		

		scList.setViewportView(pnlList); // scroll Panel
		pnl_c_c.add(scList);
		
		setTheme(Main.colorTheme);
	}
	
	void setTheme(Color c) {
		// TODO Auto-generated method stub
		this.setBackground(c);
		lbSpace.setBackground(c);
		pnlUnderMenu.setBackground(c);
		pnl_North.setBackground(c);
		pnl_South.setBackground(c);
		pnl_c_c.setBackground(c);
		pnl_Center.setBackground(c);
		pnl_c_n.setBackground(c);
		pnl_c_s.setBackground(c);
		pnl_c_c.setBackground(c);
		btnChat.setBackground(c);
	}

	public static void updateRoom() {
		// TODO Auto-generated method stub
		for(int i=0; i<chatRoom.size(); i++){
			pnlList.add(chatRoom.get(i));
			rCNT++;
		}
		
		if(chatRoom.size() == 0 || rCNT == 0){
			JPanel pTest = new JPanel();
			pTest.setBackground(Color.white);
			pTest.setPreferredSize(new Dimension(620,70));
			lbMsg = new JLabel("Please Add Chat");
			lbMsg.setFont(fontManager.CalibriPLAIN35);
			lbMsg.setBackground(Color.red);
			lbMsg.setOpaque(true);
			pTest.add(lbMsg);
			pnlList.add(pTest);
		}
	}
	
}
