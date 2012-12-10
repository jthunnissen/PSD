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
