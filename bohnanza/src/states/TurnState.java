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
public class TurnState {

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
	
	public TurnState(final Game context, String name) {
		this.context = context;
		this.name = name;
		this.stateAdvance = new AlwaysAdvance();
	}
	
	public TurnState(final Game context, StateAdvance stateAdvance, String name) {
		this.context = context;
		this.name = name;
		this.stateAdvance = stateAdvance;
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

	public String getName() {
		return name;
	}
	
//	public Set<ActionBase> getActions() {
//		return actions.keySet();
//	}
}
