package goguma;

import java.awt.Color;

class ManagerColor{
	private static ManagerColor instance;
	
	Color kakao;
	Color kakao_btn;
	Color kakao_line;
	Color violet;
	Color violet_btn;
	Color violet_line;
	Color melon;
	Color melon_btn;
	Color melon_line;
	
	private ManagerColor(){
		kakao = new Color(255, 232, 18);
		kakao_btn = new Color(255, 232, 8);
		kakao_line = new Color(255, 232, 120);
		violet = new Color(111, 87, 108);
		violet_btn = new Color(121, 96, 120, 50);
		violet_line = new Color(191,152,168);
		melon = new Color(110,255,110);
		melon_btn = new Color(85,255,85);
		melon_line = new Color(170,255,170);
		
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