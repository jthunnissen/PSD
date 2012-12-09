package main;

import java.util.ArrayList;
import java.util.Iterator;

import states.TurnState;

import actions.ActionBase;
import actions.Harvest;
import actions.PlantBean;
import actions.Trade;

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
		if (game.getPlayers().size() > 2 ) {
			Iterator<Player> playerItr = game.getPlayers().iterator();
			
			Player player1 = playerItr.next();
			
			final ActionBase plantBean = new PlantBean(game, player1);
			final ActionBase harvest = new Harvest(game, player1);
			
			final TurnState plantFirstBeanState = new TurnState(game, "Plant first bean (Player: " + player1.getName() + ")");
			final TurnState plantSecondBeanState = new TurnState(game, "Plant second bean (Player: " + player1.getName() + ")");
			final TurnState plantThirdBeanState = new TurnState(game, "Plant third bean (Player: " + player1.getName() + ")");
			
			plantFirstBeanState.addActionState(plantBean, plantSecondBeanState);
			plantFirstBeanState.addActionState(harvest, plantFirstBeanState);
			
			plantSecondBeanState.addActionState(plantBean, plantThirdBeanState);
			plantSecondBeanState.addActionState(harvest, plantSecondBeanState);
			
			plantSecondBeanState.addActionState(plantBean, plantThirdBeanState);
			plantSecondBeanState.addActionState(harvest, plantThirdBeanState);
			
		}
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
