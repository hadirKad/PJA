package dev_tp3;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	
	
	public void contenu_fichier(String file){
		
	}

	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket;
		Socket clientSocket;
		DataOutputStream dos;
		DataInputStream dis;
		RandomAccessFile sourceRaf;
		
		StringBuffer stringBuffer = new StringBuffer();
		StringBuilder sb = new StringBuilder();
		
		
		
		 try {
	           
			 serverSocket = new ServerSocket(5019);
			 
			 System.out.println("wainting for clients...");
			 
			 clientSocket = serverSocket.accept();
			
			 System.out.println("Server Listening on "  + clientSocket.getLocalPort()+ " de port  " + clientSocket.getPort());

			 
			 dis = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
			 
			 dos = new DataOutputStream(clientSocket.getOutputStream());
			 
			 boolean done = false ;
			 String fromClient = "";
			 while(!done) {
				 try {
					 
					 
					 fromClient = dis.readUTF();
					 done = fromClient.equalsIgnoreCase("QUIT");
					 File file = new File (fromClient.toString());
					 
					 if(file.exists()) {
						 if(!file.isDirectory()) {
							 
							 stringBuffer.append("le contenu du fichier " + fromClient+ ":\n");
							 sourceRaf = new RandomAccessFile(fromClient.toString(), "r");
							 while (sourceRaf.getFilePointer() < sourceRaf.length()) {
					              
								 stringBuffer.append(sourceRaf.readLine() + "\n" );
					              
					   
					            }
							 dos.writeUTF(stringBuffer.toString());
							 
						 }
						 else {
							 
							sb.append("la liste de fichiers contenus dans le répertoire " + fromClient +":\n");
                            for(File f : file.listFiles()) {
								 
								 if(!f.isDirectory())
								 sb.append(f.getName() + "\n");
								 
							 }
							 dos.writeUTF(sb.toString());
		
							
						 }
					 }
					 else {

						 if(done)
							 dos.writeUTF("fin");
						 else
						 dos.writeUTF(fromClient+ " does not exist");
					 }
					 
					 
					 dos.flush();
					 
				 }
				 catch (IOException e) {
					done = true;
				}
			 }
			 
			 dis.close();
		     dos.close();
			 clientSocket.close();
				            
	        } catch(Exception e)
	            {
	        	
	                System.out.println(e.toString());
	            }
		
		
	}

}
