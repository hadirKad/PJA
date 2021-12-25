package dev_tp3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadTask extends Thread{

	
	public static void main(String[] args) throws IOException  {
		
		
		 
		 try 
		 {
			 ServerSocket serverSocket = new ServerSocket(5019);
			 
			 System.out.println("wainting for clients...");
		 while (true)
		 {
		 Socket clientSocket = serverSocket.accept();
		 System.out.println("Creating thread ");
		 Thread t = new ThreadedServer(clientSocket);
		 t.start();
		
		 }

	}catch (Exception e) {
		System.out.println(e);
	}
	}

}
