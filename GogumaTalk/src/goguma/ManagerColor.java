package goguma;

import java.awt.Color;

class ManagerColor{
	private static ManagerColor instance;
	
	Color kakao;
	Color violet;
	Color violet_btn;
	
	private ManagerColor(){
		kakao = new Color(255, 232, 18);
		violet = new Color(111, 87, 108);
		violet_btn = new Color(121, 96, 120, 50);
		
	}
	
/*	public static void setColor(Color c){
		violet = c;
	}*/
	
	public static ManagerColor getInstance(){
		if(instance == null){
			instance = new ManagerColor();
		}
		return instance;
	}
}