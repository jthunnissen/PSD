package states;
import actions.*;
import main.*;

public class CurrentPlayerPlantState extends TurnState {

	public CurrentPlayerPlantState(Game context, Player activePlayer) {
		super(context);
	}
	
	private boolean hasOtherPlayersCardsUp() {
		for (Player p : context.getPlayers()) {
			if (p == context.getActivePlayer()) continue;
			if (p.getFaceUpCards().size() > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean handled(Action action) {
		// TODO Auto-generated method stub
		
	/*	if (getCurrentPlayer().getFaceUpCards().size() > 0 ) {
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
		}*/
		
		return false;
	}
}

