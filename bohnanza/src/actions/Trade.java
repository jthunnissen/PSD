package actions;
import java.util.List;
import main.*;

public class Trade extends Action {

	public Trade(Game game) {
		super(game);
	}
	
	/**Trade or donate cards
	 * recieve can be null, these means the cards are donated
	 * @require give != null
	 */
	@Override
	public void handle(Object[] args) throws IllegalActionException {
		Player otherPlayer = (Player) args[0];
		List<Card> give = (List<Card>) args[1];
		List<Card> receive = (List<Card>) args[2];
		if(player.isValidTrade(give,receive,true) && otherPlayer.isValidTrade(receive, give, false)) {
			player.trade(give, receive, true);
			otherPlayer.trade(receive, give, false);
		}
	}
}
