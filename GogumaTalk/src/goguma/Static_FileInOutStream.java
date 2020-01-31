package goguma;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Static_FileInOutStream {
	
	static String fileRead(String fileLocation){
		String temp = "";
		File file = new File(fileLocation); //date
		try{
			if(!file.exists()){ // file not
				FileWriter check = new FileWriter(fileLocation); // new creat file
			} else {
				FileReader reader = new FileReader(fileLocation); // file Open
				BufferedReader in = new BufferedReader(reader);
				Scanner scan = new Scanner(reader);

					String string;
				    while ((string = in.readLine()) != null) {
				    	temp = temp.concat(string+"\n");
				      }
				}
		} catch(IOException e){
			
		}
		return temp;
	}
	
	static void fileWrite_Concat(String fileLocation, String addDB){

		try {
			String temp = fileRead(fileLocation);
			temp = temp.concat(addDB);
			FileWriter write3 = new FileWriter(fileLocation);
			write3.write(temp);
			write3.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void fileWrite(String fileLocation, String data){

		try {
			FileWriter write3 = new FileWriter(fileLocation);
			write3.write(data);
			write3.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static int fileWrite_PK(String fileLocation){
		int tempInt = 0;
		String tempString = "0";
		Scanner scan;
		
		try {
			try{
				scan = new Scanner(fileRead(fileLocation));
				tempInt = (Integer.parseInt(scan.next())+1);
			} catch(Exception e){
				tempInt = 0;
			}
			
			FileWriter write3 = new FileWriter(fileLocation);
			tempString = Integer.toString(tempInt);
			write3.write(tempString);
			write3.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tempInt;
	}

	
	

}