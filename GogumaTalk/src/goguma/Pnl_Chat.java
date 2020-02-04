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

public class Pnl_Chat extends Pnl_SideBar{
	
	JLabel lbTitle;

	static JLabel lbMsg;
	
	JScrollPane scList;
	static ArrayList<Pnl_ChatFreind> chatRoom;
	static JPanel pnlList;
	
	static int rCNT = 0;
	
	static String chatName = "";
	static int chatNum = 0;

	Pnl_Chat(){
		
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		
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
		scList.setPreferredSize(new Dimension(res.width/3, 710));
		
		chatRoom = new ArrayList<Pnl_ChatFreind>();
		
		/*for(int i=0; i<2; i++){ // test Room
			chatRoom.add(new Pnl_ChatFreind("Test"+i));
		}*/
		//updateRoom();
		

		scList.setViewportView(pnlList); // scroll Panel
		pnl_c_c.add(scList);
		
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
