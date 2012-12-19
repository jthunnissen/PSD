package actions;
import main.*;

public class PlantBean extends Action {

	public PlantBean(Game game) {
		super(game);
	}
	
	@Override
	public void handle(Object[] args) throws IllegalActionException {
		BeanCard card = (BeanCard) args[0];
		BeanField field = (BeanField) args[1];
		player.plantBean(card, field);
	}
}
