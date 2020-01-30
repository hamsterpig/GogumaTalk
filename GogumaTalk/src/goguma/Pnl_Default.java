package goguma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Pnl_Default extends JPanel{
	JPanel pnl_North, pnl_South, pnl_Center, pnl_West, pnl_East;
	JPanel pnl_c_n, pnl_c_s, pnl_c_c;
	
	FontManager fontManager =FontManager.getInstance();
	ColorManager colorManager =ColorManager.getInstance();

	Pnl_Default(){
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLayout(new BorderLayout());
		pnl_North = new JPanel();
		pnl_South = new JPanel();
		pnl_Center = new JPanel(new BorderLayout());
		//pnl_West = new JPanel();
		//pnl_East = new JPanel();
		
		pnl_c_n = new JPanel();
		pnl_c_s = new JPanel();
		pnl_c_c = new JPanel();
		
		this.add(pnl_North, BorderLayout.NORTH);
		this.add(pnl_South, BorderLayout.SOUTH);
		this.add(pnl_Center, BorderLayout.CENTER);
		//this.add(pnl_West, BorderLayout.WEST);
		//this.add(pnl_East, BorderLayout.EAST);
		
		pnl_Center.add(pnl_c_n, BorderLayout.NORTH);
		pnl_Center.add(pnl_c_s, BorderLayout.SOUTH);
		pnl_Center.add(pnl_c_c, BorderLayout.CENTER);
		
		setTheme(Main.colorTheme); // Default Thema
	}
	
	private void setTheme(Color c) { // TODO Auto-generated method stub
		this.setBackground(c);
		
		pnl_North.setBackground(c);
		pnl_South.setBackground(c);
		pnl_Center.setBackground(c);
		//pnl_West.setBackground(c);
		//pnl_East.setBackground(c);
		
		pnl_c_n.setBackground(c);
		pnl_c_s.setBackground(c);
		pnl_c_c.setBackground(c);


	}
}
