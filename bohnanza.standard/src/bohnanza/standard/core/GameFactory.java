package bohnanza.standard.core;
import java.util.ArrayList;

import bohnanza.standard.core.actions.*;
import bohnanza.standard.core.states.*;

public class GameFactory {

	/**
	 * @uml.property name="singleton" readOnly="true"
	 */
	private static final GameFactory singleton = new bohnanza.standard.core.GameFactory();

	/**
	 */
	private GameFactory() {
	}

	/**
	 */
	public static GameFactory getInstance() {
		return singleton;
	}

	/**Links the different states that compose a turn together
	 * @return the start state of the game
	 */
	public TurnState buildTurnStatespace(Game game, Player activePlayer) {
		TurnState startState = new TurnStart(game);
		TurnState plantState = new PlantState(game);
		startState.addTransition(NextPlayer.class, plantState);
		TurnState tradedonateState = new TradeOrDonateState(game);
		plantState.addTransition(NextPhase.class, tradedonateState);
		//TODO: implement full hierarchy
		
		return new PlantState(game);
	}

	public ArrayList<Card> getGameDeck() {
		ArrayList<Card> deck = getStandardDeck();
		return deck;
	}

	private ArrayList<Card> getStandardDeck() {
		ArrayList<Card> standardDeck = new ArrayList<Card>();
		for (EBeanType e : EBeanType.values()) {
			for (int i = 0; i < e.numberOfCards(); i++) {
				standardDeck.add(new BeanCard(e));
			}
		}
		return standardDeck;
	}

}
