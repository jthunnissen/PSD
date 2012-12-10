package states;

import actions.BuyBeanField;
import actions.Draw3Cards;
import actions.Harvest;
import actions.PlantBean;
import main.Game;
import main.Player;

public class OtherPlayersPlantState extends TurnState {

	private Player turnStartedPlayer;
	private boolean firstTime;
	
	public OtherPlayersPlantState(Game context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public OtherPlayersPlantState(Game context, Player turnStartedPlayer) {
		this(context);
		this.turnStartedPlayer = turnStartedPlayer;
		this.firstTime = true;
	}

	@Override
	public void buildStateMapping() {
		if (getCurrentPlayer() == turnStartedPlayer && firstTime) {
			firstTime = false;
			getContext().goToNextPlayer();
		}
		while (getCurrentPlayer().getFaceUpCards().size() == 0) {
			getContext().goToNextPlayer();
			if (getCurrentPlayer() == turnStartedPlayer) break;
		}
		if (getCurrentPlayer() == turnStartedPlayer) {
			addActionState(new Draw3Cards(getContext(), getCurrentPlayer()), new PlantState(getContext()));
		} else {
			addActionState(new PlantBean(getContext()), this);
			addActionState(new Harvest(getContext()), this);
			addActionState(new BuyBeanField(getContext()), this);
		}
	}

}
