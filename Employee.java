package dev_tp2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class Employee implements Serializable{

	   public String name;
	   public String address;
	   public  int SSN;
	   public int number;
	   
	   void write(RandomAccessFile ref) throws IOException {

		   StringBuffer sb ;
		   
		   //write name 
		   if(name != null) {
			   sb = new StringBuffer(name);
			   ref.writeUTF(sb.toString());
		   }
		   
		   
		   
		   //write address
		   if(address != null) {
			   sb = new StringBuffer(address);
			   ref.writeUTF(sb.toString());
		   }
		   
		   if(SSN != 0) {
			   ref.writeInt(SSN);
		   }
		   
		   if(number != 0) {
			   ref.writeInt(number);
		   }
		
	   }

	   void read (RandomAccessFile ref) throws IOException {
		  

		   name = ref.readUTF();
		   
		   address = ref.readUTF();
		  
		   
		   SSN = ref.readInt();
		   number = ref.readInt();
		   
	   }
}
