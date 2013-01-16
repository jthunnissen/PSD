
package bohnanza.standard.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.CardPOJO;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.OfferPOJO;
import org.json.Protocol;

import bohnanza.standard.core.BeanCard;
import bohnanza.standard.core.Card;
import bohnanza.standard.core.EBeanType;
import bohnanza.standard.core.Game;
import bohnanza.standard.core.IllegalActionException;
import bohnanza.standard.core.Player;
import bohnanza.standard.core.actions.AcceptTrade;
import bohnanza.standard.core.actions.Action;
import bohnanza.standard.core.actions.BuyBeanField;
import bohnanza.standard.core.actions.DeclineTrade;
import bohnanza.standard.core.actions.DrawCards;
import bohnanza.standard.core.actions.DrawFaceUpCards;
import bohnanza.standard.core.actions.Harvest;
import bohnanza.standard.core.actions.NextPhase;
import bohnanza.standard.core.actions.PlantAsideBean;
import bohnanza.standard.core.actions.PlantBean;
import bohnanza.standard.core.actions.ProposeTrade;
import bohnanza.standard.core.actions.SetAsideCard;

class ServerThread extends Thread {

	private BufferedReader is = null;
	private PrintStream os = null;
	private Socket clientSocket = null;
	private int id = 0;
	private Server server;
	private Game game;
	private Player player;

	public Player getPlayer(){
		return player;
	}

	public ServerThread(Socket clientSocket, Server server, int id) {
		this.clientSocket = clientSocket;
		this.server = server;
		this.id = id;
		this.game = server.game;	
	}

	public void sendMessage(String message) {
		this.os.println(message);
	}

