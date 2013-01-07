package actions;
import java.util.List;

import states.OtherPlayersPlantState;
import main.Card;
import main.Game;
import main.IllegalActionException;
import main.Player;

public class AcceptTrade extends Action {

	private final Player otherPlayer;
	private final List<Card> give;
	private final List<Card> receive;
	
	public AcceptTrade(Game game, Player player, Player otherPlayer, List<Card> give, List<Card> receive) {
		super(game, player);
		this.otherPlayer = otherPlayer;
		this.give = give;
		this.receive = receive;
	}

	@Override
	public void handle() throws IllegalActionException {
		initiator.trade(give, receive, true);
		otherPlayer.trade(receive, give, false);
	}

}
