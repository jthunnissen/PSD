package main;
import java.util.HashMap;

/**
 * @uml.dependency   supplier="main.Action"
 */
public class TurnState {

	/**
	 * @uml.property  name="actions"
	 */
	private HashMap<Action, TurnState> actions;

		
		/**
		 */
		public boolean handle(Action action){
			return false;
		}


		/**
		 * @uml.property  name="name" readOnly="true"
		 */
		private final String name = "some name";
		/** 
		 * @uml.property name="context"
		 * @uml.associationEnd readOnly="true" multiplicity="(1 1)" inverse="turnState:main.Game"
		 */
		private Game context = Game.getInstance();


		/** 
		 * @uml.property name="currentPlayer"
		 * @uml.associationEnd multiplicity="(1 1)" inverse="turnState:main.Player"
		 */
		private Player currentPlayer = context.getPlayer(1);

}
