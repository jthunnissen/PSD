package main;

import java.util.ArrayList;

/**
 * @uml.dependency supplier="main.TurnState" stereotypes="Standard::Create"
 * @uml.dependency supplier="main.Action" stereotypes="Standard::Create"
 */
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

	/**
	 */
	public TurnState createGameStates(Game game) {
		new TurnState(game, "newstate");
		Action a = new Trade(game, game.getPlayers().get(0));
		return null;
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
