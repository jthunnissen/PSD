package bohnanza.standard.core.actions;
import bohnanza.standard.core.*;

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
		if(initiator.getHand().indexOf(card)==0)
			initiator.plantBean(card, field);
		else throw new IllegalActionException("Can only plant first card in hand ("+initiator.getHand().get(0).getName()+")");
	}
}
