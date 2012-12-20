package actions;
import main.*;

public class PlantBean extends Action {

	private final BeanCard card;
	private final BeanField field;
	
	public PlantBean(Game game, Player initiator, BeanCard card, BeanField field) {
		super(game, initiator);
		this.card = card;
		this.field = field;
	}
	
	@Override
	public void handle() throws IllegalActionException {
		initiator.plantBean(card, field);
	}
}
