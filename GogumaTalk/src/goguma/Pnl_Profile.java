package goguma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Pnl_Profile extends Pnl_SideBar implements ActionListener {

	JScrollPane scList;
	int fCNT = 0;

	Pnl_Profile() {
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		// Frame Default Size
		this.setPreferredSize(new Dimension(res.width / 3, res.height));
		this.setBackground(Color.yellow);

		pnl_North.setPreferredSize(new Dimension(res.width / 3, 175));
		pnl_North.setLayout(new FlowLayout());
		pnl_North.setBackground(new Color(100, 100, 255));

		lbSpace = new JLabel();
		lbSpace.setPreferredSize(new Dimension(430, 80));
		lbSpace.setBackground(Color.yellow);

		pnlMenu = new JPanel(new FlowLayout(FlowLayout.LEADING));
		pnlMenu.setPreferredSize(new Dimension(res.width / 3, 80));
		pnlMenu.setBackground(Color.green);
		pnlMenu.setOpaque(true);

		btnsearch = new JButton();
		btnsearch.setIcon(Function.lbImageSetSize("src/img/search.png", 60, 60));
		btnsearch.setPreferredSize(new Dimension(60, 60));

		btnaddPerson = new JButton();
		btnaddPerson.setIcon(Function.lbImageSetSize("src/img/addPerson.png",
				60, 60));
		btnaddPerson.setPreferredSize(new Dimension(60, 60));

		btnLogout = new JButton();
		btnLogout
				.setIcon(Function.lbImageSetSize("src/img/logout.png", 60, 60));
		btnLogout.setPreferredSize(new Dimension(60, 60));

		pnlMenu.setAlignmentY(pnlMenu.CENTER_ALIGNMENT);
		pnlMenu.add(lbSpace);
		pnlMenu.add(btnsearch);
		pnlMenu.add(btnaddPerson);
		pnlMenu.add(btnLogout);
		// pnlMenu.add(btnSetting);

		pnl_North.add(pnlMenu);

		lbProfile = new JLabel("Name");
		lbProfileImg = new JLabel("IMG");
		lbProfileImg.setIcon(Function.lbImageSetSize("src/img/person.png", 75,
				75));
		lbProfileImg.setPreferredSize(new Dimension(70, 70));
		lbProfile.setFont(fontManager.CalibriBOLD50);
		pnlProfileLine = new JPanel();
		pnlProfileLine.setPreferredSize(new Dimension(res.width / 3, 80));
		pnlProfileLine.setBackground(Color.blue);
		pnlProfileLine.setOpaque(true);

		pnlProfileLine.add(lbProfileImg);
		pnlProfileLine.add(lbProfile);
		pnlProfileLine.setLayout(new FlowLayout(FlowLayout.LEADING));
		pnl_North.add(pnlProfileLine);

		pnl_Center.setBackground(Color.white);
		pnl_Center.setOpaque(true);
		pnlList = new JPanel();
		pnlList.setBackground(Color.white);
		scList = new JScrollPane();
		scList.setViewportView(pnlList);
		scList.getViewport().setBackground(Color.white);
		if(fCNT == 0){
			JLabel lbTemp = new JLabel("Please Add Friends");
			lbTemp.setFont(fontManager.CalibriPLAIN35);
			pnlList.add(lbTemp);
			System.out.println("Please Add Friends");
		}

		// scList.setPreferredSize(new Dimension(res.width/3, 500));

		pnl_Center.add(scList);
		setTheme(Main.colorTheme);
		
		
		//add Panel
		
		
		btnLogout.addActionListener(this);

	} // new 

	private void setTheme(Color c) { // TODO Auto-generated method stub
		this.setBackground(c);

		lbSpace.setBackground(c);
		pnlMenu.setBackground(c);
		pnlProfileLine.setBackground(c);
		btnProfile.setBackground(c);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	
}
