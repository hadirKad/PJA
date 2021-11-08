package dev_tp2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;


public class Class2 {

	public static void main(String[] args) throws ClassNotFoundException {
		Employee em = null;
		try {
			
			FileInputStream fileIn = new FileInputStream("emp.dat");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	            
	        RandomAccessFile raf = new RandomAccessFile("empdirect.dat", "rw");
	
	   
	        raf.writeInt(in.readInt());
	        while((em = (Employee) in.readObject()) instanceof Employee == true ) {
	        	System.out.println(em.name);
	        	System.out.println(em.address);
	        	System.out.println(em.SSN);
	        	System.out.println(em.number);
	        	em.write(raf);
	        }
	
	        System.out.printf("donnees  sauvegardees dans empdirect.dat");
	        fileIn.close();
	        in.close();
	        raf.close();
	        
		}
		catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

	

	}

}