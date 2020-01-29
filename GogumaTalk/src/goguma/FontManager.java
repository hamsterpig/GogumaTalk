package goguma;

import java.awt.Font;

public class FontManager{
	private static FontManager instance;
	
	Font CalibriPLAIN15, CalibriPLAIN20, CalibriPLAIN25;
	Font CalibriPLAIN30;
	Font CalibriPLAIN35;
	Font CalibriPLAIN50;
	Font CalibriBOLD15, CalibriBOLD20, CalibriBOLD25,
	CalibriBOLD30, CalibriBOLD35, CalibriBOLD50;
	Font CalibriUnder18;
	Font GodicBOLD15, GodicBOLD20, GodicBOLD25, GodicBOLD30, GodicBOLD35, GodicBOLD40;
	
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
		
		GodicBOLD15 = new Font("¸¼Àº °íµñ", Font.BOLD, 15);
		GodicBOLD20 = new Font("¸¼Àº °íµñ", Font.BOLD, 20);
		GodicBOLD25 = new Font("¸¼Àº °íµñ", Font.BOLD, 25);
		GodicBOLD30 = new Font("¸¼Àº °íµñ", Font.PLAIN, 30);
		GodicBOLD35 = new Font("¸¼Àº °íµñ", Font.PLAIN, 35);
		GodicBOLD40 = new Font("¸¼Àº °íµñ", Font.PLAIN, 40);
		
	}
	
	public static FontManager getInstance(){
		if(instance == null){
			instance = new FontManager();
		}
		return instance;
	}
}
