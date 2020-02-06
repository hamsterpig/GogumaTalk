package goguma;

import java.awt.Checkbox;
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

public class PnlSetting extends PnlSideBar implements ActionListener{
	JLabel lbTitle;
	JLabel lbAlarm, lbTheme, lbAnn;
	JButton btnCreatChat, btnMessage, btnFriendState;
	JButton btnGoguma, btnKakao, btnMelon;
	JButton btnView;
	boolean isCreatChat, isMessage, isFriendState;
	boolean isGoguma, isKakao, isMelon;
	
	Dialog_ViewAnn dialog_ViewAnn;
	
	PnlSetting(){
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		
		btnSetting.setBackground(Main.colorTheme);
		
		lbTitle = new JLabel();
		lbTitle.setText("Setting");
		lbTitle.setFont(fontManager.CalibriBOLD50);
		pnl_North.add(lbTitle);
		
		pnl_c_c.setBackground(Color.LIGHT_GRAY);
		pnl_c_c.setPreferredSize(new Dimension(640,710));
		
		JPanel pnlAlarmLine = new JPanel(new FlowLayout(FlowLayout.LEADING));
		pnlAlarmLine.setBackground(Color.white);
		pnlAlarmLine.setPreferredSize(new Dimension(640,160));
		pnl_c_c.add(pnlAlarmLine);
		
		JPanel pnlAlarm = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlAlarm.setBackground(Color.white);
		lbAlarm = new JLabel("Alarm");
		lbAlarm.setPreferredSize(new Dimension(630,60));
		lbAlarm.setFont(fontManager.GodicBOLD30);
		lbAlarm.setHorizontalAlignment(lbAlarm.CENTER);
		pnlAlarm.add(lbAlarm);
		
		JPanel pnlAlarmOption = new JPanel();
		pnlAlarmOption.setBackground(new Color(230,230,255));
		JPanel pnlCreatChat = new JPanel();
		pnlCreatChat.setBackground(Color.white);
		JLabel lbCreatChat = new JLabel("Creat Chat");
		lbCreatChat.setFont(fontManager.GodicBOLD25);
		pnlAlarmOption.add(lbCreatChat);
		btnCreatChat = new JButton("O");
		btnCreatChat.setPreferredSize(new Dimension(55,55));
		btnCreatChat.setBackground(new Color(200,255,200));
		btnCreatChat.setFont(fontManager.CalibriBOLD30);
		btnCreatChat.addActionListener(this);
		pnlAlarmOption.add(btnCreatChat);
		pnlCreatChat.add(pnlAlarmOption);
		
		JPanel pnlAlarmOption2 = new JPanel();
		pnlAlarmOption2.setBackground(new Color(230,230,255));
		JPanel pnlMessage = new JPanel();
		pnlMessage.setBackground(Color.white);
		JLabel lbMessage = new JLabel("Message");
		lbMessage.setFont(fontManager.GodicBOLD25);
		pnlAlarmOption2.add(lbMessage);
		btnMessage = new JButton("O");
		btnMessage.setPreferredSize(new Dimension(55,55));
		btnMessage.setBackground(new Color(200,255,200));
		btnMessage.setFont(fontManager.CalibriBOLD30);
		btnMessage.addActionListener(this);
		pnlAlarmOption2.add(btnMessage);
		pnlMessage.add(pnlAlarmOption2);
		
		JPanel pnlAlarmOption3 = new JPanel();
		pnlAlarmOption3.setBackground(new Color(230,230,255));
		JPanel pnlFriendState = new JPanel();
		pnlFriendState.setBackground(Color.white);
		JLabel lbFriendState = new JLabel("Friend State");
		lbFriendState.setFont(fontManager.GodicBOLD25);
		pnlAlarmOption3.add(lbFriendState);
		btnFriendState = new JButton("O");
		btnFriendState.setPreferredSize(new Dimension(55,55));
		btnFriendState.setBackground(new Color(200,255,200));
		btnFriendState.setFont(fontManager.CalibriBOLD30);
		btnFriendState.addActionListener(this);
		pnlAlarmOption3.add(btnFriendState);
		pnlFriendState.add(pnlAlarmOption3);
		
		JPanel pnlAlarmOptionAll = new JPanel(new GridLayout(1,3));
		pnlAlarmOptionAll.setBackground(Color.white);
		pnlAlarmOptionAll.setPreferredSize(new Dimension(630,120));
		pnlAlarmOptionAll.add(pnlCreatChat);
		pnlAlarmOptionAll.add(pnlMessage);
		pnlAlarmOptionAll.add(pnlFriendState);
		pnlAlarmLine.add(pnlAlarm);
		pnlAlarmLine.add(pnlAlarmOptionAll);
		// end Alarm
		
		JPanel pnlThemeLine = new JPanel(new FlowLayout(FlowLayout.LEADING));
		pnlThemeLine.setBackground(Color.white);
		pnlThemeLine.setPreferredSize(new Dimension(640,160));
		pnl_c_c.add(pnlThemeLine);
		
		JPanel pnlTheme = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlTheme.setBackground(Color.white);
		lbTheme = new JLabel("Theme");
		lbTheme.setPreferredSize(new Dimension(630,60));
		lbTheme.setFont(fontManager.GodicBOLD30);
		lbTheme.setHorizontalAlignment(lbAlarm.CENTER);
		pnlTheme.add(lbTheme);
		
		JPanel pnlThemeOption = new JPanel();
		pnlThemeOption.setBackground(new Color(230,230,255));
		JPanel pnlGoguma = new JPanel();
		pnlGoguma.setBackground(Color.white);
		JLabel lbGoguma = new JLabel("Goguma");
		lbGoguma.setFont(fontManager.GodicBOLD25);
		pnlThemeOption.add(lbGoguma);
		btnGoguma = new JButton("O");
		btnGoguma.setPreferredSize(new Dimension(55,55));
		btnGoguma.setBackground(new Color(200,255,200));
		btnGoguma.setFont(fontManager.CalibriBOLD30);
		btnGoguma.addActionListener(this);
		pnlThemeOption.add(btnGoguma);
		pnlGoguma.add(pnlThemeOption);
		
		JPanel pnlThemeOption2 = new JPanel();
		pnlThemeOption2.setBackground(new Color(230,230,255));
		JPanel pnlKakao = new JPanel();
		pnlKakao.setBackground(Color.white);
		JLabel lbKakao = new JLabel("Kakao");
		lbKakao.setFont(fontManager.GodicBOLD25);
		pnlThemeOption2.add(lbKakao);
		btnKakao = new JButton("O");
		btnKakao.setPreferredSize(new Dimension(55,55));
		btnKakao.setBackground(new Color(200,255,200));
		btnKakao.setFont(fontManager.CalibriBOLD30);
		btnKakao.addActionListener(this);
		pnlThemeOption2.add(btnKakao);
		pnlKakao.add(pnlThemeOption2);
		
		JPanel pnlThemeOption3 = new JPanel();
		pnlThemeOption3.setBackground(new Color(230,230,255));
		JPanel pnlMelon = new JPanel();
		pnlMelon.setBackground(Color.white);
		JLabel lbMelon = new JLabel("Melon");
		lbMelon.setFont(fontManager.GodicBOLD25);
		pnlThemeOption3.add(lbMelon);
		btnMelon = new JButton("O");
		btnMelon.setPreferredSize(new Dimension(55,55));
		btnMelon.setBackground(new Color(200,255,200));
		btnMelon.setFont(fontManager.CalibriBOLD30);
		btnMelon.addActionListener(this);
		pnlThemeOption3.add(btnMelon);
		pnlMelon.add(pnlThemeOption3);
		
		JPanel pnlThemeOptionAll = new JPanel(new GridLayout(1,3));
		pnlThemeOptionAll.setPreferredSize(new Dimension(630,120));
		pnlThemeOptionAll.setBackground(Color.white);
		pnlThemeOptionAll.add(pnlGoguma);
		pnlThemeOptionAll.add(pnlKakao);
		pnlThemeOptionAll.add(pnlMelon);
		pnlThemeLine.add(pnlTheme);
		pnlThemeLine.add(pnlThemeOptionAll);

		// end Theme
		
		JPanel pnlAnnLine = new JPanel(new FlowLayout(FlowLayout.LEADING));
		pnlAnnLine.setBackground(Color.white);
		pnlAnnLine.setPreferredSize(new Dimension(640,135));
		pnl_c_c.add(pnlAnnLine);
		
		JPanel pnlAnn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlAnn.setBackground(Color.white);
		lbAnn = new JLabel("View past messages");
		lbAnn.setPreferredSize(new Dimension(630,60));
		lbAnn.setFont(fontManager.GodicBOLD30);
		lbAnn.setHorizontalAlignment(lbAnn.CENTER);
		pnlAnn.add(lbAnn);
		pnlAnnLine.add(pnlAnn);
		
		JPanel pnlViewLine = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlViewLine.setPreferredSize(new Dimension(630,50));
		pnlViewLine.setBackground(Color.white);
		btnView = new JButton("View");
		btnView.setPreferredSize(new Dimension(600,40));
		btnView.setBackground(new Color(255,200,200));
		btnView.addActionListener(this);
		pnlViewLine.add(btnView);
		pnlAnnLine.add(pnlViewLine);
		
		
		
		setOption();
		
	}

