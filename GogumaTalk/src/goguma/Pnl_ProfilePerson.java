package goguma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pnl_ProfilePerson extends JPanel implements MouseListener{
	JLabel lbIMG, lbLastMsg, lbName;
	FontManager fontManager = FontManager.getInstance();
	JLabel lbOnOff;
	Dialog_ActionFriend dialog_DelFriend;
	
	String name = "";
	
	static int personNum = 0;
	int myNum;
	Pnl_ProfilePerson(){}
	
	Pnl_ProfilePerson(String name, String connect) {
		this.name = name;
		
		dialog_DelFriend = new Dialog_ActionFriend();

		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setPreferredSize(new Dimension(610, 83));
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
		lbName.setText(name);
		lbName.setFont(fontManager.CalibriBOLD30);
		
		JPanel pnl_LastLine = new JPanel();
		pnl_LastLine.setOpaque(false);
		
		lbOnOff = new JLabel();
		lbOnOff.setText(" ¡Ü ");
		lbOnOff.setFont(fontManager.CalibriPLAIN25);
		
		lbLastMsg = new JLabel();
		lbLastMsg.setFont(fontManager.CalibriPLAIN25);
		
		
		if(connect.equals("true")){
			lbOnOff.setForeground(Color.green);
			lbLastMsg.setForeground(Color.blue);
			lbLastMsg.setText("OnLine");
		} else {
			lbOnOff.setForeground(Color.gray);
			lbLastMsg.setForeground(Color.gray);
			lbLastMsg.setText("OffLine");
		}
		
		
		pnl_LastLine.add(lbOnOff);
		pnl_LastLine.add(lbLastMsg);
		lbTLine.add(lbName);
		lbTLine.add(pnl_LastLine);
		
		this.add(lbIMG);
		this.add(lbTLine);
		this.addMouseListener(this);
		
		myNum = personNum;
		personNum++;
		
		
		}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(dialog_DelFriend==null){
			dialog_DelFriend = new Dialog_ActionFriend();
			dialog_DelFriend.txID_Check.setText(name);
			dialog_DelFriend.txID_Check.setFont(fontManager.GodicBOLD20);
			dialog_DelFriend.setVisible(true);
		} else{
			dialog_DelFriend.txID_Check.setText(name);
			dialog_DelFriend.txID_Check.setFont(fontManager.GodicBOLD20);
			dialog_DelFriend.setVisible(true);
		}
	
		
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
