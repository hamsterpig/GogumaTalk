package goguma;

import java.awt.Font;

public class FontManager{
	private static FontManager instance;
	
	static Font CalibriPLAIN15, CalibriPLAIN20, CalibriPLAIN25;

	static Font CalibriPLAIN30;

	static Font CalibriPLAIN35;

	static Font CalibriPLAIN50;
	
	static Font CalibriBOLD15, CalibriBOLD20, CalibriBOLD25,
	CalibriBOLD30, CalibriBOLD35, CalibriBOLD50;
	
	static Font CalibriUnder18;
	
	private FontManager(){
		CalibriPLAIN15 = new Font("Calibri", Font.PLAIN, 15);
		CalibriPLAIN20 = new Font("Calibri", Font.PLAIN, 20);
		CalibriPLAIN25 = new Font("Calibri", Font.PLAIN, 25);
		CalibriPLAIN30 = new Font("Calibri", Font.PLAIN, 30);
		CalibriPLAIN35 = new Font("Calibri", Font.PLAIN, 35);
		CalibriPLAIN50 = new Font("Calibri", Font.PLAIN, 50);
		
		CalibriBOLD15 = new Font("Calibri", Font.BOLD, 15);
		CalibriBOLD20 = new Font("Calibri", Font.BOLD, 20);
		CalibriBOLD25 = new Font("Calibri", Font.BOLD, 25);
		CalibriBOLD30 = new Font("Calibri", Font.BOLD, 30);
		CalibriBOLD35 = new Font("Calibri", Font.BOLD, 35);
		CalibriBOLD50 = new Font("Calibri", Font.BOLD, 50);
		
		CalibriUnder18 = new Font("Calibri", Font.ITALIC, 18);
		
	}
	
	public static FontManager getInstance(){
		if(instance == null){
			instance = new FontManager();
		}
		return instance;
	}
}
