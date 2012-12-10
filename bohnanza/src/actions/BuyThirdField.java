<<<<<<< HEAD
package actions;
import main.*;

public class BuyThirdField extends ActionBase {


	public BuyThirdField(Game game, Player player) {
		super(game, player);
	}
	
	public BuyThirdField(Game game) {
		super(game);
	}
	
	@Override
	/**
	 * Plants first card in a Player's hand in a specified field
	 */
	public void handle(Object[] args) throws IllegalActionException {
		player.buyThirdField();
	}

}
=======
package actions;
import main.*;

public class BuyThirdField extends ActionBase {


	public BuyThirdField(Game game, Player player) {
		super(game, player);
	}
	
	public BuyThirdField(Game game) {
		super(game);
	}
	
	@Override
	/**
	 * Plants first card in a Player's hand in a specified field
	 */
	public void handle(Object[] args) throws IllegalActionException {
		player.buyThirdField();
	}

}
>>>>>>> master
