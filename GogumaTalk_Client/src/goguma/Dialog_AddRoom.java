package goguma;

import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

public class Dialog_AddRoom extends JDialog implements ItemListener,
		ActionListener {
	// /방 추가버튼 누르면 뜨는 dialog // 방정보 입력해서 방 생성
	JPanel pnlBase, pnlTitle, pnlImage, pnlPassWord, pnlContents, pnlMaxNum,
			pnlBtn;
	JLabel lblTitle, lblImage, lblPassWord, lblContents, lblMaxNum;
	JTextField tfTitle, tfPassWord, tfContents;
	JButton btnAddRoom, btnimg1, btnimg2, btnimg3, btnimg4, btnimg5, btnimg6,
			btnimg7;
	JSlider slidMaxNum;
	JCheckBox cbPassWord;
	String btnimg=null, master = "";
	int INIT_VAL = 2, arrayMax = 6, curnum = 0;
	boolean pwCheck = false;
	PnlMultiChat multichat;

	Dialog_AddRoom(PnlMultiChat mtc, String userId) {
		this.setSize(350, 380);
		this.setLocationRelativeTo(null);
		master = userId;
		multichat = mtc;

		lblTitle = new JLabel("방 제목");
		lblImage = new JLabel("이미지");
		lblPassWord = new JLabel("비밀번호");
		lblContents = new JLabel("#해시태그를 이용해서 채팅방을 소개해 보세요.");
		lblMaxNum = new JLabel("인원 수");

		tfTitle = new JTextField(23);
		tfPassWord = new JTextField(20);
		tfPassWord.setVisible(false);
		tfContents = new JTextField(27);

		btnAddRoom = new JButton("방 생성");
		btnAddRoom.addActionListener(this);
		btnimg1 = new JButton("1");
		btnimg1.addActionListener(this);
		btnimg2 = new JButton("2");
		btnimg2.addActionListener(this);
		btnimg3 = new JButton("3");
		btnimg3.addActionListener(this);
		btnimg4 = new JButton("4");
		btnimg4.addActionListener(this);
		btnimg5 = new JButton("5");
		btnimg5.addActionListener(this);
		btnimg6 = new JButton("6");
		btnimg6.addActionListener(this);
		btnimg7 = new JButton("7");
		btnimg7.addActionListener(this);

		slidMaxNum = new JSlider(2, 6, INIT_VAL);
		slidMaxNum.setMajorTickSpacing(1);
		slidMaxNum.setMinorTickSpacing(1);
		slidMaxNum.setPaintTicks(true);
		slidMaxNum.setPaintLabels(true);

		cbPassWord = new JCheckBox("");
		cbPassWord.addItemListener(this);

		pnlTitle = new JPanel();

		pnlTitle.add(lblTitle);
		pnlTitle.add(tfTitle);
		pnlImage = new JPanel();
		pnlImage.add(lblImage);
		pnlImage.add(btnimg1);
		pnlImage.add(btnimg2);
		pnlImage.add(btnimg3);
		pnlImage.add(btnimg4);
		pnlImage.add(btnimg5);
		pnlImage.add(btnimg6);
		pnlImage.add(btnimg7);
		pnlPassWord = new JPanel();
		pnlPassWord.add(lblPassWord);
		pnlPassWord.add(cbPassWord);
		pnlPassWord.add(tfPassWord);
		pnlContents = new JPanel();
		pnlContents.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 0));
		pnlContents.add(lblContents);
		// pnlContents.add(tfContents);
		pnlMaxNum = new JPanel();
		pnlMaxNum.add(lblMaxNum);
		pnlMaxNum.add(slidMaxNum);
		pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 125, 25));
		pnlBtn.add(btnAddRoom);

		pnlBase = new JPanel();
		pnlBase.setLayout(new FlowLayout(FlowLayout.LEADING));
		pnlBase.add(pnlTitle);
		pnlBase.add(pnlImage);
		pnlBase.add(pnlPassWord);
		pnlBase.add(pnlContents);
		pnlBase.add(tfContents);
		pnlBase.add(pnlMaxNum);
		pnlBase.add(pnlBtn);

		add(pnlBase, "Center");

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getItemSelectable() == cbPassWord) {

			if (pwCheck) {
				pwCheck = false;
			} else {
				pwCheck = true;
			}
			tfPassWord.setVisible(pwCheck);
			pnlPassWord.revalidate();
			pnlPassWord.repaint();

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnimg1) {
			btnimg = "img/img3.png";
		} else if (e.getSource() == btnimg2) {
			btnimg = "img/img10.png";
		} else if (e.getSource() == btnimg3) {
			btnimg = "img/img8.png";
		} else if (e.getSource() == btnimg4) {
			btnimg = "img/img7.png";
		} else if (e.getSource() == btnimg5) {
			btnimg = "img/img9.png";
		} else if (e.getSource() == btnimg6) {
			btnimg = "img/img11.png";
		} else if (e.getSource() == btnimg7) {
			btnimg = "img/img12.png";
		}

		if (e.getSource() == btnAddRoom) {

			String pw = "null";
			String hashtag = tfContents.getText();
			if (pwCheck) {
				pw = tfPassWord.getText();
			} else {
				pw = "null";
			}
			if(tfContents.getText().equals("")){
				hashtag = "null";				
			}
			Main.soket.toServ.println("/make/room㈛" + tfTitle.getText()+"㈛"+btnimg+"㈛"+ pw +"㈛" + slidMaxNum.getValue()+ "㈛"+hashtag);
			Main.soket.toServ.flush();
			this.setVisible(false);
		}

	}
}
