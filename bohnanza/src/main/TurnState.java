package main;
import java.util.HashMap;

/**
 * @uml.dependency   supplier="main.Action" stereotypes="Standard::Call"
 */
public class TurnState {

	/**
	 * @uml.property  name="actions"
	 */
	private HashMap<Action, TurnState> actions = new HashMap<Action, TurnState>();


	/**
	 */
	public boolean handle(Action action, String[] args){
		return false;
	}


	/**
	 * @uml.property   name="name" readOnly="true"
	 */
	private final String name = "some name";



	/**
	 */
	public void addActionState(Action action, TurnState state){
	}



	public String getName() {
		return name;
	}


	/**
	 * @uml.property  name="currentPlayer"
	 * @uml.associationEnd  inverse="turnState:main.Player"
	 */
	private Player currentPlayer = null;


	/**
	 * Getter of the property <tt>currentPlayer</tt>
	 * @return  Returns the currentPlayer.
	 * @uml.property  name="currentPlayer"
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}



	/**
	 * Setter of the property <tt>currentPlayer</tt>
	 * @param currentPlayer  The currentPlayer to set.
	 * @uml.property  name="currentPlayer"
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}




	/**
	 */
	public boolean handle(Action action){
		return false;	
	}


	/**
	 * @uml.property  name="context"
	 * @uml.associationEnd  inverse="currentState:main.Game"
	 */
	private Game context;


	/**
	 * Getter of the property <tt>context</tt>
	 * @return  Returns the context.
	 * @uml.property  name="context"
	 */
	public Game getContext() {
		return context;
	}



	/**
	 * Setter of the property <tt>context</tt>
	 * @param context  The context to set.
	 * @uml.property  name="context"
	 */
	public void setContext(Game context) {
		this.context = context;
	}

}
