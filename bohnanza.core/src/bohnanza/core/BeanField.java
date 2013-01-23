package bohnanza.core;

import java.util.ArrayList;

public class BeanField extends Field<BeanCard> {

	@Override
	public ArrayList<BeanCard> harvest() {
		// TODO Handle here points if possible
		return super.harvest();
	}

	/** Checks if the card that should be added is if the same type as the other
	 * cards in this field.
	 * 
	 * @param card Card that should be added.
	 * @return true if card is of the same type of the other card on this field. */
	public boolean checkCard(BeanCard card) {
		if(cards.size() > 0) {
			if(getTypeOf() != card.getType())
				return false;
		}
		return true;
	}

	public IBeanType getTypeOf() {
		return cards.get(0).getType();
	}

}
