package states;

import java.util.HashMap;
import java.util.Set;

import main.Game;
import actions.ActionBase;

/**
 * @uml.dependency supplier="main.Action" stereotypes="Standard::Call"
 */
public class TurnState {

	/**
	 * @uml.property name="actions"
	 */
	private HashMap<ActionBase, TurnState> actions = new HashMap<ActionBase, TurnState>();

	/**
	 * @uml.property name="name" readOnly="true"
	 */
	private String name;
	/**
	 * @uml.property name="context"
	 * @uml.associationEnd inverse="currentState:main.Game"
	 */
	private final Game context;

	public TurnState(final Game context, String name) {
		this.context = context;
		this.name = name;
	}

	/**
	 */
	public boolean handle(ActionBase action, String[] args) {
		if (action.handle(args)) {
			context.setCurrentState(actions.get(action));
			return true;
		}
		return false;
	}

	/**
	 */
	public boolean handle(ActionBase action) {
		return handle(action, new String[0]);
	}

	/**
	 */
	public void addActionState(ActionBase action, TurnState state) {
		actions.put(action, state);
	}

	public String getName() {
		return name;
	}
	
	public Set<ActionBase> getActions() {
		return actions.keySet();
	}
}
