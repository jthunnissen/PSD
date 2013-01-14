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
	protected GameFactory() {
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
		TurnState tradedonateState = new TradeState(game);
		plantState.addTransition(NextPhase.class, tradedonateState);
		TurnState secondplantState = new SecondPlantState(game);
		tradedonateState.addTransition(NextPhase.class, secondplantState);
		TurnState drawState = new DrawState(game);
		secondplantState.addTransition(PlantAsideBean.class, drawState);
		drawState.addTransition(DrawCards.class, startState);		
		return plantState;
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
