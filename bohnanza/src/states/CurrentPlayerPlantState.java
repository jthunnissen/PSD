<<<<<<< HEAD
/**
 * 
 */
package states;

import actions.BuyThirdField;
import actions.Harvest;
import actions.PlantBean;
import actions.SkipAction;
import main.Game;
import main.Player;

/**
 * @author Damiaan
 *
 */
public class CurrentPlayerPlantState extends TurnState {

	/**
	 * @param context
	 */
	public CurrentPlayerPlantState(Game context) {
		super(context);
	}

	/* (non-Javadoc)
	 * @see states.TurnState#buildStateMapping()
	 */
	@Override
	public void buildStateMapping() {
		
		if (getCurrentPlayer().getFaceUpCards().size() > 0 ) {
			addActionState(new BuyThirdField(getContext()), this);
			addActionState(new PlantBean(getContext(), getCurrentPlayer()), this);
			if (getCurrentPlayer().getFields().size() > 0) {
				addActionState(new Harvest(getContext(), getCurrentPlayer()), this);
			}
		} else {
			if (hasOtherPlayersCardsUp()) {
				addActionState(new SkipAction(getContext()), new OtherPlayersPlantState(getContext(), getCurrentPlayer()));
			} else {
				addActionState(new SkipAction(getContext()), new PlantState(getContext()));
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
=======
/**
 * 
 */
package states;

import actions.BuyThirdField;
import actions.Harvest;
import actions.PlantBean;
import actions.SkipAction;
import main.Game;
import main.Player;

/**
 * @author Damiaan
 *
 */
public class CurrentPlayerPlantState extends TurnState {

	/**
	 * @param context
	 */
	public CurrentPlayerPlantState(Game context) {
		super(context);
	}

	/* (non-Javadoc)
	 * @see states.TurnState#buildStateMapping()
	 */
	@Override
	public void buildStateMapping() {
		
		if (getCurrentPlayer().getFaceUpCards().size() > 0 ) {
			addActionState(new BuyThirdField(getContext()), this);
			addActionState(new PlantBean(getContext(), getCurrentPlayer()), this);
			if (getCurrentPlayer().getFields().size() > 0) {
				addActionState(new Harvest(getContext(), getCurrentPlayer()), this);
			}
		} else {
			if (hasOtherPlayersCardsUp()) {
				addActionState(new SkipAction(getContext()), new OtherPlayersPlantState(getContext(), getCurrentPlayer()));
			} else {
				addActionState(new SkipAction(getContext()), new PlantState(getContext()));
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
>>>>>>> master
