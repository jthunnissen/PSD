package main;

import java.util.ArrayList;

public class BeanField extends Field {

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.Field#addCard(main.Card)
	 */
	@Override
	public boolean addCard(Card card) {
		return card instanceof BeanCard ? addCard((BeanCard) card) : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.Field#harvest()
	 */
	@Override
	public ArrayList<Card> harvest() {
		// TODO Handle here points if possible
		return super.harvest();
	}

	public boolean addCard(BeanCard card) {
		return checkCard(card) ? super.addCard(card) : false;
	}

	/**
	 * Checks if the card that should be added is if the same type as the other
	 * cards in this field.
	 * 
	 * @param card
	 *            Card that should be added.
	 * @return true if card is of the same type of the other card on this field.
	 */
	private boolean checkCard(BeanCard card) {
		if (cards.size() > 0) {
			if (getTypeOf(card) != card.getType())
				return false;
		}
		return true;
	}
	
	private EBeanType getTypeOf(Card card) {
		return ((BeanCard) cards.get(0)).getType();
	}

}
