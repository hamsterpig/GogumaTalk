package goguma;

import java.io.Serializable;
import java.util.*;

public class Unit implements Serializable{
	//serialVersionUID는 통신 하는 자바 시스템간 동일해야 한다.
	private static final long serialVersionUID = 1L;
	
	private int size;
	private String code;
	private ArrayList data;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ArrayList getData() {
		return data;
	}
	public void setData(ArrayList data) {
		this.data = data;
	}
	
}
