package goguma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PnlProfilePerson extends JPanel implements MouseListener, MouseMotionListener{
	JLabel lbIMG, lbLastMsg, lbName;
	ManagerFont fontManager = ManagerFont.getInstance();
	JLabel lbOnOff;
	Dialog_ActionFriend dialog_DelFriend;
	JPanel lbTLine, pnl_LastLine;
	
	String name = "";
	String connect = "";
	
	static int personNum = 0;
	int myNum, y;
	Point LocationPoint;
	PnlProfilePerson(){}
	
	PnlProfilePerson(String name, String connect) {
		this.name = name;
		this.connect = connect;
		
		this.setPreferredSize(new Dimension(610, 83));
		this.setBackground(new Color(200,200,200));
		this.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		lbIMG = new JLabel();
		lbIMG.setIcon(Function.lbImageSetSize("img/person.png", 60, 60));
		lbIMG.setPreferredSize(new Dimension(60, 60));
		lbIMG.setBackground(new Color(240,240,240));
		lbIMG.setOpaque(true);
		
		lbTLine = new JPanel();
		lbTLine.setLayout(new GridLayout(2,1));
		lbTLine.setBackground(new Color(200,200,200));
		
		lbName = new JLabel();
		lbName.setText(name);
		lbName.setFont(fontManager.CalibriBOLD30);
		
		pnl_LastLine = new JPanel();
		pnl_LastLine.setOpaque(false);
		
		lbOnOff = new JLabel();
		lbOnOff.setText(" ¡Ü ");
		lbOnOff.setFont(fontManager.CalibriPLAIN25);
		
		lbLastMsg = new JLabel();
		lbLastMsg.setFont(fontManager.CalibriPLAIN25);
		
		
		pnl_LastLine.add(lbOnOff);
		pnl_LastLine.add(lbLastMsg);
		lbTLine.add(lbName);
		lbTLine.add(pnl_LastLine);
		
		this.add(lbIMG);
		this.add(lbTLine);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		myNum = personNum;
		personNum++;
		
		setThemeOnOff(connect);
		}
	private void setThemeOnOff(String connect) {
		// TODO Auto-generated method stub
		System.out.println("dddd");
		if(connect.equals("true")){
			lbOnOff.setForeground(Color.green);
			lbLastMsg.setForeground(Color.blue);
			lbLastMsg.setText("OnLine");
			pnl_LastLine.setBackground(new Color(222,222,255));
			lbTLine.setBackground(new Color(222,222,255));
			this.setBackground(new Color(222,222,255));
		} else {
			lbOnOff.setForeground(Color.gray);
			lbLastMsg.setForeground(Color.gray);
			lbLastMsg.setText("OffLine");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(dialog_DelFriend==null){
			dialog_DelFriend = new Dialog_ActionFriend();
			dialog_DelFriend.txID_Check.setText(name);
			dialog_DelFriend.txID_Check.setFont(fontManager.GodicBOLD20);
			dialog_DelFriend.setVisible(true);
			dialog_DelFriend.setLocation(LocationPoint);
		} else{
			dialog_DelFriend.txID_Check.setText(name);
			dialog_DelFriend.txID_Check.setFont(fontManager.GodicBOLD20);
			dialog_DelFriend.setVisible(true);
		}
	
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		LocationPoint = e.getLocationOnScreen();
	
	}
}
