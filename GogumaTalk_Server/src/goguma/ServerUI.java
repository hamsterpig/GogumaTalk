package goguma;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;


public class ServerUI extends JFrame implements ActionListener{
	private final int SERVER_CLOSE = 0;
	private final int SERVER_PAUSE = 1;
	private final int SERVER_START = 2;
	private Dimension dimRes = new Dimension(1280,720);
	private JPanel pnlBase, pnlNorth, pnlNorthLeft, pnlNLState, pnlNLAdmin,pnlNorthRight, 
					pnlLeft, pnlLNorth, pnlLNNotice, pnlLNTime, pnlLRight,  pnlRight,
					pnlRNorth, pnlRNId, pnlRNIp;
	private JLabel lblServer, lblServerState, lblAdmin, lblNowAdmin,
					lblTime, lblNowTime, lblNotice,
					lblId, lblIp;
	private JTextField jfNotice, jfId, jfIp;
	private JButton btnStart, btnPause, btnClose,
					btnSend, btnTracking, btnKickback;
	private int w, h;
	private JScrollPane pnlLLeft, pnlLRNorth, pnlRSouth, pnlLRSouth;
	private Vector<String> roomColumn, userColumn, reportColumn, blackListColumn;
	private DefaultTableModel roomModel, userModel, reportModel, blackListModel;
	private Vector<String> roomRow, userRow, reportRow, blackListRow;
	private JTable roomTable, userTable, reportTable, blackListTable;
	private Thread clockTh;
	private ThreadClock clock;
	private int serverState;
	private String serverIP;
	//private Socket sock = null;
	//private BufferedReader br = null;
	//private PrintWriter pw = null;
	private SocketManager2 sock= null;
	ServerUI(){
		sock = SocketManager2.getInstance();
		this.setSize(dimRes);
		this.setResizable(false);
		this.setTitle("GogumaServer");
		serverState = SERVER_PAUSE;
		//NorthPanel
		pnlNorth = new JPanel();
		pnlNorth.setLayout(new FlowLayout(FlowLayout.RIGHT,100,0));
		pnlNorth.setPreferredSize(new Dimension(1230,50));
		
		pnlNorthLeft = new JPanel();
		pnlNorthLeft.setLayout(new FlowLayout(FlowLayout.CENTER,20,5));
		pnlNLState = new JPanel();
		lblServer = new JLabel("Server State");
		lblServerState = new JLabel("  ●");
		lblServerState.setForeground(Color.green);
		pnlNLState.add(lblServer);
		pnlNLState.add(lblServerState);
		
		pnlNLAdmin = new JPanel();
		lblAdmin = new JLabel("Admin : ");
		lblNowAdmin = new JLabel("gwanglim");
		pnlNLAdmin.add(lblAdmin);
		pnlNLAdmin.add(lblNowAdmin);
		
		pnlNorthLeft.add(pnlNLState);
		pnlNorthLeft.add(pnlNLAdmin);
				
		pnlNorthRight = new JPanel();
		pnlNorthRight.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
		btnStart = new JButton("Start");
		btnStart.setPreferredSize(new Dimension(70,40));
		btnStart.addActionListener(this);
		btnPause = new JButton("Pause");
		btnPause.setPreferredSize(new Dimension(70,40));
		btnPause.addActionListener(this);
		btnClose = new JButton("Close");
		btnClose.setPreferredSize(new Dimension(70,40));
		btnClose.addActionListener(this);
		
		pnlNorthRight.add(btnStart);
		pnlNorthRight.add(btnPause);
		pnlNorthRight.add(btnClose);
		
		pnlNorth.add(pnlNorthLeft);
		pnlNorth.add(pnlNorthRight);
		
		//pnlLeft
		pnlLeft = new JPanel();
		pnlLeft.setLayout(new FlowLayout(FlowLayout.LEADING,0,10));
		pnlLeft.setBackground(Color.gray);
		pnlLeft.setPreferredSize(new Dimension((dimRes.width/3)*2-50,(dimRes.height/13)*11));
		w = (dimRes.width/3)*2-50;
		h = (dimRes.height/13)*11;
		
		/////////pnlLeftNorth
		pnlLNorth = new JPanel();
		pnlLNorth.setPreferredSize(new Dimension(w-20, h/8));
		
		pnlLNTime = new JPanel();
		pnlLNTime.setLayout(new FlowLayout(FlowLayout.LEADING,10,0));
		pnlLNTime.setPreferredSize(new Dimension(w-20, 30));
		//pnlLNTime.setBackground(Color.gray);
		lblTime = new JLabel("Time : ");
		clock = new ThreadClock();
		clockTh = new Thread(clock,"Thread");
		clockTh.start();
		lblNowTime = clock.label1;
		
		pnlLNTime.add(lblTime);
		pnlLNTime.add(lblNowTime);
		
		pnlLNNotice = new JPanel();
		pnlLNNotice.setLayout(new FlowLayout(FlowLayout.LEADING,10,0));
		pnlLNNotice.setPreferredSize(new Dimension(w-20, 30));
		//pnlLNNotice.setBackground(Color.gray);
		lblNotice = new JLabel("FullMessage : ");
		jfNotice = new JTextField();
		jfNotice.setPreferredSize(new Dimension(200,30));
		btnSend = new JButton("Send");
		btnSend.addActionListener(this);
		pnlLNNotice.add(lblNotice);
		pnlLNNotice.add(jfNotice);
		pnlLNNotice.add(btnSend);
		
		pnlLNorth.add(pnlLNTime);
		pnlLNorth.add(pnlLNNotice);
		
		
		///////////////RoomTable
		roomColumn = new Vector<String>();
		roomColumn.addElement("RoomNumber");
		roomColumn.addElement("RoomName");
		roomColumn.addElement("PeopleNumber");
		
		
		roomModel = new DefaultTableModel(roomColumn,0);
		roomRow = new Vector<String>();
		roomRow.addElement("1");
		roomRow.addElement("PlayChat");
		roomRow.addElement("5/8");
		
		roomModel.addRow(roomRow);
		roomRow = new Vector<String>();
		roomRow.addElement("2");
		roomRow.addElement("We like the party");
		roomRow.addElement("3/8");
		roomModel.addRow(roomRow);
		
		roomTable = new JTable(roomModel) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				JComponent component = (JComponent) super.prepareRenderer(renderer, row, column);
				return component;
			}
		};
		roomTable.addMouseListener(new MouseAdapter() {
			//@Override
		});
		roomTable.setAlignmentX(CENTER_ALIGNMENT);
	
		JTableHeader header = roomTable.getTableHeader();
		header.setPreferredSize(new Dimension(0,40));
		
		pnlLLeft = new JScrollPane(roomTable);
		pnlLLeft.setPreferredSize(new Dimension((w/4)*3-50,h+2));
		
		///////////////UserTable
		userColumn = new Vector<String>();
		userColumn.addElement("Online User ID");
		userColumn.addElement("IP");
		
		userModel = new DefaultTableModel(userColumn,0);
		
		userTable = new JTable(userModel) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				JComponent component = (JComponent) super.prepareRenderer(renderer, row, column);
				return component;
			}
		};
		userTable.setAlignmentX(CENTER_ALIGNMENT);
	
		JTableHeader header2 = userTable.getTableHeader();
		header2.setPreferredSize(new Dimension(0,40));
		
		pnlLRNorth = new JScrollPane(userTable);
		pnlLRNorth.setPreferredSize(new Dimension((w/4)+35,(h/8)*4));
						
		/////////////BlackList
		blackListColumn = new Vector<String>();
		blackListColumn.addElement("Black List User ID");
		blackListColumn.addElement("IP");
		
		blackListModel = new DefaultTableModel(blackListColumn,0);
		blackListTable = new JTable(blackListModel){
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
				JComponent component = (JComponent)super.prepareRenderer(renderer, row, column);
				return component;
			}
		};
		blackListTable.setAlignmentX(CENTER_ALIGNMENT);
		
		JTableHeader blackListHeader = blackListTable.getTableHeader();
		blackListHeader.setPreferredSize(new Dimension(0,40));
		
		pnlLRSouth = new JScrollPane(blackListTable);
		pnlLRSouth.setPreferredSize(new Dimension((w/4)+35,(h/8)*3-15));
		
		
		pnlLRight = new JPanel();
		pnlLRight.setPreferredSize(new Dimension((w/4)+35,h));
		pnlLRight.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		pnlLRight.setBackground(Color.gray);
		pnlLRight.add(pnlLRNorth);
		pnlLRight.add(pnlLRSouth);
		
		pnlLeft.add(pnlLNorth);
		pnlLeft.add(pnlLLeft);
		pnlLeft.add(pnlLRight);
		
		//pnlRight
		pnlRight = new JPanel();
		pnlRight.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		pnlRight.setBackground(Color.gray);
		pnlRight.setPreferredSize(new Dimension((dimRes.width/3),(dimRes.height/13)*11));
		
		//pnlRNorth
		pnlRNorth = new JPanel();
		pnlRNorth.setLayout(new FlowLayout(FlowLayout.CENTER,80,15));
		pnlRNorth.setPreferredSize(new Dimension((dimRes.width/3),(dimRes.height/13)*4));
		pnlRNorth.setBorder(new TitledBorder(new LineBorder(Color.gray,2),"Tracking"));
		
		pnlRNId = new JPanel();
		lblId = new JLabel("ID : ");
		jfId = new JTextField(15);
		pnlRNId.add(lblId);
		pnlRNId.add(jfId);
		
		pnlRNIp = new JPanel();
		lblIp = new JLabel("IP : ");
		jfIp = new JTextField(15);
		jfIp.setEnabled(false);
		pnlRNIp.add(lblIp);
		pnlRNIp.add(jfIp);
		
		btnTracking = new JButton("Tracking");
		btnTracking.setPreferredSize(new Dimension(100,50));
		btnTracking.addActionListener(this);
		btnKickback = new JButton("KickBack");
		btnKickback.setPreferredSize(new Dimension(100,50));
		btnKickback.addActionListener(this);
		
		pnlRNorth.add(pnlRNId);
		pnlRNorth.add(pnlRNIp);
		pnlRNorth.add(btnTracking);
		pnlRNorth.add(btnKickback);
		
		///////////////reportTable
		reportColumn = new Vector<String>();
		reportColumn.addElement("UserName");
		reportColumn.addElement("ReportContents");
		
		reportModel = new DefaultTableModel(reportColumn,0);
		reportRow = new Vector<String>();
		reportRow.addElement("Goock");
		reportRow.addElement("Holly Jesus");				
		reportModel.addRow(reportRow);
		
		reportRow = new Vector<String>();
		reportRow.addElement("Zzin");
		reportRow.addElement("What is it");
		reportModel.addRow(reportRow);
		
		reportTable = new JTable(reportModel) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				JComponent component = (JComponent) super.prepareRenderer(renderer, row, column);
				return component;
			}
		};
		reportTable.setAlignmentX(CENTER_ALIGNMENT);
	
		JTableHeader header3 = reportTable.getTableHeader();
		header3.setPreferredSize(new Dimension(0,40));
		
		pnlRSouth = new JScrollPane(reportTable);
		pnlRSouth.setPreferredSize(new Dimension((dimRes.width/3),(dimRes.height/13)*7-20));
			
		
		pnlRight.add(pnlRNorth);
		pnlRight.add(pnlRSouth);
		
		pnlBase = new JPanel();
		pnlBase.setBackground(Color.gray);
		pnlBase.add(pnlNorth);
		pnlBase.add(pnlLeft);
		pnlBase.add(pnlRight);
		
		this.add(pnlBase);
		this.setVisible(true);
	}
	/*private DefaultTableModel DefaultTableModel(Vector<String> blackListColumn2, int i) {
		// TODO Auto-generated method stub
		return null;
	}*/
	public void insertUser(String id, String ip){
		userRow = new Vector<String>();
		userRow.addElement(id);
		userRow.addElement(ip);
		userModel.addRow(userRow);
	}
	public boolean existID(String str){
		if(userTable.getRowCount() != 0) {
			for(int i=0; i<userTable.getRowCount();i++) {
				if(str.equals((String)userTable.getValueAt(i, 0))) {
					return true;
				}
			}
		}
		return false;
	}
	public void tracking(String str) {
		if(userTable.getRowCount() != 0) {
			for(int i=0; i<userTable.getRowCount();i++) {
				if(str.equals((String)userTable.getValueAt(i, 0))) {
					jfIp.setText((String)userTable.getValueAt(i,1));
					break;
				}
			}
		}
	}
	public void kickBackUser(String str) {
		if(userTable.getRowCount() != 0) {
			for(int i=0; i<userTable.getRowCount();i++) {
				if(str.equals((String)userTable.getValueAt(i, 0))) {
					jfIp.setText(null);
					jfId.setText(null);
					userModel.removeRow(i);
					break;
				}
			}
			synchronized (sock) {
				try{
					PrintWriter pw = (PrintWriter) sock.get(str);
				
					pw.println("/quit");//"/alarm "+
					pw.println("서버로 부터 강퇴 되었습니다.^^");
					
					pw.flush();
					pw.close();
					sock.remove(str);
				}catch(NullPointerException e){			
				}
			}
		}
	}
	public void noticeMassage(String msg){
		synchronized (sock) {
			Collection<PrintWriter> collection = sock.values();
			Iterator<PrintWriter> iter = collection.iterator();
			while(iter.hasNext()){
				PrintWriter pw = iter.next();
				pw.println("/alarm "+msg);
				pw.flush();
			}			
		}
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			UIManager.setLookAndFeel
			("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Exception e){
			System.out.println("ERROR");
		}
		new ServerUI();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStart){
			lblServerState.setForeground(Color.green);
			serverState = SERVER_START;
		}else if(e.getSource() == btnPause){
			lblServerState.setForeground(Color.orange);
			serverState = SERVER_PAUSE;
		}else if(e.getSource() == btnClose){
			lblServerState.setForeground(Color.red);
			serverState = SERVER_CLOSE;
		}//////////Send
		else if(e.getSource() == btnSend) {
			if(userTable.getRowCount() != 0) {
				String str = jfNotice.getText();
				noticeMassage(str);
			}else {
				System.out.println("user가 없음");	
			}
		}/////////////Tracking
		else if(e.getSource() == btnTracking) {
			String str = jfId.getText();
			tracking(str);
		}////////////KickBack
		else if(e.getSource() == btnKickback){
			String str = jfId.getText();
			kickBackUser(str);		
		}
			
	}

}
