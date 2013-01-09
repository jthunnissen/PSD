
package bohnanza.standard.client;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import bohnanza.standard.server.Protocol;


import javafx.application.Application;

public class Client implements Runnable {

	// The client socket
	private static Socket clientSocket = null;
	// The output stream
	private static PrintStream os = null;
	// The input stream
	private static BufferedReader is = null;

	private static BufferedReader inputLine = null;
	private static boolean closed = false;
	private ClientGUI application;
	private String host;
	private Integer port;

	public Client (ClientGUI application, String host) {
		this.application = application;
		String[] temp = host.split(":");
		this.host = temp[0];
		this.port = Integer.valueOf(temp[1]);
		try {
			clientSocket = new Socket(this.host, this.port);
			inputLine = new BufferedReader(new InputStreamReader(System.in));
			os = new PrintStream(clientSocket.getOutputStream());
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + this.host +":"+ this.port);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to the host "
					+ host);
		}

		/*
		 * If everything has been initialized then we want to write some data to the
		 * socket we have opened a connection to on the port portNumber.
		 */
		if (clientSocket != null && os != null && is != null) {
			/* Create a thread to read from the server. */
			new Thread(this).start();
			//sendToServer("NEWPLAYER "+username);
			//while (!closed) {
			//	os.println(inputLine.readLine().trim());
			//}
			/*
			 * Close the output stream, close the input stream, close the socket.
			 */
		}
	}

	public void sendToServer(String update){
		System.out.println("OUT:"+update);
		os.println(update);
	}


	/*
	 * Create a thread to read from the server. (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		/*
		 * Keep on reading from the socket till we receive "Bye" from the
		 * server. Once we received that then we want to break.
		 */
		String responseLine;
		try {
			while ((responseLine = is.readLine()) != null) {
				System.out.println("IN:"+responseLine);
				application.update(responseLine);
				if (responseLine.indexOf("*** Bye") != -1)
					break;
			}
			closed = true;
			os.close();
			is.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}