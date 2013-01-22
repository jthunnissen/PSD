package bohnanza.core;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbstractFactory {
	
	private HashMap<Class<? extends TurnState>, ArrayList<Transition>> stateTransitions = new HashMap<Class<? extends TurnState>, ArrayList<Transition>>();
	private final IBeanType[] beanTypes;
	private Class<? extends TurnState> startState;
	
	protected AbstractFactory(IBeanType[] beanTypes) {
		this.fillStateTransistions();
		this.beanTypes = beanTypes;
	}
	
	public static AbstractFactory getInstance() {
		return null;
	}
	
	public ArrayList<Card> getGameDeck() {
		ArrayList<Card> deck = getStandardDeck(beanTypes);
		return deck;
	}
	
	/**Links the different states that compose a turn together
	 * @return the start state of the game
	 */
	public TurnState buildTurnStatespace(GameBase game, Player activePlayer) {
		return getTurnState(startState, game);
	}
	
	public void setStartState(Class<? extends TurnState> startState) {
		this.startState = startState;
	}
	
	protected abstract void fillStateTransistions();
	
	public TurnState getTurnState(Class<? extends TurnState> state, GameBase game) {
		try {
			TurnState newState = state.getConstructor(GameBase.class).newInstance(game);
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
	
	private final class Transition {
		private final Class<? extends Action> action;
		private  final Class<? extends TurnState> state;
		
		protected Transition(Class<? extends Action> action, Class<? extends TurnState> state) {
			this.action = action;
			this.state = state;
		}
	}
	
	protected ArrayList<Card> getStandardDeck(IBeanType[] beanTypes) {
		ArrayList<Card> standardDeck = new ArrayList<Card>();
		for (IBeanType bt : beanTypes) {
			for (int i = 0; i < bt.numberOfCards(); i++) {
				standardDeck.add(new BeanCard(bt));
			}
		}
		return standardDeck;
	}

}
