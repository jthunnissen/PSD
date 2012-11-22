package main;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class BeanField extends Field<BeanCard> {

	@Override
	public boolean addCard(BeanCard card) {
		if (checkCard(card)) {
		return super.addCard(card);
		} else {
			return false;
		}
	}
	
	private boolean checkCard(BeanCard card) {
		throw new NotImplementedException();
	}
	
}
