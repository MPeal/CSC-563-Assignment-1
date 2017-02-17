package pkg;

/*          
Name: Michael Peal
Course: 563
Assignment: #1
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class FTserver 
{
	public static void main(String[] args) 
	{
		try{
		ServerSocket ss = new ServerSocket(2006);
		
		while(true)
		{
			Socket s = ss.accept();
			
			FTthread t = new FTthread(ss, s);
			t.start();
			ss.close();
		}
		}catch(IOException e){System.out.println("Server has this problem: "+e);}
	}
}