package states;
import actions.*;
import main.*;

public class CurrentPlayerPlantState extends TurnState {

	/**
	 * @param context
	 */
	public CurrentPlayerPlantState(Game context, Player activePlayer) {
		super(context, activePlayer);
	}

	public void buildStateMapping() {
		
		if (getCurrentPlayer().getFaceUpCards().size() > 0 ) {
			addActionState(new BuyBeanField(getContext()), this);
			addActionState(new PlantBean(getContext()), this);
			if (getCurrentPlayer().getBeanFields().size() > 0) {
				addActionState(new Harvest(getContext()), this);
			}
		} else {
			if (hasOtherPlayersCardsUp()) {
				addActionState(new EndPhase(getContext()), new OtherPlayersPlantState(getContext(), getCurrentPlayer()));
			} else {
				addActionState(new EndPhase(getContext()), new PlantState(getContext(),activePlayer));
			}
		}

	}
	
	private boolean hasOtherPlayersCardsUp() {
		for (Player p : getContext().getPlayers()) {
			if (p == getCurrentPlayer()) continue;
			if (p.getFaceUpCards().size() > 0) {
				return true;
			}
		}
		return false;
	}
}

