package bohnanzagui.standard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/** This class is responsible for sending and receiving updates. 
*
* @author Anne van de Venis
* @version 1.0
*/
public class Client implements Runnable {

	/**
	 * Socket that connect to the server
	 */
	private static Socket clientSocket = null;
	/**
	 * Output stream for sending updates to server
	 */
	private static PrintStream os = null;
	/**
	 * Input stream for receiving updates from server
	 */
	private static BufferedReader is = null;
	/**
	 * Application where updates are forwarded to
	 */
	private ClientGUI application;	

	public Client(ClientGUI application, String host) {
		this.application = application;
		String[] temp = host.split(":");
		String hostName = temp[0];
		Integer port = Integer.valueOf(temp[1]);
		try {
			clientSocket = new Socket(hostName, port);	
			os = new PrintStream(clientSocket.getOutputStream());
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch(UnknownHostException e) {
			System.err.println("Don't know about host " + hostName + ":" + port);
		} catch(IOException e) {
			System.err.println("Couldn't get I/O for the connection to the host " + hostName);
		}
		if(clientSocket != null && os != null && is != null) {
			new Thread(this).start();
		}
	}

	/**
	 * Sends update to the server
	 * @param update The update to be send
	 */
	public void sendToServer(String update) {
		System.out.println("OUT:" + update);
		os.println(update);
	}

	/* Create a thread to read from the server. (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run() */
	public void run() {
		String responseLine;
		try {
			while((responseLine = is.readLine()) != null) {
				System.out.println("IN:" + responseLine);
				application.update(responseLine);
				if(responseLine.indexOf("*** Bye") != -1)
					break;
			}
			os.close();
			is.close();
			clientSocket.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}