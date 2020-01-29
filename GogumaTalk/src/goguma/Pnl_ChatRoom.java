package goguma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pnl_ChatRoom extends JPanel implements MouseListener{
	JLabel lbIMG, lbLastMsg, lbName;
	FontManager fontManager = FontManager.getInstance();
	
	static int roomNum = 0;
	int myNum;
	
	Pnl_ChatRoom(){
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setPreferredSize(new Dimension(res.width/3, 70));
		this.setBackground(new Color(200,200,200));
		this.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		lbIMG = new JLabel();
		lbIMG.setIcon(Function.lbImageSetSize("img/person.png", 60, 60));
		lbIMG.setPreferredSize(new Dimension(60, 60));
		lbIMG.setBackground(new Color(240,240,240));
		lbIMG.setOpaque(true);
		
		JPanel lbTLine = new JPanel();
		lbTLine.setLayout(new GridLayout(2,1));
		lbTLine.setBackground(new Color(200,200,200));
		
		lbName = new JLabel();
		lbName.setText("Name");
		lbName.setFont(fontManager.CalibriBOLD30);
		
		lbLastMsg = new JLabel();
		lbLastMsg.setText("No conversation content.");
		lbLastMsg.setFont(fontManager.CalibriPLAIN25);
		
		lbTLine.add(lbName);
		lbTLine.add(lbLastMsg);
		
		this.add(lbIMG);
		this.add(lbTLine);
		this.addMouseListener(this);
		
		myNum = roomNum;
		roomNum++;
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(myNum);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
