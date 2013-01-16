package bohnanza.standard.core;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import bohnanza.standard.core.actions.*;
import bohnanza.standard.core.states.*;

public class GameFactory {

	/**
	 * @uml.property name="singleton" readOnly="true"
	 */
	private static final GameFactory singleton = new bohnanza.standard.core.GameFactory();
	
	private HashMap<Class<? extends TurnState>, ArrayList<Transition>> stateTransitions = new HashMap<Class<? extends TurnState>, ArrayList<Transition>>();
	
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
		return getTurnState(PlantState.class, game);
	}

	public ArrayList<Card> getGameDeck() {
		ArrayList<Card> deck = getStandardDeck();
		return deck;
	}
	
	public TurnState getTurnState(Class<? extends TurnState> state, Game game) {
		try {
			TurnState newState = state.getConstructor(Game.class).newInstance(game);
			if(stateTransitions.containsKey(state)) {
				for(Transition transition : stateTransitions.get(state)) {
					newState.addTransition(transition.action, transition.state);
				}
			}
			return newState;
		} catch (NoSuchMethodException | SecurityException | InstantiationException 
				| IllegalAccessException | IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected void fillStateTransistions() {
		addTransition(StartState.class, NextPlayer.class, PlantState.class);
		addTransition(PlantState.class, NextPhase.class, TradeState.class);
		addTransition(TradeState.class, NextPhase.class, SecondPlantState.class);
		addTransition(SecondPlantState.class, PlantAsideBean.class, DrawState.class);
		addTransition(DrawState.class, DrawCards.class, StartState.class);
	}
	
	protected final boolean addTransition(Class<? extends TurnState> fromState, Class<? extends Action> action, Class<? extends TurnState> toState) {
		ArrayList<Transition> transactions;
		if(stateTransitions.containsKey(fromState)) {
			transactions = stateTransitions.get(fromState);
		} else {
			transactions = new ArrayList<Transition>();
			stateTransitions.put(fromState, transactions);
		}
		return(transactions.add(new Transition(action, toState)));
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
	
	protected final class Transition {
		private final Class<? extends Action> action;
		private  final Class<? extends TurnState> state;
		
		protected Transition(Class<? extends Action> action, Class<? extends TurnState> state) {
			this.action = action;
			this.state = state;
		}
	}

}
