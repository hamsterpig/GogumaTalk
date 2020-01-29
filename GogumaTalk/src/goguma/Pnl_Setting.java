package goguma;

import javax.swing.JLabel;

public class Pnl_Setting extends Pnl_SideBar{
	JLabel lbTitle;
	
	Pnl_Setting(){
		btnSetting.setBackground(Main.colorTheme);
		
		lbTitle = new JLabel();
		lbTitle.setText("Setting");
		lbTitle.setFont(fontManager.CalibriBOLD50);
		pnl_North.add(lbTitle);
	}
}
