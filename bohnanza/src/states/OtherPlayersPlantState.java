package states;
import actions.*;
import main.*;

public class OtherPlayersPlantState extends TurnState {

	private Player turnStartedPlayer;
	private boolean firstTime;
	
	public OtherPlayersPlantState(Game context, Player activePlayer) {
		super(context, activePlayer);
		// TODO Auto-generated constructor stub
	}
	
	public OtherPlayersPlantState(Game context, Player activePlayer, Player turnStartedPlayer) {
		this(context, activePlayer);
		this.turnStartedPlayer = turnStartedPlayer;
		this.firstTime = true;
	}

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
			addActionState(new DrawCards(getContext()), new PlantState(getContext(),activePlayer));
		} else {
			addActionState(new PlantBean(getContext()), this);
			addActionState(new Harvest(getContext()), this);
			addActionState(new BuyBeanField(getContext()), this);
		}
	}

}
