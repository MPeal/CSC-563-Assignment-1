	   
Name: Michael Peal
Course: 563	   
Assignment: #1


Connection
------------------------
-User connects by first entering server IP address and port number (use 2006 for port number)

Choice
------------------------
-Server will prompt user to enter either "u" for upload or "d" for download
-If user reply is "u" or "U" server asks client for file to upload.
-If user reply is "d" or "D" server asks client for file to download.
-If user reply is anything else, the server sends the message "Invalid prompt, please re-connect and try again." 
	- If above happens, the connection is closed. Client must re-establish connection.

Upload
------------------------
-After server asks client for file to upload, client must provide file name.
	-Client does not need to include ".txt" in the file name
	-Currently on MY LAPTOP, the client can only upload files from C:/Users/Mike/Documents/
-The file will be read from the socket and then written to the server directory
	-The server directory path on MY LAPTOP is currently set to C://Users/Mike/workspace/
-After file upload, server sends the client message "Upload Complete!"

Download
------------------------
-After server asks client for file to download, client must provide file name.
	-Client does not need to include ".txt" in the file name
	-Currently on MY LAPTOP, the server will send files from its directory, C://Users/Mike/workspace/
-The file is then read from the server and sent to the client
	-Currently on MY LAPTOP, all files are downloaded to C://Users/Mike/Desktop/
-After file download, message "Download Complete!" is printed by the client program