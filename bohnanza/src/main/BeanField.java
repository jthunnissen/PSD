package main;

import java.util.ArrayList;

public class BeanField extends Field<BeanCard> {

	@Override
	public boolean addCard(BeanCard card) {
		if (checkCard(card)) {
		return super.addCard(card);
		} else {
			return false;
		}
	}
	
	
	
	/**
	 * Checks if the card that should be added is if the same type as the other cards in this field.
	 * @param card Card that should be added.
	 * @return true if card is of the same type of the other card on this field.
	 */
	private boolean checkCard(BeanCard card) {
		if(cards.size() > 0){
			if(cards.get(0).getType() != card.getType())
				return false;
		}
		return true;
	}
	
}
