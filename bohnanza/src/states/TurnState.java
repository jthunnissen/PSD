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
	private Map<Player, List<Class<? extends Action>>> actions = new HashMap<Player, List<Class<? extends Action>>>();
	
	/**
	 * @uml.property name="transitions"
	 */
	private Map<Class<? extends Action>, TurnState> transitions = new HashMap<Class<? extends Action>, TurnState>();
	
	/**
	 * @uml.property name="context"
	 * @uml.associationEnd inverse="currentState:main.Game"
	 */
	protected final Game context;

	public TurnState(final Game context) {
		assert actions != null;
		this.context = context;
	}

	public final void handle(Action action) throws IllegalActionException {
		List<Class<? extends Action>> playerActions =  actions.get(action.getInitiator());
		if(playerActions == null || !playerActions.contains(action.getClass())) throw new IllegalActionException("Action not permitted for this player in current state");
		action.handle();
		if(handled(action)) {
			try {
				TurnState nextState = transitions.get(action);
				context.setCurrentState(nextState);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Class<? extends Action>> getActions(Player player) {
		return actions.get(player);
	}

	/**
	 * Update internal state of this TurnState, called after action was successfully executed with parameters args
	 * @return true if action advances the game to a new TurnState
	 */
	protected abstract boolean handled(Action action);

	/**Adds action to the list of possible actions for initiator in the current state.*/
	protected void addAction(Player initiator, Class<? extends Action> action) {
		actions.get(initiator).add(action);
	}
	
	/**Adds action to the list of possible actions for the active player in the current state.*/
	protected void addAction(Class<? extends Action> action) {
		actions.get(context.getActivePlayer()).add(action);
	}
	
	/**Remove action from the list of possible actions for the active player in the curren state.*/
	protected void removeAction(Class<? extends Action> action) {
		actions.get(context.getActivePlayer()).remove(action);
	}
	
	/**Remove action from the list of possible actions for initiator in the curren state.*/
	protected void removeAction(Player initiator, Class<? extends Action> action) {
		actions.get(initiator).remove(action);
	}
	
	/**To be used only by the game factory. Adds state as the next state the game will be in if action ends the current state.*/
	public void addTransition(Class<? extends Action> action, TurnState state) {
		transitions.put(action, state);
	}
}
