package goguma;

import javax.swing.JLabel;

public class Pnl_MultiChat extends Pnl_SideBar{
	JLabel lbTitle;
	
	Pnl_MultiChat(){
		btnMultiChat.setBackground(Main.colorTheme);
		
		lbTitle = new JLabel();
		lbTitle.setText("Multi Chat");
		lbTitle.setFont(fontManager.CalibriBOLD50);
		pnl_North.add(lbTitle);
	}
}
