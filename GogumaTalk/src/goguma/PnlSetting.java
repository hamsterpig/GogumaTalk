package goguma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PnlSetting extends PnlSideBar{
	JLabel lbTitle;
	
	PnlSetting(){
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		
		btnSetting.setBackground(Main.colorTheme);
		
		lbTitle = new JLabel();
		lbTitle.setText("Setting");
		lbTitle.setFont(fontManager.CalibriBOLD50);
		pnl_North.add(lbTitle);
		
		pnl_c_c.setBackground(Color.LIGHT_GRAY);
		pnl_c_c.setPreferredSize(new Dimension(res.width/3,710));
		
		JPanel pnlAlarmLine = new JPanel();
		pnlAlarmLine.setBackground(Color.blue);
		pnlAlarmLine.setPreferredSize(new Dimension(res.width/3,60));
		pnl_c_c.add(pnlAlarmLine);
		
		
	}
}
