
package org.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bohnanza.standard.core.Card;
import bohnanza.standard.core.Game;
import bohnanza.standard.core.Player;
import bohnanza.standard.core.actions.Action;

public class Protocol {
	
	public static final String TYPE = "type";

	public static final String CURRENTPLAYER = "currentplayer";
	public static final String GAME_TYPE = "game_type";
	public static final int GAME_STANDARD = 1;
	public static final int GAME_AL_CABOHNE = 2;
	
	public static final String PLAYERS = "players";
	public static final String PLAYER_NAME = "name";
	public static final String PLAYER_SCORE = "score";
	public static final String PLAYER_HAND = "hand";
	public static final String PLAYER_ASIDE = "aside";
	public static final String PLAYER_FACEUP = "faceup";
	public static final String PLAYER_FIELDS = "fields";
	public static final String PLAYER_ACTIONS = "actions";
	public static final String CARD_NAME = "name";
	public static final String CARD_SCORE = "score";
	public static final String CARD_HASHCODE = "hashcode";

	public static final String ACCEPTTRADE = "ACCEPTTRADE";
	public static final String BUYBEANFIELD = "BUYBEANFIELD";
	public static final String DECLINETRADE = "DECLINETRADE";
	public static final String DRAWCARDS = "DRAWCARDS";
	public static final String DRAWFACEUPCARDS = "DRAWFACEUPCARDS";
	public static final String HARVEST = "HARVEST";
	public static final String NEXTPHASE = "NEXTPHASE";
	public static final String NEXTPLAYER = "NEXTPLAYER";
	public static final String PLANTASIDEBEAN = "PLANTASIDEBEAN";
	public static final String PLANTBEAN = "PLANTBEAN";
	public static final String PROPOSETRADE = "PROPOSETRADE";
	public static final String SETASIDECARD = "SETASIDECARD";
	public static final String CHAT = "CHAT";
	public static final String ERROR = "error";
	



	private ArrayList<Player> players;
	private ArrayList<Action> actions;

	public void addPlayer(Player player){
		players.add(player);
	}

	public void addAction(Action action){
		actions.add(action);
	}

	public static String toJSON(Game game, HashMap<Integer, Card> cardIndex){
		String result = "";

		try {
			JSONObject root = new JSONObject();
			root.put(TYPE, "gameupdate");
			root.put(GAME_TYPE, GAME_STANDARD);
			// Current player
			root.put(CURRENTPLAYER, game.getActivePlayer().getName());
			// All players
			JSONArray jsonPlayers = new JSONArray();
			for(Player player: game.getPlayers()){
				jsonPlayers.put(player.toJSON(game.getActions(player), cardIndex));
			}
			root.put(PLAYERS, jsonPlayers);

			result = root.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return result;
	}

	public static GamePOJO fromJSON(String json, String username){
		GamePOJO result = null;
		JSONObject root;
		try {
			root = new JSONObject(json);
			String currentPlayerName = root.getString(CURRENTPLAYER);
			PlayerPOJO thisPlayer = null, currentPlayer = null;

			ArrayList<PlayerPOJO> playersPOJO = new ArrayList<PlayerPOJO>();
			JSONArray players = root.getJSONArray(PLAYERS);
			for(int i=0; i<players.length();i++){
				PlayerPOJO playerPOJO =new PlayerPOJO(players.getJSONObject(i)); 
				playersPOJO.add(playerPOJO);

				if(username.equals(playerPOJO.getName())){
					thisPlayer = playerPOJO;
				}
				if(currentPlayerName.equals(playerPOJO.getName())){
					currentPlayer = playerPOJO;
				}

			}
			result = new GamePOJO(currentPlayer, thisPlayer, playersPOJO);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static boolean usernameFromJSON(JSONObject root){
		boolean result = false;
		try {
			result = (root.getBoolean("response") == true);
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public static String usernameCheckToJSON(boolean response){
		String result = "";
		JSONObject root = new JSONObject();
		try {
			root.put(TYPE, "usernamecheck");
			root.put("response", response);
			result = root.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String chatFromJSON(JSONObject response) {
		String result = "";
		try {
			result = response.getString("response");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String chatToJSON(String response){
		String result = "";	
		try {
			JSONObject root = new JSONObject();
			root.put(TYPE, Protocol.CHAT);
			root.put("response", response);
			result = root.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String waitingForPlayers() {
		String result = "";

		try {
			JSONObject root = new JSONObject();
			root.put(TYPE, "waiting");
			result = root.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static String errorToJSON(String message) {
		String result = "";	
		try {
			JSONObject root = new JSONObject();
			root.put(TYPE, Protocol.ERROR);
			root.put("response", message);
			result = root.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static OfferPOJO sendOfferFromJSON(JSONObject root){
		OfferPOJO result = null;
		
		try {
			String player = root.getString(PLAYER_NAME);
			ArrayList<CardPOJO> cards = new ArrayList<CardPOJO>();
			JSONArray jsonCards = root.getJSONArray("cards");
			for(int i=0; i<jsonCards.length(); i++){
				cards.add(new CardPOJO(jsonCards.getJSONObject(i)));
			}
			
			ArrayList<CardPOJO> offer = new ArrayList<CardPOJO>();
			JSONArray jsonOffer = root.getJSONArray("offer");
			for(int j=0; j<jsonOffer.length(); j++){
				offer.add(new CardPOJO(jsonOffer.getJSONObject(j)));
			}
			result = new OfferPOJO(player, cards, offer);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String sendOfferToJSON(String type, String playerName, List<CardPOJO> cards, List<CardPOJO> offer) {
		String result = "";	
		try {
			JSONObject root = new JSONObject();
			root.put(TYPE, type);
			root.put(Protocol.PLAYER_NAME, playerName);
			JSONArray jsonCards = new JSONArray();
			for(CardPOJO card: cards){
				JSONObject jsonCard = new JSONObject();
				jsonCard.put(Protocol.CARD_HASHCODE, card.getHashcode());
				jsonCards.put(jsonCard);
			}
			root.put("cards", jsonCards);
			JSONArray jsonOffer = new JSONArray();
			for(CardPOJO card: offer){
				JSONObject jsonCard = new JSONObject();
				jsonCard.put(Protocol.CARD_NAME, card.getName());
				jsonCard.put(Protocol.CARD_SCORE, card.getScore());
				jsonCard.put(Protocol.CARD_HASHCODE, card.getHashcode());
				jsonOffer.put(jsonCard);
			}
			root.put("offer", jsonOffer);
			result = root.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

}