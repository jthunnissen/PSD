package bohnanza.standard.core;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bohnanza.standard.core.actions.AcceptTrade;
import bohnanza.standard.core.actions.Action;
import bohnanza.standard.core.actions.BuyBeanField;
import bohnanza.standard.core.actions.DeclineTrade;
import bohnanza.standard.core.actions.DrawCards;
import bohnanza.standard.core.actions.DrawFaceUpCards;
import bohnanza.standard.core.actions.Harvest;
import bohnanza.standard.core.actions.NextPhase;
import bohnanza.standard.core.actions.NextPlayer;
import bohnanza.standard.core.actions.PlantAsideBean;
import bohnanza.standard.core.actions.PlantBean;
import bohnanza.standard.core.actions.ProposeTrade;
import bohnanza.standard.core.actions.SetAsideCard;
import bohnanza.standard.server.Protocol;

/**
 * This class represents a Player
 * @author Anne
 *
 */
public class Player {

	/**
	 * Represents the fields this Player has.
	 * @uml.property  name="fields"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="player:main.Field"
	 */
	private List<BeanField> beanFields = new ArrayList<BeanField>();

	/**
	 * Represents the cards in the hand of this Player.
	 * @uml.property  name="hand"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="player:main.Card"
	 */
	private List<Card> hand = new ArrayList<Card>();

	/**
	 * Represents the treasury of this Player
	 * @uml.property  name="coins"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="player:main.Card"
	 */
	private List<Card> treasury = new ArrayList<Card>();
	
	/** 
	 * Represents face up cards during trade/donate turn phase
	 * @uml.property name="faceUpCards"
	 */
	private List<Card> faceUpCards = new ArrayList<Card>();
	
	/** 
	 * Represents traded/donated/set aside cards during turn phase
	 * @uml.property name="setAsideCards"
	 */
	private List<Card> setAsideCards = new ArrayList<Card>();

	public List<Card> getSetAsideCards() {
		return setAsideCards;
	}
	
	/**
	 * The name from this Player.
	 * @uml.property  name="name"
	 */
	private String name;

	/**
	 * Getter of the property <tt>name</tt>
	 * @return  Returns the name.
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @post getFields.size() == 2
	 * @param name The Player's name
	 */
	public Player(String name) {
		this.name = name;
		this.beanFields.add(new BeanField());
		this.beanFields.add(new BeanField());
	}

	/**
	 * Calculates the score, all the cards in this Players' treasury.
	 * @return score
	 */
	public int calcScore() {
		return this.treasury.size();
	}
		
	public int addCardToTreasury(Card card) {
		this.treasury.add(card);
		return treasury.size();
	}

	/**
	 * Adds a card to the players hand.
	 * @param card Card that will be added the Players' hand
	 * @return true if the Card is successfully added to the Player's hand.
	 */
	public boolean addCardToHand(Card card){
		return hand.add(card);
	}

	/**
	 * Harvest the Players' field and adds cards to the treasury.
	 * @param fieldnr Number of the field that will be harvested.
	 * @return Cards that should be added to the discard pile.
	 * @throws IllegalActionException 
	 */
	public ArrayList<Card> harvastField(Field field) throws IllegalActionException{
		// Check if all fields contain one card 
		if(field.getCards().size() == 1) {
			boolean ok = true;
			for(Field f: beanFields){
				if(f.getCards().size() > 1)
					ok = false;
			}
			if(!ok) {
				throw new IllegalActionException("This field has one card, other fields have more");
			}
		}
		
		
		ArrayList<Card> cards = field.harvest();
		int beano = ((BeanCard) cards.get(0)).getBeanometer(cards.size());
		ArrayList<Card> discard = new ArrayList<Card>();
		for(int i=0; i<cards.size(); i++){
			if(i<beano){
				this.addCardToTreasury(cards.get(i));
			} else {
				discard.add(cards.get(i));
			}
		}
		return discard;
	}

	/**
	 * The first BeanCard in the Players' hand will be planted on a Players' field.
	 * @param fieldnr Number of the field where the card will be planted.
	 * @return true if the BeanCard is successfully planted in the Players' field.
	 * @throws IllegalActionException 
	 */
	public void plantBean(BeanCard bean, BeanField field) throws IllegalActionException {
		if(!setAsideCards.isEmpty()){
			if(!setAsideCards.remove(bean)) throw new IllegalActionException("Bean must be set aside");
		}
		else if(!hand.isEmpty() && hand.get(0)==bean) hand.remove(0);
		else throw new IllegalActionException("Player cannot plant this bean");
		
		if(beanFields.contains(field)) field.addCard(bean);
		else throw new IllegalActionException("Player does not own field");
	}

	/**
	 * Getter of the Players' hand
	 * @return The hand of this Player.
	 */
	public List<Card> getHand(){
		return hand;
	}
	
	/**
	 * Getter of the Players' fields
	 * @return The fields of this Player.
	 */
	public List<BeanField> getBeanFields(){
		return beanFields;
	}
	
