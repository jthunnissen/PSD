package states;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.*;
import actions.*;

public abstract class TurnState {

	/**
	 * @uml.property name="actions"
	 */
	private List<Action> actions = new ArrayList<Action>();
	
	/**
	 * @uml.property name="transitions"
	 */
	private Map<Action, Class<TurnState>> transitions = new HashMap<Action, Class<TurnState>>();
	
	/**
	 * @uml.property name="context"
	 * @uml.associationEnd inverse="currentState:main.Game"
	 */
	protected final Game context;
	
	/**
	 * @uml.property name="active player"
	 * @uml.associationEnd inverse="currentState:main.Player"
	 */
	protected final Player activePlayer;

	public TurnState(final Game context, final Player activePlayer, List<Action> actions) {
		assert actions != null;
		this.context = context;
		this.activePlayer = activePlayer;
		this.actions = actions;
	}

	public final void handle(Action action, String[] args) throws IllegalActionException {
		if(!actions.contains(action)) throw new IllegalActionException("Action not permitted in current state");
		action.handle(args);
		if(handled(action, args)) {
			try {
				TurnState nextState = transitions.get(action).getConstructor().newInstance(context, activePlayer);
				context.setCurrentState(nextState);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Update internal state of this TurnState, called after action was successfully executed with parameters args
	 * @return true if action advances the game to a new TurnState
	 */
	protected abstract boolean handled(Action action, Object[] args);

	/**Adds state as the next state when action ends the current state.*/
	protected void addAction(Action action) {
		actions.add(action);
	}
	
	/**Adds action to the list of possible actions.*/
	protected void removeAction(Action action) {
		transitions.remove(action);
	}
	
	/**Removes action form the list of possible actions.*/
	protected void addTransition(Action action, Class<TurnState> state) {
		transitions.put(action, state);
	}
}