	private void setOption() {
		// Alarm
		isCreatChat = true;
		isMessage = true;
		isFriendState = true;
		
		// Theme
		isGoguma = false;
		isKakao = true;
		isMelon = false;
		
		btnGoguma.setText("X");
		btnGoguma.setBackground(Color.gray);
		btnKakao.setText("O");
		btnKakao.setBackground(new Color(200,255,200));
		btnMelon.setText("X");
		btnMelon.setBackground(Color.gray);
		
		// View
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnCreatChat){
			
			btnAlarmClick(btnCreatChat);
		} else if(e.getSource() == btnMessage){
			btnAlarmClick(btnMessage);
		} else if(e.getSource() == btnFriendState){
			btnAlarmClick(btnFriendState);
		} else if(e.getSource() == btnGoguma){
			btnThemeClick(btnGoguma);
		} else if(e.getSource() == btnKakao){
			btnThemeClick(btnKakao);
		} else if(e.getSource() == btnMelon){
			btnThemeClick(btnMelon);
		} else if(e.getSource() == btnView){
			if(dialog_ViewAnn==null){
				dialog_ViewAnn = new Dialog_ViewAnn();
				dialog_ViewAnn.setVisible(true);
			} else{
				dialog_ViewAnn.setVisible(true);
			}
		}
	}

	private void btnThemeClick(JButton btn) {
		// TODO Auto-generated method stub
		
		if(btn.getText().equals("O")){

		} else if(btn.getText().equals("X")){
			btnGoguma.setText("X");
			btnGoguma.setBackground(Color.gray);
			btnKakao.setText("X");
			btnKakao.setBackground(Color.gray);
			btnMelon.setText("X");
			btnMelon.setBackground(Color.gray);
		
			btn.setText("O");
			btn.setBackground(new Color(200,255,200));
		}
	}

	private void btnAlarmClick(JButton btn) {
		// TODO Auto-generated method stub
		if(btn.getText().equals("O")){
			btn.setText("X");
			btn.setBackground(Color.gray);
		} else if(btn.getText().equals("X")){
			btn.setText("O");
			btn.setBackground(new Color(200,255,200));
		}
	}
}
