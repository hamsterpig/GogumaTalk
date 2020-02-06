package goguma;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class DataFormat implements Serializable{
	private String fileName;
    private ImageIcon img;    

    public DataFormat(String fileName, ImageIcon img) {
        this.fileName = fileName;
        this.img = img;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setImg(ImageIcon img) {
        this.img = img;
    }
    public String getFileName() {
        return fileName;
    }
    public ImageIcon getImg() {
        return img;
    }
}
