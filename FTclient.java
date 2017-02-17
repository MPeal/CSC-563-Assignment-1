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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class FTclient 
{
	public static void main(String[] args) 
	{
		//Ask user for host IP address, assign it to string
		System.out.println("What is the host IP address?");
		Scanner forIP = new Scanner(System.in);
		String ipAddress = forIP.next();
		
		System.out.println("What port number are you connecting to?");
		Scanner forPort = new Scanner(System.in);
		int portNum = forPort.nextInt();
		
		try{
			//create socket
			Socket clientSocket = new Socket(ipAddress, portNum);

			//input stream & reader
			InputStream clientInputStream = clientSocket.getInputStream();
			BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientInputStream));
			
			//create output stream & writer
			OutputStream clientOutputStream = clientSocket.getOutputStream();
			PrintWriter clientWriter = new PrintWriter(clientOutputStream, true);
			
			//look for input from server and print it to the console
			String command = clientReader.readLine();
			System.out.println(command);
			
			//assign the user input as the next character, then send that character over the output stream
			Scanner in = new Scanner(System.in);
			String reply = in.next();
			clientWriter.println(reply);
			
			
			//Base conditional statements around what client wants to do
			//If choose download, client reads text file from server and writes to a file
			
			if(!reply.equals("u") && !reply.equals("U") && !reply.equals("d") && !reply.equals("D"))
			{
				String reprimand = clientReader.readLine();
				System.out.println(reprimand);
			}
			if(reply.equals("D") || reply.equals("d"))
			{
				//gets the "which file to download?" prompt from server and prints it
				String serverPrompt = clientReader.readLine();
				System.out.println(serverPrompt);
				//Creates a scanner that scans for user input, assigns that to a string, send string to server
				Scanner forFileName = new Scanner(System.in);
				String filename = forFileName.nextLine();
				clientWriter.println(filename);
				
				try{
					//Create a file writer that will write the file
					FileWriter fw = new FileWriter("c://Users/Mike/Desktop/"+filename+".txt");
					
					//Create print writer
					PrintWriter fp = new PrintWriter(fw);
					
					//Print to the file each line coming in from the client
					String fileLine;
					while((fileLine = clientReader.readLine()) != null)
					{
						fp.println(fileLine);
					}
					fp.close();
					System.out.println("Download Complete!");
					clientWriter.close();
					clientSocket.close();
					//close the file printer
					}catch(IOException e){System.out.println("Client problem is " + e);}
			}
			//If choose upload, client uploads and writes text file to server
			else if(reply.equals("U") || reply.equals("u"))
			{
				//Creates scanner looking for server "which file to upload (must be a .txt file, DO include file extension" prompt
				//Prints the prompt to the console
				Scanner serverUploadQuestion = new Scanner(clientReader);
				String serverUploadPrompt = serverUploadQuestion.nextLine();
				System.out.println(serverUploadPrompt);
				
				//Assigns the filename to the next user input, sends the name to server
				Scanner clientFileChoice = new Scanner(System.in);
				String fileToUpload = clientFileChoice.nextLine();
				clientWriter.println(fileToUpload);
				clientWriter.flush();
				
				//Reads the designated file and prints it to the server
				FileReader fileRead = new FileReader("C:/Users/Mike/Documents/" +fileToUpload+".txt");
				BufferedReader reader = new BufferedReader(fileRead);

				String line;
				while((line = reader.readLine()) != null)
				{
					clientWriter.println(line);//test by printing to console
					clientWriter.flush();
				}
				if(reader.readLine() == null)
				{
					clientSocket.shutdownOutput();
					System.out.println("Upload complete!");
				}
				clientSocket.close();}
			}catch(IOException e){System.out.println(e+" client error please connect and try again");}
	}
}