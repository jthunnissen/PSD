package actions;
import java.util.List;
import main.*;

public class InitTradeOrDonation extends Action {

	private final Player otherPlayer;
	private final List<Card> give;
	private final List<Card> receive;
	
	public InitTradeOrDonation(Game game, Player initiator, Player otherPlayer, List<Card> give, List<Card> receive) {
		super(game, initiator);
		this.otherPlayer = otherPlayer;
		this.give = give;
		this.receive = receive;
	}
	
	/**Trade or donate cards
	 * recieve can be null, these means the cards are donated
	 * @require give != null
	 */
	@Override
	public void handle() throws IllegalActionException {
		if(initiator.isValidTrade(give,receive,true) && otherPlayer.isValidTrade(receive, give, false)) {
			initiator.trade(give, receive, true);
			otherPlayer.trade(receive, give, false);
		}
	}
}
