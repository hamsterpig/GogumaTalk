package goguma;

import java.awt.Color;

class ColorManager{
	private static ColorManager instance;
	
	Color kakao;
	Color violet;
	Color violet_btn;
	
	private ColorManager(){
		kakao = new Color(255, 232, 18);
		violet = new Color(111, 87, 108);
		violet_btn = new Color(121, 96, 120, 50);
		
	}
	
/*	public static void setColor(Color c){
		violet = c;
	}*/
	
	public static ColorManager getInstance(){
		if(instance == null){
			instance = new ColorManager();
		}
		return instance;
	}
}