	/**
	 * This methods represents the Player's action to buy a third Field.
	 * @return true if player has enough money and field is successfully bought.
	 * @throws IllegalActionException 
	 */
	public boolean buyField() throws IllegalActionException {
		if(this.beanFields.size() == 3) {
			throw new IllegalActionException("Player already has 3 fields");
		}
		if(this.calcScore() < 3) 
			throw new IllegalActionException("Player has not enough money. Has: "+this.calcScore());
		BeanField thirdField = new BeanField();
		beanFields.add(thirdField);
				
		return true;
		
	}

	/**
	 * Perform a trade or donation
	 * @param receive
	 * @param give
	 * @throws IllegalOperationException if not isValidTrade(receive,give)
	 */
	public void trade(List<Card> receive, List<Card> give, boolean active) throws IllegalActionException {
		if(!isValidTrade(receive, give, active)) throw new IllegalActionException(this.name + " can only trade onwned for not owned cards");
		hand.removeAll(give);
		if(active) faceUpCards.removeAll(give);
		setAsideCards.addAll(receive);
	}

	public boolean isValidTrade(List<Card> receive, List<Card> give, boolean active) {
		boolean valid = true;
		for(Card card: give) {
			if(active){
				if(!faceUpCards.contains(card)){
					valid = false;
					System.out.println("Active does not have: "+card.getName());
				}
			} else {
				if(!hand.contains(card)) {
					valid = false;
					System.out.println("Initiator does not have: "+card.getName());
				}
				
			}
		}
		return valid;
	}

	/**
	 * Getter of the property <tt>faceUpCards</tt>
	 * @return  Returns the faceUpCards.
	 * @uml.property  name="faceUpCards"
	 */
	public Collection<Card> getFaceUpCards() {
		return faceUpCards;
	}

	/**
	 * Setter of the property <tt>faceUpCards</tt>
	 * @param faceUpCards  The faceUpCards to set.
	 * @uml.property  name="faceUpCards"
	 */
	public void setFaceUpCards(List<Card> faceUpCards) {
		this.faceUpCards = faceUpCards;
	}
	
	public void setFaceUpCardaside(Card card) throws IllegalActionException {
		if(!faceUpCards.remove(card)) throw new IllegalActionException("Can only set aside face-up cards");
		setAsideCards.add(card);
	}
	
	public JSONObject toJSON(List<Class<? extends Action>> list){
		JSONObject result = new JSONObject();
		try {
			result.put(Protocol.PLAYER_NAME, getName());
			result.put(Protocol.PLAYER_SCORE, String.valueOf(calcScore()));

			JSONArray jsonCards = new JSONArray();
			for(Card card : getHand()){
				jsonCards.put(card.toJSON());
			}
			result.put(Protocol.PLAYER_HAND, jsonCards);

			JSONArray jsonFaceUps = new JSONArray();
			for(Card card : getFaceUpCards()){
				jsonFaceUps.put(card.toJSON());
			}
			result.put(Protocol.PLAYER_FACEUP, jsonFaceUps);
			
			JSONArray jsonAsideCards = new JSONArray();
			for(Card card : getSetAsideCards()){
				jsonAsideCards.put(card.toJSON());
			}
			result.put(Protocol.PLAYER_ASIDE, jsonAsideCards);

			JSONArray jsonFields = new JSONArray();
			for(Field field : getBeanFields()) {
				jsonFields.put(field.toJSON());
			}
			result.put(Protocol.PLAYER_FIELDS, jsonFields);

			// Actions
			JSONArray jsonActions = new JSONArray();
			//List<Class<? extends Action>> actions = .getActions(player);
			if(list.size() > 0){
				if(list.contains(AcceptTrade.class))
					jsonActions.put(Protocol.ACCEPTTRADE);
				if(list.contains(BuyBeanField.class))
					jsonActions.put(Protocol.BUYBEANFIELD);
				if(list.contains(DeclineTrade.class))
					jsonActions.put(Protocol.DECLINETRADE);
				if(list.contains(DrawCards.class))
					jsonActions.put(Protocol.DRAWCARDS);
				if(list.contains(DrawFaceUpCards.class))
					jsonActions.put(Protocol.DRAWFACEUPCARDS);
				if(list.contains(Harvest.class))
					jsonActions.put(Protocol.HARVEST);
				if(list.contains(NextPhase.class))
					jsonActions.put(Protocol.NEXTPHASE);
				if(list.contains(NextPlayer.class))
					jsonActions.put(Protocol.NEXTPLAYER);
				if(list.contains(PlantAsideBean.class))
					jsonActions.put(Protocol.PLANTASIDEBEAN);
				if(list.contains(PlantBean.class))
					jsonActions.put(Protocol.PLANTBEAN);
				if(list.contains(ProposeTrade.class))
					jsonActions.put(Protocol.PROPOSETRADE);
				if(list.contains(SetAsideCard.class))
					jsonActions.put(Protocol.SETASIDECARD);
			}
			result.put(Protocol.PLAYER_ACTIONS, jsonActions);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
}
