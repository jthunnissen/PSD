package bohnanza.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import org.json.Protocol;

import bohnanza.core.Action;
import bohnanza.core.Card;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;
import bohnanza.core.shared.actions.NextPlayer;
import bohnanza.standard.model.StandardGame;

/** This class is responsible for creating connections to players 
*
* @author Anne van de Venis
* @version 1.0
*/
public class Server implements Runnable {

	/**
	 * Socket of the server
	 */
	private static ServerSocket serverSocket = null;
	/**
	 * Socket of the client
	 */
	private static Socket clientSocket = null;

	/**
	 * The Bohnanza game
	 */
	public StandardGame game = new StandardGame();

	/**
	 * Max number of players
	 */
	private static final int maxClientsCount = 10;
	/**
	 * Array that contains all connections to the players
	 */
	private static final ServerThread[] threads = new ServerThread[maxClientsCount];
	/**
	 * Information storage about all cards that are send to players
	 */
	HashMap<Integer, Card> cardIndex = new HashMap<Integer, Card>();

	public static void main(String args[]) {
		int portNumber = 2222;
		if(args.length < 1) {
			System.out.println("Usage: java Server <portNumber>\n" + "Now using port number=" + portNumber);
		} else {
			portNumber = Integer.valueOf(args[0]).intValue();
		}

		try {
			serverSocket = new ServerSocket(portNumber);
		} catch(IOException e) {
			System.out.println(e);
		}

		Server server = new Server();
		new Thread(server).start();

		while(true) {
			try {
				clientSocket = serverSocket.accept();
				int i = 0;
				for(i = 0; i < maxClientsCount; i++) {
					if(threads[i] == null) {
						(threads[i] = new ServerThread(clientSocket, server, i)).start();
						break;
					}
				}
				if(i == maxClientsCount) {
					PrintStream os = new PrintStream(clientSocket.getOutputStream());
					os.println("Server too busy. Try later.");
					os.close();
					clientSocket.close();
				}
			} catch(IOException e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Send game update to all players
	 * @param from Player that caused the update
	 */
	public void sendUpdate(int from) {
		if(game.isStarted() && game.getActions(game.getActivePlayer()).size() == 1) {
			if(game.getActions(game.getActivePlayer()).contains(NextPlayer.class)) {
				Action action = new NextPlayer(game);
				try {
					game.handle(action);
				} catch(IllegalActionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		String message;
		if(!game.isStarted()) {
			message = Protocol.waitingForPlayers();
		} else {
			message = Protocol.toJSON(game, cardIndex);
		}

		for(int i = 0; i < maxClientsCount; i++) {
			if(threads[i] != null /* && i != from */) {
				threads[i].sendMessage(message);
			}
		}
	}

	/**
	 * Send message to all players
	 * @param from Player that send message
	 * @param message Message to be send
	 */
	public void broadcast(int from, String message) {
		for(int i = 0; i < maxClientsCount; i++) {
			if(threads[i] != null /* && i != from */) {
				threads[i].sendMessage(message);
			}
		}
	}

	@Override
	public void run() {
		String responseLine;
		BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
		try {
			while((responseLine = is.readLine()) != null) {
				System.out.println(responseLine);
				if(responseLine.equals("start")) {
					game.start();
					sendUpdate(0);
				}
				if(responseLine.indexOf("end") != -1)
					break;
			}
			// closed = true;
		} catch(IOException | IllegalActionException e) {
			System.err.println("Exception:  " + e);
		}
	}

	/**
	 * Send a message to a particular player
	 * @param player Players that receives the update
	 * @param message Message to be send
	 */
	public void sendToPlayer(Player player, String message) {
		if(message.equals("GAMEUPDATE")) {
			message = Protocol.toJSON(game, cardIndex);
		}
		for(int i = 0; i < maxClientsCount; i++) {
			if(threads[i] != null && threads[i].getPlayer() == player) {
				threads[i].sendMessage(message);
				break;
			}
		}

	}

	/**
	 * Add card to the card information storage
	 * @param card Card to be stored
	 */
	public synchronized void addToCardIndex(Card card) {
		cardIndex.put(card.hashCode(), card);
	}

	/**
	 * Getter for the card information storage
	 * @param hashcode ID (hashcode) of the to be retrieved card
	 * @return
	 */
	public Card getCardFromIndex(int hashcode) {
		return cardIndex.get(hashcode);
	}
}