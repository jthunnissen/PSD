package main;



/**
 * @uml.dependency   supplier="main.TurnState" stereotypes="Standard::Create"
 * @uml.dependency   supplier="main.Action" stereotypes="Standard::Create"
 */
public class GameFactory {

	/**
	 * @uml.property  name="singleton" readOnly="true"
	 */
	private static final GameFactory singleton = new main.GameFactory();


	/**
	 */
	private GameFactory(){
	}



	/**
	 */
	public static GameFactory getInstance(){
		return singleton;
	}




	/**
	 */
	public TurnState createGameStates(Game game){
		new TurnState();
		Action a = new Trade();
		return null;
	}

}
