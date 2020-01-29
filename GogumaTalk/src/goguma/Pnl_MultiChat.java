package goguma;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pnl_MultiChat extends Pnl_SideBar{
	JLabel lbTitle;
	JPanel pnlList = new JPanel();
	
	Pnl_MultiChat(){
		btnMultiChat.setBackground(Main.colorTheme);
		
		lbTitle = new JLabel();
		lbTitle.setText("Multi Chat");
		lbTitle.setFont(fontManager.CalibriBOLD50);
		pnl_North.add(lbTitle);
		
		pnl_c_c.setBackground(Color.white);
	}
}
