<<<<<<< HEAD
package org.json;

import java.util.ArrayList;

import javafx.scene.image.Image;
import bohnanza.standard.core.Card;

public class OfferPOJO {

	private String initiator;
	private ArrayList<CardPOJO> cards;
	private ArrayList<CardPOJO> offer;
	
	public OfferPOJO(String initiator, ArrayList<CardPOJO> cards, ArrayList<CardPOJO> offer){
		this.initiator = initiator;
		this.cards = cards;
		this.offer = offer;
	}
	
	public String getInitiator(){
		return initiator;
	}
	
	public ArrayList<CardPOJO> getCards(){
		return cards;
	}
	
	public ArrayList<CardPOJO> getOffer(){
		return offer;
	}
}
=======
package org.json;

import java.util.ArrayList;

import javafx.scene.image.Image;
import bohnanza.standard.core.Card;

public class OfferPOJO {

	private String initiator;
	private ArrayList<CardPOJO> cards;
	private ArrayList<CardPOJO> offer;
	
	public OfferPOJO(String initiator, ArrayList<CardPOJO> cards, ArrayList<CardPOJO> offer){
		this.initiator = initiator;
		this.cards = cards;
		this.offer = offer;
	}
	
	public String getInitiator(){
		return initiator;
	}
	
	public ArrayList<CardPOJO> getCards(){
		return cards;
	}
	
	public ArrayList<CardPOJO> getOffer(){
		return offer;
	}
}
>>>>>>> 48dffdda767d187a09c9e7be9bd524ba6c5b9394
