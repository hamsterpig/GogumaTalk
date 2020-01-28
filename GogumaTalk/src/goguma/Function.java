package goguma;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Function {
	
	static ImageIcon imageSetSize(ImageIcon icon, int i, int j) { // image Size Setting
		Image ximg = icon.getImage();  //ImageIcon�� Image�� ��ȯ.
		Image yimg = ximg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg); 
		return xyimg;
	}
	
	static ImageIcon lbImageSetSize(String imgLocation, int i, int j) { // image Size Setting
		ImageIcon icon = new ImageIcon(imgLocation);
		Image ximg = icon.getImage();  //ImageIcon�� Image�� ��ȯ.
		Image yimg = ximg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg); 
		return xyimg;
	}
	

}
