package states;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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
	private Map<Action, Class<TurnState>> actions = new HashMap<Action, Class<TurnState>>();

	/**
	 * @uml.property name="context"
	 * @uml.associationEnd inverse="currentState:main.Game"
	 */
	protected final Game context;
	
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
			nextState = actions.get(action).getConstructor().newInstance(context, activePlayer);
			getContext().setCurrentState(nextState);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 */
	protected void addActionState(Action action, Class<TurnState> state) {
		actions.put(action, state);
	}
	
	protected void addActionState(Action action, TurnState state) {
		//actions.put(action, state);
	}
	
	protected Set<Action> getActions() {
		return actions.keySet();
	}

	public Game getContext() {
		return context;
	}
	
	protected final Player getCurrentPlayer() {
		return context.getCurrentPlayer();
	}
}
