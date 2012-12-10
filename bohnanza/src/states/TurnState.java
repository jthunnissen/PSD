<<<<<<< HEAD
package states;

import java.util.HashMap;
import main.Game;
import main.IllegalActionException;
import main.Player;
import java.util.Map;
import actions.ActionBase;

/**
 * @uml.dependency supplier="main.Action" stereotypes="Standard::Call"
 */
public abstract class TurnState {

	/**
	 * @uml.property name="playeractions"
	 */
	private Map<Player,Map<ActionBase, TurnState>> actions = new HashMap<Player, Map<ActionBase, TurnState>>();

	/**
	 * @uml.property name="stateAdvance"
	 */
	private StateAdvance stateAdvance;
	
	/**For developer reference
	 * @uml.property name="name" readOnly="true"
	 */
	private final String name;
	/**
	 * @uml.property name="context"
	 * @uml.associationEnd inverse="currentState:main.Game"
	 */
	private final Game context;
	
	/**
	 * @return Returns the context.
	 * @uml.property name="context"
	 */
	public Game getContext() {
		return context;
	}
	
	public TurnState(final Game context) {
		this.context = context;
		this.stateAdvance = new AlwaysAdvance();
	}

	/**
	 * @throws IllegalActionException 
	 */
	public final void handle(ActionBase action, String[] args) throws IllegalActionException {
		action.handle(args);
		TurnState nextState = actions.get(action);
		nextState.actions.clear();
		nextState.buildStateMapping();
		getContext().setCurrentState(nextState);
	}

	/** Execute action for player and advance to next state if appropriate */
	public void performAction(Player player, ActionBase action, Object[] args) throws IllegalActionException {
		if(!actions.get(player).containsKey(action)) throw new IllegalActionException("Action not available");
		action.handle(player, args);
		if(stateAdvance.canAdvance()) context.setCurrentState(actions.get(player).get(action));
	}

	/**
	 */
	public void addActionState(Player player, ActionBase action, TurnState state) {
		actions.get(player).put(action, state);
	}
	
	public abstract void buildStateMapping();
	
	public Set<ActionBase> getActions() {
		return actions.keySet();
	}

	public Game getContext() {
		return context;
	}
	
	protected final Player getCurrentPlayer() {
		return context.getCurrentPlayer();
	}
}
=======
package states;

import java.util.HashMap;
import java.util.Set;

import main.Game;
import main.IllegalActionException;
import main.Player;
import actions.ActionBase;

/**
 * @uml.dependency supplier="main.Action" stereotypes="Standard::Call"
 */
public abstract class TurnState {

	/**
	 * @uml.property name="actions"
	 */
	private HashMap<ActionBase, TurnState> actions = new HashMap<ActionBase, TurnState>();

	/**
	 * @uml.property name="context"
	 * @uml.associationEnd inverse="currentState:main.Game"
	 */
	private final Game context;

	public TurnState(final Game context) {
		this.context = context;
	}

	/**
	 * @throws IllegalActionException 
	 */
	public final void handle(ActionBase action, String[] args) throws IllegalActionException {
		action.handle(args);
		TurnState nextState = actions.get(action);
		nextState.actions.clear();
		nextState.buildStateMapping();
		getContext().setCurrentState(nextState);
	}

	/**
	 */
	protected void addActionState(ActionBase action, TurnState state) {
		actions.put(action, state);
	}
	
	public abstract void buildStateMapping();
	
	public Set<ActionBase> getActions() {
		return actions.keySet();
	}

	public Game getContext() {
		return context;
	}
	
	protected final Player getCurrentPlayer() {
		return context.getCurrentPlayer();
	}
}
>>>>>>> master
