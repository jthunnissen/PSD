package states;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import main.*;
import actions.*;

/**
 * @uml.dependency supplier="main.Action" stereotypes="Standard::Call"
 */
public abstract class TurnState {

	/**
	 * @uml.property name="actions"
	 */
	protected List<Action> actions = new ArrayList<Action>();
	
	/**
	 * @uml.property name="transitions"
	 */

	protected Map<Action, Class<TurnState>> transitions = new HashMap<Action, Class<TurnState>>();
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

	public TurnState(final Game context, final Player activePlayer) {
		this.context = context;
		this.activePlayer = activePlayer;
	}

	/**
	 * @throws IllegalActionException 
	 */
	public final void handle(Action action, String[] args) throws IllegalActionException {
		action.handle(args);
		
		TurnState nextState;
		try {
			nextState = transitions.get(action).getConstructor().newInstance(context, activePlayer);
			getContext().setCurrentState(nextState);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 */
	protected void addActionState(Action action, Class<TurnState> state) {
		transitions.put(action, state);
	}
	
	protected void addActionState(Action action, TurnState state) {
		//TODO: remove this method
	}
	
	protected Set<Action> getActions() {
		return transitions.keySet();
	}

	public Game getContext() {
		return context;
	}
	
	protected final Player getCurrentPlayer() {
		return context.getCurrentPlayer();
	}
}
