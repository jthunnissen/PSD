package org.json;

import java.util.ArrayList;

/** This class is a data container for a plyaer in the GUi 
*
* @author Anne van de Venis
* @version 1.0
*/
public class PlayerPOJO {

	/**
	 * Name of the player
	 */
	private String name;
	/**
	 * Score of the player
	 */
	private String score;
	/**
	 * Cards in players' hand
	 */
	private ArrayList<CardPOJO> hand = new ArrayList<CardPOJO>();
	/**
	 * Face-up cards
	 */
	private ArrayList<CardPOJO> faceUp = new ArrayList<CardPOJO>();
	/**
	 * Aside cards
	 */
	private ArrayList<CardPOJO> aside = new ArrayList<CardPOJO>();
	/**
	 * Fields this players owns
	 */
	private ArrayList<CardPOJO> fields = new ArrayList<CardPOJO>();
	/**
	 * Actiosn this player can execute
	 */
	private ArrayList<String> actions = new ArrayList<String>();

	public PlayerPOJO(String name, String score, ArrayList<CardPOJO> hand, ArrayList<CardPOJO> faceUp, ArrayList<CardPOJO> aside, ArrayList<CardPOJO> fields, ArrayList<String> actions) {
		this.name = name;
		this.score = score;
		this.hand = hand;
		this.faceUp = faceUp;
		this.aside = aside;
		this.fields = fields;
		this.actions = actions;
	}

	public PlayerPOJO(JSONObject jsonPlayer) {
		try {
			name = jsonPlayer.getString(Protocol.PLAYER_NAME);
			score = jsonPlayer.getString(Protocol.PLAYER_SCORE);
			// Hand
			JSONArray jsonCards = jsonPlayer.getJSONArray(Protocol.PLAYER_HAND);

			for(int j = 0; j < jsonCards.length(); j++) {
				hand.add(new CardPOJO(jsonCards.getJSONObject(j)));
			}

			// Face up
			JSONArray jsonFaceUp = jsonPlayer.getJSONArray(Protocol.PLAYER_FACEUP);
			for(int l = 0; l < jsonFaceUp.length(); l++) {
				faceUp.add(new CardPOJO(jsonFaceUp.getJSONObject(l)));
			}

			// Aside
			JSONArray jsonAside = jsonPlayer.getJSONArray(Protocol.PLAYER_ASIDE);
			for(int l = 0; l < jsonAside.length(); l++) {
				aside.add(new CardPOJO(jsonAside.getJSONObject(l)));
			}
			// Fields
			JSONArray jsonFields = jsonPlayer.getJSONArray(Protocol.PLAYER_FIELDS);
			for(int k = 0; k < jsonFields.length(); k++) {
				fields.add(new CardPOJO(jsonFields.getJSONObject(k)));
			}

			// Actions
			JSONArray jsonActions = jsonPlayer.getJSONArray(Protocol.PLAYER_ACTIONS);
			for(int m = 0; m < jsonActions.length(); m++) {
				actions.add(jsonActions.getString(m));
			}
		} catch(JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Getter for this player's name
	 * @return Players name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for this player's score
	 * @return Player's score
	 */
	public String getScore() {
		return score;
	}

	/**
	 * Getter for this players's hand
	 * @return This player's hand
	 */
	public ArrayList<CardPOJO> getHand() {
		return hand;
	}

	/**
	 * Getter for this player's face-up cards
	 * @return Player's  face-up cards
	 */
	public ArrayList<CardPOJO> getFaceUp() {
		return faceUp;
	}

	/**
	 * Getter for this player's aside cards
	 * @return Player's aside cards
	 */
	public ArrayList<CardPOJO> getAside() {
		return aside;
	}

	/**
	 * Getter for this player's fields
	 * @return Player's field
	 */
	public ArrayList<CardPOJO> getFields() {
		return fields;
	}

	/**
	 * Getter for this player's actions 
	 * @return Player's actions
	 */
	public ArrayList<String> getActions() {
		return actions;
	}

}
