package dev_tp3;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;

public class ThreadedServer extends Thread {

	Socket clientSocket; 
	
	ThreadedServer (Socket s)
	{
		clientSocket = s;
	}
	
	 public void run()
	{
		 try {
            
			 System.out.println("Server Listening on "  + clientSocket.getLocalPort()+ " de port  " + clientSocket.getPort());

			 
			 DataInputStream dis = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
			 
			 DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
			 
			 StringBuffer stringBuffer = new StringBuffer();
			 StringBuilder sb = new StringBuilder();
			 RandomAccessFile sourceRaf;
			 
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
					              
								 stringBuffer.append(sourceRaf.readLine() + "\n");
					              
					   
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

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}


}
