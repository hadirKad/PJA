package dev_tp2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Class1 {

	private static Scanner lire;

	public static void main(String[] args) throws IOException {
		
		lire = new Scanner(System.in);
		System.out.println("donner moi le nomber d'employee :");
		int employeeNomber = lire.nextInt();
		
		Employee end = null;
		
		try {
			FileOutputStream fileOut = new FileOutputStream("emp.dat");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			out.writeInt(employeeNomber);
			for(int i=0 ; i<employeeNomber ; i++) {
				Employee e = new Employee();
				System.out.print("employee " +i +" name :");
				e.name = lire.next();
				System.out.print("employee "+i+" address :");
				e.address = lire.next();
				System.out.print("employee "+i+" SSN :");
				e.SSN = lire.nextInt();
				System.out.print("employee "+i+" number :");
				e.number = lire.nextInt();
				out.writeObject(e);
			}
			out.writeObject(end);
			out.close();
	        fileOut.close();
	        System.out.printf("donnees serialisees sauvegardees dans emp.dat");
		}
		catch(IOException i)
	      {
	          i.printStackTrace();
	      }

	}

}
