package dev_tp3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		
		Scanner read = new Scanner(System.in);
		
		try
        {
			
			InetAddress serverAddress = InetAddress.getLocalHost();
            System.out.println("server Ip address: " + serverAddress.getHostAddress());
            Socket socket = new Socket(serverAddress.getHostName(), 5019);
		
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            
           
            String fromServer = ""   , toServer = "";
           
            
            while (!toServer.equalsIgnoreCase("QUIT")) {
            	
            	try {
            		
            		toServer = read.nextLine();
            		dos.writeUTF(toServer);
            		dos.flush();
            		fromServer = dis.readUTF();
            		System.out.println( fromServer);
            		
            	}
            	catch (IOException e) {
					System.out.println(e);
			
            	}
            	
            }
            
            dos.close();
            
        }
        catch(UnknownHostException e1)
        {
            System.out.println("Unknown host exception " + e1.toString());
        }
        catch(IOException e2)
        {
            System.out.println("IOException " + e2.toString());
        }
        catch(IllegalArgumentException e3)
        {
            System.out.println("Illegal Argument Exception " + e3.toString());
        }
        catch(Exception e4)
        {
            System.out.println("Other exceptions " + e4.toString());
        }

	}

	}


