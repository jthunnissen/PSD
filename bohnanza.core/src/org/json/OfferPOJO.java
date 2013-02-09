package org.json;

import java.util.ArrayList;

/** This class is a data container for an offer in the GUi 
*
* @author Anne van de Venis
* @version 1.0
*/
public class OfferPOJO {

	/**
	 * Player that initiates the offer
	 */
	private String initiator;
	/**
	 * Cards that the initiator wants
	 */
	private ArrayList<CardPOJO> cards;
	/**
	 * Cards that initiator offers
	 */
	private ArrayList<CardPOJO> offer;

	public OfferPOJO(String initiator, ArrayList<CardPOJO> cards, ArrayList<CardPOJO> offer) {
		this.initiator = initiator;
		this.cards = cards;
		this.offer = offer;
	}

	/**
	 * Returns initiator
	 * @return Initiator
	 */
	public String getInitiator() {
		return initiator;
	}

	/**
	 * Returns cards that initiator wants
	 * @return Card that initiator wants
	 */
	public ArrayList<CardPOJO> getCards() {
		return cards;
	}

	/**
	 * Returns cards that initiator offers
	 * @return Cards that the initiator offers
	 */
	public ArrayList<CardPOJO> getOffer() {
		return offer;
	}

}