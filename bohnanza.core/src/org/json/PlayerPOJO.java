package org.json;

import java.util.ArrayList;

public class PlayerPOJO {

	private String name;
	private String score;
	private ArrayList<CardPOJO> hand = new ArrayList<CardPOJO>();
	private ArrayList<CardPOJO> faceUp = new ArrayList<CardPOJO>();
	private ArrayList<CardPOJO> aside = new ArrayList<CardPOJO>();
	private ArrayList<CardPOJO> fields = new ArrayList<CardPOJO>();
	private ArrayList<String> actions = new ArrayList<String>();

	public PlayerPOJO(String name, String score, ArrayList<CardPOJO> hand,
			ArrayList<CardPOJO> faceUp, ArrayList<CardPOJO> aside,
			ArrayList<CardPOJO> fields, ArrayList<String> actions) {
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
			JSONArray jsonFaceUp = jsonPlayer
					.getJSONArray(Protocol.PLAYER_FACEUP);
			for(int l = 0; l < jsonFaceUp.length(); l++) {
				faceUp.add(new CardPOJO(jsonFaceUp.getJSONObject(l)));
			}

			// Aside
			JSONArray jsonAside = jsonPlayer
					.getJSONArray(Protocol.PLAYER_ASIDE);
			for(int l = 0; l < jsonAside.length(); l++) {
				aside.add(new CardPOJO(jsonAside.getJSONObject(l)));
			}
			// Fields
			JSONArray jsonFields = jsonPlayer
					.getJSONArray(Protocol.PLAYER_FIELDS);
			for(int k = 0; k < jsonFields.length(); k++) {
				fields.add(new CardPOJO(jsonFields.getJSONObject(k)));
			}

			// Actions
			JSONArray jsonActions = jsonPlayer
					.getJSONArray(Protocol.PLAYER_ACTIONS);
			for(int m = 0; m < jsonActions.length(); m++) {
				actions.add(jsonActions.getString(m));
			}
		} catch(JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getName() {
		return name;
	}

	public String getScore() {
		return score;
	}

	public ArrayList<CardPOJO> getHand() {
		return hand;
	}

	public ArrayList<CardPOJO> getFaceUp() {
		return faceUp;
	}

	public ArrayList<CardPOJO> getAside() {
		return aside;
	}

	public ArrayList<CardPOJO> getFields() {
		return fields;
	}

	public ArrayList<String> getActions() {
		return actions;
	}

}
