package bohnanza.core;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import bohnanza.core.shared.actions.BuyBeanField;
import bohnanza.core.shared.actions.Harvest;

public abstract class TurnState<Game extends GameBase> {

	/** @uml.property name="actions" */
	private Map<Player, Collection<Class<? extends Action<? extends GameBase>>>> actions = new HashMap<Player, Collection<Class<? extends Action<? extends GameBase>>>>();

	/** @uml.property name="transitions" */
	private Map<Class<? extends Action<? extends GameBase>>, Class<? extends TurnState<Game>>> transitions = new HashMap<Class<? extends Action<? extends GameBase>>, Class<? extends TurnState<Game>>>();

	/** @uml.property name="context" */
	protected final Game context;

	public TurnState(final Game context) {
		this.context = context;
		for(Player player : context.getPlayers()) {
			actions.put(player, new ArrayList<Class<? extends Action<? extends GameBase>>>());
		}
	}

	/** Adds the actions Harvest and BuyBeanField for each player */
	protected void addDefaultActions() {
		for(Player player : context.getPlayers()) {
			actions.get(player).add(Harvest.class);
			actions.get(player).add(BuyBeanField.class);
		}
	}

	/** Check whether action is a allowed and execute it */
	public final void handle(Action<? extends GameBase> action, AbstractFactory factory) throws IllegalActionException {
		Collection<Class<? extends Action<? extends GameBase>>> playerActions = actions.get(action.getInitiator());
		if(playerActions == null || !playerActions.contains(action.getClass()))
			throw new IllegalActionException("Action not permitted for this player in current state");
		action.handle();
		// if(handled(action) && transitions.containsKey(action)) {
		if(handled(action)) {
			getNextState(action, factory);
		} else
			System.out.println("Contains next state :" + String.valueOf(transitions.containsKey(action)));
		System.out.println("State: " + this.getClass().getName());
	}

	private void getNextState(Action<? extends GameBase> action, AbstractFactory factory) {
		try {
			TurnState<Game> nextState = factory.getTurnState(transitions.get(action.getClass()), context);
			context.setCurrentState(nextState);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Collection<Class<? extends Action<? extends GameBase>>> getActions(Player player) {
		return actions.get(player);
	}

	/** Update internal state of this TurnState, called after action was
	 * successfully executed with parameters args
	 * @return true if action advances the game to a new TurnState */
	protected abstract boolean handled(Action<? extends GameBase> action);

	/** Adds action to the list of possible actions for initiator in the current
	 * state. */
	protected void addAction(Player initiator, Class<? extends Action<? extends GameBase>> action) {
		actions.get(initiator).add(action);
	}

	/** Adds action to the list of possible actions for the active player in the
	 * current state. */
	protected void addAction(Class<? extends Action<? extends GameBase>> action) {
		actions.get(context.getActivePlayer()).add(action);
	}

	/** Remove action from the list of possible actions for the active player in
	 * the current state. */
	protected void removeAction(Class<? extends Action<? extends GameBase>> action) {
		actions.get(context.getActivePlayer()).remove(action);
	}

	/** Remove action from the list of possible actions for initiator in the
	 * current state. */
	protected void removeAction(Player initiator, Class<? extends Action<? extends GameBase>> action) {
		actions.get(initiator).remove(action);
	}

	/** Remove all actions in the current state. */
	protected void removeAllActions() {
		for(Collection<Class<? extends Action<? extends GameBase>>> playeractions : actions.values()) {
			playeractions.clear();
		}
	}

	/** To be used only by the game factory. Adds state as the next state the
	 * game will be in if action ends the current state. */
	public void addTransition(Class<? extends Action<? extends GameBase>> action, Class<? extends TurnState<Game>> state) {
		transitions.put(action, state);
	}
}
