package main;
import java.util.ArrayList;
import states.*;
import actions.*;

public class GameFactory {

	/**
	 * @uml.property name="singleton" readOnly="true"
	 */
	private static final GameFactory singleton = new main.GameFactory();

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
		//TODO: fix this!
		//TurnStart.addTransition(NextPlayer.class, PlantState.class);
		//PlantState.addTransition(NextPhase.class, TradeOrDonateState.class);
		//etc
		
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
