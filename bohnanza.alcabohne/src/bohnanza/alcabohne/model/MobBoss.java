package bohnanza.alcabohne.model;
import java.util.ArrayList;
import java.util.List;
import bohnanza.core.*;

public class MobBoss extends AlCabohnePlayer {

	List<BeanCard> cards = new ArrayList<BeanCard>();
	
	protected MobBoss(String name) {
		super(name, false);
	}
	
	public List<BeanCard> getCards() {
		return cards;
	}
	
	public void addCard(BeanCard card) {
		cards.add(card);
	}

}
