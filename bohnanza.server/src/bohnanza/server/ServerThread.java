package bohnanza.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.json.CardPOJO;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.OfferPOJO;
import org.json.Protocol;

import bohnanza.core.Action;
import bohnanza.core.BeanCard;
import bohnanza.core.Card;
import bohnanza.core.IllegalActionException;
import bohnanza.core.Player;
import bohnanza.core.shared.actions.DrawCards;
import bohnanza.core.shared.actions.Harvest;
import bohnanza.core.shared.actions.NextPhase;
import bohnanza.core.shared.actions.PlantBean;
import bohnanza.standard.actions.AcceptTrade;
import bohnanza.standard.actions.BuyBeanField;
import bohnanza.standard.actions.DeclineTrade;
import bohnanza.standard.actions.DrawFaceUpCards;
import bohnanza.standard.actions.PlantAsideBean;
import bohnanza.standard.actions.ProposeTrade;
import bohnanza.standard.actions.SetAsideCard;
import bohnanza.standard.model.BohnanzaPlayer;
import bohnanza.standard.model.EBeanType;
import bohnanza.standard.model.StandardGame;

/** This class is responsible for sending and receiving updates to a player on the server side. 
*
* @author Anne van de Venis
* @version 1.0
*/
class ServerThread extends Thread {

	/**
	 * Socket to the client
	 */
	private Socket clientSocket = null;
	/**
	 * Outputstream for sending updates to client
	 */
	private PrintStream os = null;
	/**
	 * ID for this player
	 */
	private int id = 0;
	/**
	 * Server that created this thread
	 */
	private Server server;
	/**
	 * The Bohnanza game
	 */
	private StandardGame game;
	/**
	 * Player that plays via this connection
	 */
	private Player player;

	/**
	 * Getter for the player
	 * @return The player that plays via this connection
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Creates ServerThread
	 * @param clientSocket Socket for this connection
	 * @param server Server that created this thread
	 * @param id ID for this player
	 */
	public ServerThread(Socket clientSocket, Server server, int id) {
		this.clientSocket = clientSocket;
		this.server = server;
		this.id = id;
		this.game = server.game;
	}

	/**
	 * Send message to other players
	 * @param message Message to be send
	 */
	public void sendMessage(String message) {
		this.os.println(message);
	}

