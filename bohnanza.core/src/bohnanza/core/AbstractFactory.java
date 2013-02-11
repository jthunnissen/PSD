package bohnanza.core;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbstractFactory {

	/**
	 * All possible transitions from the current state
	 */
	private HashMap<Class<? extends TurnState>, ArrayList<Transition>> stateTransitions = new HashMap<Class<? extends TurnState>, ArrayList<Transition>>();
	/**
	 * Beancards that should be included in the game deck
	 */
	private final IBeanType[] beanTypes;
	/**
	 * Initial state in the game
	 */
	private Class<? extends TurnState> startState;

	protected AbstractFactory(IBeanType[] beanTypes) {
		this.fillStateTransistions();
		this.beanTypes = beanTypes;
	}

	/**
	 * Returns game deck
	 * @return Game deck
	 */
	public ArrayList<Card> getGameDeck() {
		ArrayList<Card> deck = getStandardDeck(beanTypes);
		return deck;
	}

	/** Links the different states that compose a turn together
	 * @return the start state of the game */
	public TurnState buildTurnStatespace(BaseGame game) {
		return getTurnState(startState, game);
	}

	/**
	 * Sets given state as start state
	 * @param startState The initial state
	 */
	public void setStartState(Class<? extends TurnState> startState) {
		this.startState = startState;
	}

	/**
	 * Creates all states transition (game flow) of this game
	 */
	protected abstract void fillStateTransistions();

	/**
	 * Return list of possible next states of the game
	 * @param state Current state of the game
	 * @param game Base game
	 * @return List of possible transitions to next state
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TurnState getTurnState(Class<? extends TurnState> state, BaseGame game) {
		try {
			TurnState newState = state.asSubclass(TurnState.class).getConstructor(BaseGame.class).newInstance(game);
			if(stateTransitions.containsKey(state)) {
				for(Transition transition : stateTransitions.get(state)) {
					newState.addTransition(transition.action, transition.state);
				}
			}
			return newState;
		} catch(NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Adds transition to current state
	 * @param fromState State of the game at the start of the transition
	 * @param action Action that initiates the transition
	 * @param toState State of the game after the transition
	 * @return True if transition is successfully added
	 */
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

	/**
	 * Class that respresent an transition
	 */
	private final class Transition {
		/**
		 * Action that initiates the transition
		 */
		private final Class<? extends Action> action;
		/**
		 * State after the transition
		 */
		private final Class<? extends TurnState> state;

		protected Transition(Class<? extends Action> action, Class<? extends TurnState> state) {
			this.action = action;
			this.state = state;
		}
	}

	/**
	 * Creates a gamedeck for the Bohnanza game
	 * @param beanTypes List of all Beancard that should be included in the gamedeck
	 * @return
	 */
	protected ArrayList<Card> getStandardDeck(IBeanType[] beanTypes) {
		ArrayList<Card> standardDeck = new ArrayList<Card>();
		BeanCard card;
		for(IBeanType bt : beanTypes) {
			card = new BeanCard(bt);
			for(int i = 0; i < bt.numberOfCards(); i++) {
				standardDeck.add(card);
			}
		}
		return standardDeck;
	}

}