	public void run() {
		try {
			is = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
			os = new PrintStream(clientSocket.getOutputStream());

			String line;
			while ((line = is.readLine()) != null) {
				try {
					if(player != null){
						System.out.println(player.getName() +": "+line);
					}	else{
						System.out.println(line);
					}
					if (line.startsWith("/quit")) {
						break;
					}
					String[] commandos = line.split(" ");
					if(line.startsWith("NEWPLAYER")){
						try{
							player = server.game.addPlayer(line.replace("NEWPLAYER ", ""));
						}catch(IllegalActionException e){
							this.sendMessage(Protocol.usernameCheckToJSON(false));
						}
						if(player != null){
							this.sendMessage(Protocol.usernameCheckToJSON(true));
							player.addCardToHand(new BeanCard(EBeanType.BLUEBEAN));
							server.sendUpdate(id);
						}

					} else if(line.startsWith(Protocol.ACCEPTTRADE)) {
						JSONObject offerJSON = new JSONObject(line.replaceFirst(Protocol.ACCEPTTRADE, ""));
						OfferPOJO offerPOJO = Protocol.sendOfferFromJSON(offerJSON);
						
						List<Card> cards = new ArrayList<Card>();
						for(CardPOJO cardPOJO : offerPOJO.getCards()){

							BeanCard card = findBeanCardFromPlayerFaceUp(cardPOJO.getName(), player);
							cards.add(card);
						}
						
						List<Card> offer = new ArrayList<Card>();

						
					

						HashMap<String, Integer> offerNames = new HashMap<String, Integer>();
						for(CardPOJO cardPOJO : offerPOJO.getOffer()){
							int index = 0;
							if(offerNames.containsKey(cardPOJO.getName())){
								index = (int)offerNames.get(cardPOJO.getName()) + 1;
							} else{
								index = 1;
							}
							offerNames.put(cardPOJO.getName(), index);
							offer.add(findBeanCardFromPlayerHand(cardPOJO.getName(), findPlayer(offerPOJO.getInitiator()), 1));
						}
						Action action = new AcceptTrade(game, game.getActivePlayer(), findPlayer(offerPOJO.getInitiator()), cards, offer);
						game.handle(action);
						server.sendUpdate(0);
					} else if(line.startsWith(Protocol.BUYBEANFIELD)){
						Action action = new BuyBeanField(game, player);
						game.handle(action);
						server.sendUpdate(id);
					} else if(line.startsWith(Protocol.DECLINETRADE)) {
						Action action = new DeclineTrade(game, player);
						game.handle(action);
						server.sendToPlayer(player, Protocol.DECLINETRADE);
					} else if(line.startsWith(Protocol.DRAWCARDS)) {
						Action action = new DrawCards(game, player);
						game.handle(action);
						server.sendUpdate(id);
					} else if(line.startsWith(Protocol.DRAWFACEUPCARDS)) {
						Action action = new DrawFaceUpCards(game, player);
						game.handle(action);
						server.sendUpdate(id);
					} else if(line.startsWith(Protocol.HARVEST)){
						int fieldid = Integer.valueOf(commandos[1]);
						Action action = new Harvest(game, player, player.getBeanFields().get(fieldid));
						game.handle(action);
						server.sendUpdate(id);
					} else if(line.startsWith(Protocol.NEXTPHASE)){
						Action action = new NextPhase(game,player);
						game.handle(action);
						server.sendUpdate(id);
					} else if(line.startsWith(Protocol.PLANTBEAN )|| line.startsWith(Protocol.PLANTASIDEBEAN)) {
						BeanCard card = findBeanCard(commandos[2]);
						
						// TODO: Hack possible?
					
						int fieldid = Integer.valueOf(commandos[1]);
						
						Action action;
						if(line.startsWith(Protocol.PLANTBEAN )) {
							// Must be first card in hand
							if(card.getName().equals(player.getHand().get(0).getName())){
								card = (BeanCard) player.getHand().get(0);
							}
							action = new PlantBean(game, player, card, player.getBeanFields().get(fieldid));
						} else{
							card = this.findBeanCardFromPlayerAside(card.getName());
							action = new PlantAsideBean(game, player, card, player.getBeanFields().get(fieldid));
						}
						
						game.handle(action);
						server.sendUpdate(id);

					} else if(line.startsWith(Protocol.PROPOSETRADE)){
						OfferPOJO offerPOJO = Protocol.sendOfferFromJSON(new JSONObject(line.replaceFirst(Protocol.PROPOSETRADE, "")));
						List<Card> cards = new ArrayList<Card>();
						List<CardPOJO> cardsPOJO = new ArrayList<CardPOJO>();
						for(CardPOJO card: offerPOJO.getCards()){
							cards.add(findBeanCardFromPlayerFaceUp(card.getName(), game.getActivePlayer()));
							cardsPOJO.add(new CardPOJO(card.getName(), ""));
						}
						
						List<Card> offer = new ArrayList<Card>();
						List<CardPOJO> offerCardPOJO = new ArrayList<CardPOJO>();
						HashMap<String, Integer> offerNames = new HashMap<String, Integer>();
						for(CardPOJO card: offerPOJO.getOffer()){
							int index = 1;
							if(offerNames.containsKey(card.getName())){
								index = (int) offerNames.get(card.getName()) + 1;
							}
							offerNames.put(card.getName(), index);
							Card offerCard = findBeanCardFromPlayerHand(card.getName(), player, index);
							offer.add(offerCard);
							offerCardPOJO.add(new CardPOJO(offerCard.getName(), ""));
						}
						//Action action = new ProposeTrade(game, player, game.getActivePlayer(), give, receive);
										
						Action action = new ProposeTrade(game, player, game.getActivePlayer(), cards, offer);
						game.handle(action);
						server.sendToPlayer(game.getActivePlayer(), Protocol.sendOfferToJSON(Protocol.PROPOSETRADE, player.getName(), cardsPOJO, offerCardPOJO));
					} else if(line.startsWith(Protocol.SETASIDECARD)){
						Action action = new SetAsideCard(game, player, findBeanCardFromPlayerFaceUp(commandos[1], player));
						game.handle(action);
						server.sendUpdate(0);
					} else if(line.startsWith(Protocol.CHAT)){

						server.broadcast(id, Protocol.chatToJSON(line.replace(Protocol.CHAT+" ", "")));

						server.broadcast(id, Protocol.chatToJSON(line.replaceFirst(Protocol.CHAT+" ", "")));

					} else {
						System.out.println("Unknown command: "+ line);
						//server.broadcast(id, line);
					}

				} catch (IllegalActionException | JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					server.sendToPlayer(player, Protocol.errorToJSON(e.getMessage()));
				}
			}

			synchronized (this) {
				server.broadcast(id, "Houdoe");	
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
	
	public BeanCard findBeanCardFromPlayerFaceUp(String cardName, Player player){
		BeanCard result = null;
		for(Card card :  player.getFaceUpCards()){
			if(card.getName().startsWith(cardName)){
				result = (BeanCard) card;
				break;
			}
		}
		return result;
	}

	public Card findBeanCardFromPlayerHand(String cardName, Player player, int index){
		Card result = null;
		int found = 0;
		for(Card card :  player.getHand()){
			if(card.getName().equals(cardName)){
				result = card;
				found++;
			}
			if(found == index) {

				break;
			}
		}
		if(result == null) System.out.println("this player has no "+cardName);
		return result;
	}
	public BeanCard findBeanCardFromPlayerAside(String cardName){
		BeanCard result = null;
		for(Card card :  player.getSetAsideCards()){
			if(card.getName().startsWith(cardName)){
				result = (BeanCard) card;
				break;
			}
		}
		return result;
	}

	public BeanCard findBeanCard(String cardName){
		BeanCard result = null;
		for(EBeanType bean : EBeanType.values()){
			if(bean.toString().startsWith(cardName))
				result = new BeanCard(bean);
		}
		return result;
	}
	
	public Player findPlayer(String playerName){
		Player result = null;
		for(Player player: game.getPlayers()){
			if(player.getName().equals(playerName)){
				result = player;
				break;
			}
		}
		return result;
	}
}