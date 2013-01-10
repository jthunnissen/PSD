
package bohnanza.standard.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
import bohnanza.standard.core.actions.PlantBean;
import bohnanza.standard.core.actions.ProposeTrade;

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
						String[] options = line.split(";");
						BeanCard giveCard = findBeanCard(options[1]);
						List<Card> give = new ArrayList<Card>();
						give.add(giveCard);
						List<Card> receive = new ArrayList<Card>();
						String[] receiveCards = options[2].split(",");
						for(int i=0; i<receiveCards.length; i++){
							receive.add(findBeanCard(receiveCards[i]));
						}
						Action action = new AcceptTrade(game, player, game.getActivePlayer(), give, receive);
						game.getCurrentState().handle(action);
					} else if(line.startsWith(Protocol.BUYBEANFIELD)){
						Action action = new BuyBeanField(game, player);
						game.getCurrentState().handle(action);
						server.sendUpdate(id);
					} else if(line.startsWith(Protocol.DECLINETRADE)) {
						Action action = new DeclineTrade(game, player);
						game.getCurrentState().handle(action);
						server.sendToPlayer(player, Protocol.DECLINETRADE);
					} else if(line.startsWith(Protocol.DRAWCARDS)) {
						Action action = new DrawCards(game, player);
						game.getCurrentState().handle(action);
						server.sendUpdate(id);
					} else if(line.startsWith(Protocol.DRAWFACEUPCARDS)) {
						Action action = new DrawFaceUpCards(game, player);
						game.getCurrentState().handle(action);
						server.sendUpdate(id);
					} else if(line.startsWith(Protocol.HARVEST)){
						int fieldid = Integer.valueOf(commandos[1]);
						Action action = new Harvest(game, player, player.getBeanFields().get(fieldid));
						game.getCurrentState().handle(action);
						server.sendUpdate(id);
					} else if(line.startsWith(Protocol.NEXTPHASE)){
						Action action = new NextPhase(game,player);
						game.getCurrentState().handle(action);
						server.sendUpdate(id);
					} else if(line.startsWith(Protocol.PLANTBEAN)) {
						BeanCard card = null;
						for(EBeanType bean : EBeanType.values()){
							if(bean.toString().startsWith((commandos[2])))
								card = new BeanCard(bean);
						}
						// TODO: Hack possible?
						if(card.getName().equals(player.getHand().get(0).getName())){
							card = (BeanCard) player.getHand().get(0);
						} else
							System.out.println("RAAAAR");
						int fieldid = Integer.valueOf(commandos[1]);
						Action action = new PlantBean(game, player, card, player.getBeanFields().get(fieldid));
						game.getCurrentState().handle(action);
						server.sendUpdate(id);
					} else if(line.startsWith(Protocol.PROPOSETRADE)){
						String[] options = line.split(";");
						BeanCard giveCard = findBeanCard(options[1]);
						List<Card> give = new ArrayList<Card>();
						give.add(giveCard);
						List<Card> receive = new ArrayList<Card>();
						String[] receiveCards = options[2].split(",");
						if(receiveCards.length>0){
							for(int i=0; i<receiveCards.length; i++){
								receive.add(findBeanCard(receiveCards[i]));
							}
						}
						Action action = new ProposeTrade(game, player, game.getActivePlayer(), give, receive);
						game.getCurrentState().handle(action);
						server.sendToPlayer(game.getActivePlayer(), line);
					} else if(line.startsWith(Protocol.CHAT)){
						server.broadcast(id, Protocol.chatToJSON(line.replace(Protocol.CHAT+" ", "")));
					} else {
						server.broadcast(id, line);
					}

				} catch (IllegalActionException e) {
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

	public BeanCard findBeanCard(String cardName){
		BeanCard result = null;
		for(EBeanType bean : EBeanType.values()){
			if(bean.toString().startsWith(cardName))
				result = new BeanCard(bean);
		}
		return result;
	}
}