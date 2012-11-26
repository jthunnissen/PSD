package main;

import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class Trade extends Action {

	public Trade(Game game, Player player) {
		super(game, player);
	}
	
	/**Trade or donate cards
	 * recieve can be null, these means the cards are donated
	 * @require give != null
	 */
	@Override
	public boolean handle(Object[] args) {
		Player otherPlayer = (Player) args[0];
		List<Card> give = (List<Card>) args[1];
		List<Card> receive = (List<Card>) args[2];
		boolean result = true;
		if(!player.isValidTrade(give,receive,true) && otherPlayer.isValidTrade(receive, give, false)) result = false;
		else try {
			player.trade(give, receive, true);
			otherPlayer.trade(receive, give, false);
		} catch (IllegalOperationException e) {
			result = false;
		}
		return result;
	}

}
