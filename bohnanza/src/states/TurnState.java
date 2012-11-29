package states;

import java.util.HashMap;

import main.Game;
import main.Player;

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
	 * @uml.property name="currentPlayer"
	 * @uml.associationEnd inverse="turnState:main.Player"
	 */
	private Player currentPlayer;
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
		return false;
	}

	/**
	 */
	public boolean handle(ActionBase action) {
		return false;
	}

	/**
	 */
	public void addActionState(ActionBase action, TurnState state) {
	}

	public String getName() {
		return name;
	}

	/**
	 * Getter of the property <tt>currentPlayer</tt>
	 * 
	 * @return Returns the currentPlayer.
	 * @uml.property name="currentPlayer"
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Setter of the property <tt>currentPlayer</tt>
	 * 
	 * @param currentPlayer
	 *            The currentPlayer to set.
	 * @uml.property name="currentPlayer"
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Getter of the property <tt>context</tt>
	 * 
	 * @return Returns the context.
	 * @uml.property name="context"
	 */
	public Game getContext() {
		return context;
	}

}