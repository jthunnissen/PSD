/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author       Damiaan
 * @uml.dependency   supplier="main.GameFactory" stereotypes="Standard::Call"
 */
public class Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

		
		/**
		 */
		public Game(int players){
			GameFactory.getInstance().createGameStates(this);
		}


		/**
		 * @uml.property  name="currentState"
		 * @uml.associationEnd  inverse="game:main.TurnState"
		 */
		private TurnState currentState = null;


		/**
		 * Getter of the property <tt>currentState</tt>
		 * @return  Returns the currentState.
		 * @uml.property  name="currentState"
		 */
		public TurnState getCurrentState() {
			return currentState;
		}


		/**
		 * Setter of the property <tt>currentState</tt>
		 * @param currentState  The currentState to set.
		 * @uml.property  name="currentState"
		 */
		public void setCurrentState(TurnState currentState) {
			this.currentState = currentState;
		}


		/** 
		 * @uml.property name="players"
		 * @uml.associationEnd multiplicity="(0 -1)" inverse="game:main.Player"
		 */
		private Collection players = new java.util.ArrayList();


		/** 
		 * Getter of the property <tt>players</tt>
		 * @return  Returns the players.
		 * @uml.property  name="players"
		 */
		public Collection getPlayers() {
			return players;
		}


		/** 
		 * Setter of the property <tt>players</tt>
		 * @param players  The players to set.
		 * @uml.property  name="players"
		 */
		public void setPlayers(ArrayList players) {
			this.players = players;
		}


		/** 
		 * @uml.property name="drawDesk"
		 * @uml.associationEnd multiplicity="(0 -1)" inverse="game:main.Card"
		 */
		private Collection drawDesk;


		/** 
		 * Getter of the property <tt>drawDesk</tt>
		 * @return  Returns the drawDesk.
		 * @uml.property  name="drawDesk"
		 */
		public Collection getDrawDesk() {
			return drawDesk;
		}


		/**
		 * @uml.property  name="discardPile"
		 * @uml.associationEnd  multiplicity="(0 -1)" inverse="game:main.Card"
		 */
		private Collection discardPile = new java.util.ArrayList();


		/**
		 * Getter of the property <tt>discardPile</tt>
		 * @return  Returns the discardPile.
		 * @uml.property  name="discardPile"
		 */
		public Collection getDiscardPile() {
			return discardPile;
		}


		/**
		 * Setter of the property <tt>discardPile</tt>
		 * @param discardPile  The discardPile to set.
		 * @uml.property  name="discardPile"
		 */
		public void setDiscardPile(Collection discardPile) {
			this.discardPile = discardPile;
		}


		/** 
		 * Setter of the property <tt>drawDesk</tt>
		 * @param drawDesk  The drawDesk to set.
		 * @uml.property  name="drawDesk"
		 */
		public void setDrawDesk(Collection drawDesk) {
			this.drawDesk = drawDesk;
		}

}