	public void run() {
		BufferedReader is = null;	
		try {
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			os = new PrintStream(clientSocket.getOutputStream());

			String line;
			while((line = is.readLine()) != null) {
				try {
					if(player != null) {
						System.out.println(player.getName() + ": " + line);
					} else {
						System.out.println(line);
					}
					if(line.startsWith("/quit")) {
						break;
					}
					String[] commandos = line.split(" ");
					if(line.startsWith("NEWPLAYER")) {
						try {
							player = new BohnanzaPlayer(line.replace("NEWPLAYER ", ""));
							player = server.game.addPlayer(player);
						} catch(IllegalActionException e) {
							this.sendMessage(Protocol.usernameCheckToJSON(false));
						}
						if(player != null) {
							this.sendMessage(Protocol.usernameCheckToJSON(true));
							player.addCardToHand(new BeanCard(EBeanType.BLUEBEAN));
							server.sendUpdate(id);
						}

					} else if(line.startsWith(Protocol.ACCEPTTRADE)) {
						JSONObject offerJSON = new JSONObject(line.replaceFirst(Protocol.ACCEPTTRADE, ""));
						OfferPOJO offerPOJO = Protocol.sendOfferFromJSON(offerJSON);

						List<Card> cards = new ArrayList<Card>();
						for(CardPOJO cardPOJO : offerPOJO.getCards()) {
							cards.add(server.getCardFromIndex(cardPOJO.getHashcode()));
						}
						List<Card> offer = new ArrayList<Card>();
						for(CardPOJO cardPOJO : offerPOJO.getOffer()) {
							offer.add(server.getCardFromIndex(cardPOJO.getHashcode()));
						}
						Action action = new AcceptTrade(game, game.getActivePlayer(), findPlayer(offerPOJO.getInitiator()), cards, offer);
						game.handle(action);
						server.sendUpdate(0);
					} else if(line.startsWith(Protocol.BUYBEANFIELD)) {
						Action action = new BuyBeanField(game, player);
						game.handle(action);
						server.sendUpdate(id);
					} else if(line.startsWith(Protocol.DECLINETRADE)) {
						Action action = new DeclineTrade(game, player);
						game.handle(action);
						server.sendToPlayer(findPlayer(line.replaceFirst(Protocol.DECLINETRADE + " ", "")), "GAMEUPDATE");
					} else if(line.startsWith(Protocol.DRAWCARDS)) {
						Action action = new DrawCards(game, player);
						game.handle(action);
						server.sendUpdate(id);
					} else if(line.startsWith(Protocol.DRAWFACEUPCARDS)) {
						Action action = new DrawFaceUpCards(game, player);
						game.handle(action);
						server.sendUpdate(id);
					} else if(line.startsWith(Protocol.HARVEST)) {
						int fieldid = Integer.valueOf(commandos[1]);
						Action action = new Harvest(game, player, player.getBeanFields().get(fieldid));
						game.handle(action);
						server.sendUpdate(id);
					} else if(line.startsWith(Protocol.NEXTPHASE)) {
						Action action = new NextPhase(game);
						game.handle(action);
						server.sendUpdate(id);
					} else if(line.startsWith(Protocol.PLANTBEAN) || line.startsWith(Protocol.PLANTASIDEBEAN)) {
						BeanCard card = (BeanCard) server.getCardFromIndex(Integer.valueOf(commandos[2]));
						int fieldid = Integer.valueOf(commandos[1]);

						Action action;
						if(line.startsWith(Protocol.PLANTBEAN)) {
							action = new PlantBean(game, player, card, player.getBeanFields().get(fieldid));
						} else {
							action = new PlantAsideBean(game, player, card, player.getBeanFields().get(fieldid));
						}
						game.handle(action);
						server.sendUpdate(id);

					} else if(line.startsWith(Protocol.PROPOSETRADE)) {
						OfferPOJO offerPOJO = Protocol.sendOfferFromJSON(new JSONObject(line.replaceFirst(Protocol.PROPOSETRADE, "")));
						List<Card> cards = new ArrayList<Card>();
						List<CardPOJO> cardsPOJO = new ArrayList<CardPOJO>();
						for(CardPOJO cardPojo : offerPOJO.getCards()) {
							Card card = server.getCardFromIndex(cardPojo.getHashcode());
							cards.add(card);
							cardsPOJO.add(new CardPOJO(card.getName(), 0, card.hashCode()));
						}

						List<Card> offer = new ArrayList<Card>();
						List<CardPOJO> offerCardPOJO = new ArrayList<CardPOJO>();
						for(CardPOJO card : offerPOJO.getOffer()) {
							Card offerCard = server.getCardFromIndex(card.getHashcode());
							offer.add(offerCard);
							offerCardPOJO.add(new CardPOJO(offerCard.getName(), 0, offerCard.hashCode()));
						}
						Action action = new ProposeTrade(game, player, game.getActivePlayer(), cards, offer);
						game.handle(action);
						server.sendToPlayer(game.getActivePlayer(), Protocol.sendOfferToJSON(Protocol.PROPOSETRADE, player.getName(), cardsPOJO, offerCardPOJO));
					} else if(line.startsWith(Protocol.SETASIDECARD)) {
						Action action = new SetAsideCard(game, player, server.getCardFromIndex(Integer.valueOf(commandos[1])));
						game.handle(action);
						server.sendUpdate(0);
					} else if(line.startsWith(Protocol.CHAT)) {
						server.broadcast(id, Protocol.chatToJSON(line.replaceFirst(Protocol.CHAT + " ", "")));
					} else {
						System.out.println("Unknown command: " + line);
						// server.broadcast(id, line);
					}

				} catch(IllegalActionException | JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					server.sendToPlayer(player, Protocol.errorToJSON(e.getMessage()));
				}
			}

			synchronized(this) {
				server.broadcast(id, "Houdoe");
			}
			os.println("*** Bye ***");

			/* Close the output stream, close the input stream, close the
			 * socket. */
			is.close();
			os.close();
			clientSocket.close();
		} catch(IOException e) {
		}
	}

	/**
	 * Returns player for a given name
	 * @param playerName Name of the player
	 * @return Player with the given name or NULL
	 */
	public Player findPlayer(String playerName) {
		Player result = null;
		for(Player player : game.getPlayers()) {
			if(player.getName().equals(playerName)) {
				result = player;
				break;
			}
		}
		return result;
	}
}