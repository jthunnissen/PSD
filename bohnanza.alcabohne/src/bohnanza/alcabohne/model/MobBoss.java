package bohnanza.alcabohne.model;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import bohnanza.core.Action;
import bohnanza.core.BeanCard;
import bohnanza.core.BeanField;
import bohnanza.core.Card;
import bohnanza.core.GameBase;
import bohnanza.core.IBeanType;
import bohnanza.core.IllegalActionException;

public class MobBoss extends AlCabohnePlayer {

	/** @invariant !cards.isEmpty() **/
	private BeanField field = new BeanField();
	private int autoHarvestSize;

	protected MobBoss(String name, int autoHarvestSize) {
		super(name, false);
		this.autoHarvestSize = autoHarvestSize;
	}

	public List<BeanCard> getCards() {
		return field.getCards();
	}

	public void addCard(BeanCard card) throws IllegalActionException {
		field.addCard(card);
		harvest();
	}

	/** @require cards != null
	 * @return the type of bean this mob boss is collecting */
	public IBeanType getBeanType() {
		return field.getBeanType();
	}

	/** Harvest the field if preconditions are satisfied */
	public void harvest() throws IllegalActionException {
		if(field.getSize() >= autoHarvestSize) {
			harvestField(field);
		}
	}

	@Override
	public JSONObject toJSON(Collection<Class<? extends Action<? extends GameBase>>> list, HashMap<Integer, Card> cardIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
