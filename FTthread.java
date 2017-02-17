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

public class FTthread extends Thread
{
	public ServerSocket ss;
	public Socket myS;
	
	public FTthread(ServerSocket ss, Socket s) //constructor, which takes in a socket to connect to
	{
		this.ss = ss;
		this.myS = s;
	}
	
	public void run()
	{ 
		try{
		BufferedReader bf = new BufferedReader(new InputStreamReader(myS.getInputStream()));
		PrintWriter out = new PrintWriter(myS.getOutputStream(), true);
		
		//print a prompt to the client
		out.println("Enter 'u' to upload a file, 'd' to download a file");
		out.flush();
		
			String u = "U";
			String uu = "u";
			String d = "D";
			String dd = "d";
			String filename;
			String clientChoice = bf.readLine();
			
			//if the client chooses upload
			if (clientChoice.equals(u) || clientChoice.equals(uu))
			{
				//ask the client for the file name, assign it to a string variable
				out.println("Which text file are you uploading?");
				out.flush();
				filename = bf.readLine();
				
				try{
				//Create a file writer that will write the file
				FileWriter fw = new FileWriter("c://Users/Mike/workspace/"+filename+".txt");
				
				//Create print writer
				PrintWriter fp = new PrintWriter(fw);
				
				//Print to the file each line coming in from the client
				String fileLine;
				while((fileLine = bf.readLine()) != null)
				{
					fp.println(fileLine);
				}
				//close the file printer
				fp.close();
				out.close();
				}catch(FileNotFoundException fnfe){System.out.println(fnfe.getMessage() + "The file was not found");}		
			}
			//if client chooses download
			else if (clientChoice.equals(d) || clientChoice.equals(dd))
			{
				try{
				//Prompts client to pick a file, assigns reply as a String fileToSend
				out.println("Which file would you like to download?");
				String file = bf.readLine();
				System.out.println(file);
				FileReader fr = new FileReader("C:/Users/Mike/workspace/"+file+".txt");
				BufferedReader br = new BufferedReader(fr);
				
				String line;
				while((line=br.readLine()) != null)
				{
					out.println(line);
				}
				if(br.readLine() == null)
				{
					br.close();
					myS.shutdownOutput();
				}
				}catch(FileNotFoundException fnfe){System.out.println(fnfe.getMessage() + "The file was not found");}
			}
			else if (!clientChoice.equals(u) && !clientChoice.equals(uu) && !clientChoice.equals(d) && !clientChoice.equals(dd))
			{
				out.println("Invalid prompt.  Please re-connect and try again.");
			}
	
		}catch(IOException e){System.out.println("Server at-end problem is " + e);}
	
	}	
}
