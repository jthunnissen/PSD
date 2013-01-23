package bohnanza.alcabohne.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import bohnanza.core.*;

public class MobBoss extends AlCabohnePlayer {

	/** @invariant !cards.isEmpty() **/
	private List<BeanCard> cards = new ArrayList<BeanCard>();

	protected MobBoss(String name) {
		super(name, false);
	}

	public List<BeanCard> getCards() {
		return cards;
	}

	public void addCard(BeanCard card) {
		cards.add(card);
	}

	/** @require cards != null
	 * @return the type of bean this mob boss is collecting */
	public IBeanType getBeanType() {
		return cards.get(0).getType();
	}

	@Override
	public JSONObject toJSON(List<Class<? extends Action<? extends GameBase>>> list, HashMap<Integer, Card> cardIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
