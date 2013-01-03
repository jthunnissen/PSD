package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

import main.BeanCard;
import main.Card;
import main.EBeanType;
import main.Game;
import main.Player;

/*
 * A chat server that delivers public and private messages.
 */
public class Server implements Runnable {

	// The server socket.
	private static ServerSocket serverSocket = null;
	// The client socket.
	private static Socket clientSocket = null;
	
	public Game game = new Game();

	// This chat server can accept up to maxClientsCount clients' connections.
	private static final int maxClientsCount = 10;
	private static final clientThread[] threads = new clientThread[maxClientsCount];

	public static void main(String args[]) {
		
		// The default port number.
		int portNumber = 2222;
		if (args.length < 1) {
			System.out.println("Usage: java Server <portNumber>\n"
					+ "Now using port number=" + portNumber);
		} else {
			portNumber = Integer.valueOf(args[0]).intValue();
		}

		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println(e);
		}
		
		Server server = new Server();
		new Thread(server).start();

		/*
		 * Create a client socket for each connection and pass it to a new client
		 * thread.
		 */
		while (true) {
			try {
				clientSocket = serverSocket.accept();
				int i = 0;
				for (i = 0; i < maxClientsCount; i++) {
					if (threads[i] == null) {
						(threads[i] = new clientThread(clientSocket, server, i)).start();
						break;
					}
				}
				if (i == maxClientsCount) {
					PrintStream os = new PrintStream(clientSocket.getOutputStream());
					os.println("Server too busy. Try later.");
					os.close();
					clientSocket.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
	
	public void sendUpdate(int from){
		Protocol protocol = new Protocol(game);
		String message = protocol.toJSON();
		for (int i = 0; i < maxClientsCount; i++) {
			if (threads[i] != null /*&& i != from*/) {
				threads[i].sendMessage(message);
			}
		}
	}
	
	public void broadcast(int from, String message) {
		for (int i = 0; i < maxClientsCount; i++) {
			if (threads[i] != null && i != from) {
				threads[i].sendMessage(message);
			}
		}
	}
	
	public void run() {
		/*
		 * Keep on reading from the socket till we receive "Bye" from the
		 * server. Once we received that then we want to break.
		 */
		String responseLine;
		BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
		try {
			while ((responseLine = is.readLine()) != null) {
				System.out.println(responseLine);
				if (responseLine.indexOf("start") != -1)
					break;
			}
			//closed = true;
		} catch (IOException e) {
			System.err.println("IOException:  " + e);
		}
	}
}


class clientThread extends Thread {

	private String clientName = null;
	private BufferedReader is = null;
	private PrintStream os = null;
	private Socket clientSocket = null;
	private int id = 0;
	private int maxClientsCount;
	private Server server;
	
	

	public clientThread(Socket clientSocket, Server server, int id) {
		this.clientSocket = clientSocket;
		this.server = server;
		this.id = id;
	
		
	}
	
	public void sendMessage(String message) {
		this.os.println(message);
	}

	public void run() {
		int maxClientsCount = this.maxClientsCount;
		

		try {
			/*
			 * Create input and output streams for this client.
			 */
			is = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
			os = new PrintStream(clientSocket.getOutputStream());
			String name;
			

			
			/* Start the conversation. */
			String line;
			while ((line = is.readLine()) != null) {
				//String line = is.readLine();
				System.out.println(line);
				if (line.startsWith("/quit")) {
					break;
				}
				
				if(line.startsWith("NEWPLAYER")){
					
					Player player = new Player(line.replace("NEWPLAYER ", ""));
					player.addCardToHand(new BeanCard(EBeanType.BLUEBEAN));
					server.game.addPlayer(player);
				}

				/* The message is public, broadcast it to all other clients. */
				synchronized (this) {
					//server.broadcast(id, line);
					server.sendUpdate(id);
				}
			}

			synchronized (this) {
				for (int i = 0; i < maxClientsCount; i++) {
					server.broadcast(id, "Houdoe");	
							}
			}
			os.println("*** Bye ***");

			
			/*
			 * Close the output stream, close the input stream, close the socket.
			 */
			is.close();
			os.close();
			clientSocket.close();
		} catch (IOException e) {
		}
	}
}