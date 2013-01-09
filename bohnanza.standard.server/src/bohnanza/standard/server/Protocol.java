
package bohnanza.standard.server;

import java.util.ArrayList;


import org.json.CardPOJO;
import org.json.GamePOJO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.PlayerPOJO;

import bohnanza.standard.core.Card;
import bohnanza.standard.core.Field;
import bohnanza.standard.core.Game;
import bohnanza.standard.core.Player;
import bohnanza.standard.core.actions.Action;


public class Protocol {

	private static final String CURRENTPLAYER = "currentplayer";
	private static final String PLAYERS = "players";
	private static final String PLAYER_NAME = "name";
	private static final String PLAYER_SCORE = "score";
	private static final String PLAYER_HAND = "hand";
	private static final String PLAYER_FACEUP = "faceup";
	private static final String PLAYER_FIELDS = "fields";
	private static final String CARD_NAME = "name";
	private static final String CARD_SCORE = "score";
	
	public static final String ACCEPTTRADE = "ACCEPTTRADE";
	public static final String BUYBEANFIELD = "BUYBEANFIELD";
	public static final String DECLINETRADE = "DECLINETRADE";
	public static final String DRAWCARDS = "DRAWCARDS";
	public static final String DRAWFACEUPCARDS = "DRAWFACEUPCARDS";
	public static final String HARVEST = "HARVEST";
	public static final String PLANTBEAN = "PLANTBEAN";
	public static final String PROPOSETRADEORDONATION = "PROPOSETRADEORDONATION";
	public static final String CHAT = "CHAT";
	
	
	
	
	private ArrayList<Player> players;
	private ArrayList<Action> actions;
	private int currentPlayer = -1;
	
	private Game game;
	
	public Protocol(Game game){
		this.game = game;
	}

	public void addPlayer(Player player){
		players.add(player);
	}

	public void addAction(Action action){
		actions.add(action);
	}

	public String toJSON(){
		String result = "";
		
		try {
			JSONObject root = new JSONObject();
			root.put("type", "gameupdate");
			// Current player
			root.put(CURRENTPLAYER, game.getActivePlayer().getName());
			// All players
			JSONArray jsonPlayers = new JSONArray();
			for(Player player: game.getPlayers()){
				JSONObject jsonPlayer = new JSONObject();
				jsonPlayer.put(PLAYER_NAME, player.getName());
				jsonPlayer.put(PLAYER_SCORE, String.valueOf(player.calcScore()));
				
				JSONArray jsonCards = new JSONArray();
				for(Card card : player.getHand()){
					JSONObject jsonCard = new JSONObject();
					jsonCard.put(CARD_NAME, card.getName());
					jsonCard.put(CARD_SCORE, String.valueOf(card.getNumberOfCards()));
					jsonCards.put(jsonCard);
				}
				jsonPlayer.put(PLAYER_HAND, jsonCards);
				
				JSONArray jsonFaceUps = new JSONArray();
				for(Card card : player.getFaceUpCards()){
					JSONObject jsonFaceUp = new JSONObject();
					jsonFaceUp.put(CARD_NAME, card.getName());
					jsonFaceUp.put(CARD_SCORE, String.valueOf(card.getNumberOfCards()));
					jsonFaceUps.put(jsonFaceUp);
				}
				jsonPlayer.put(PLAYER_FACEUP, jsonFaceUps);
				
				JSONArray jsonFields = new JSONArray();
				for(Field field : player.getBeanFields()) {
					JSONObject jsonField = new JSONObject();
					if(field.getCards().size() == 0) {
						jsonField.put(CARD_NAME, "null");
						jsonField.put(CARD_SCORE, "0");
					} else {
						jsonField.put(CARD_NAME, field.getCards().get(0).getName());
						jsonField.put(CARD_SCORE, String.valueOf(field.getCards().size()));
					}
					jsonFields.put(jsonField);
				}
				jsonPlayer.put(PLAYER_FIELDS, jsonFields);
				
				jsonPlayers.put(jsonPlayer);
			}
			root.put(PLAYERS, jsonPlayers);
			
			
			// Actions
			
			
			result = root.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public GamePOJO fromJSON(String json){
		GamePOJO result = null;
		
		return result;
	}
	
	public String fromJSONCurrentPlayer(String json){
		String result = "";
		
		try {
			JSONObject root= new JSONObject(json);
			result = root.getString(CURRENTPLAYER);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<PlayerPOJO> fromJSONGetPlayers(String json, String username){
		
		ArrayList<PlayerPOJO> result = new ArrayList<PlayerPOJO>();
		JSONObject root;
		try {
			root = new JSONObject(json);
			JSONArray players = root.getJSONArray(PLAYERS);
			for(int i=0; i<players.length();i++){
				String playerName = players.getJSONObject(i).getString(PLAYER_NAME);
				if(!(username != null && !playerName.equals(username))){
					String playerScore = players.getJSONObject(i).getString(PLAYER_SCORE);
					// Hand
					JSONArray jsonCards = players.getJSONObject(i).getJSONArray(PLAYER_HAND);
					ArrayList<CardPOJO> playerHand = new ArrayList<CardPOJO>();
					for(int j=0; j< jsonCards.length(); j++){
						String cardName = jsonCards.getJSONObject(j).getString(CARD_NAME);
						String cardScore = jsonCards.getJSONObject(j).getString(CARD_SCORE);
						playerHand.add(new CardPOJO(cardName, cardScore));
					}
					
					// Face up
					JSONArray jsonFaceUp = players.getJSONObject(i).getJSONArray(PLAYER_FACEUP);
					ArrayList<CardPOJO> playerFaceUp = new ArrayList<CardPOJO>();
					for(int l=0; l<jsonFaceUp.length(); l++){
						String cardName = jsonFaceUp.getJSONObject(l).getString(CARD_NAME);
						String cardScore = jsonFaceUp.getJSONObject(l).getString(CARD_SCORE);
						playerFaceUp.add(new CardPOJO(cardName, cardScore));
					}
					// Fields
					JSONArray jsonFields = players.getJSONObject(i).getJSONArray(PLAYER_FIELDS);
					ArrayList<CardPOJO> playerFields = new ArrayList<CardPOJO>();
					for(int k=0; k< jsonFields.length(); k++){
						String cardName = jsonFields.getJSONObject(k).getString(CARD_NAME);
						String cardScore = jsonFields.getJSONObject(k).getString(CARD_SCORE);
						playerFields.add(new CardPOJO(cardName, cardScore));
					}
					
					result.add(new PlayerPOJO(playerName, playerScore, playerHand, playerFaceUp, playerFields));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
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
			root.put("type", "usernamecheck");
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
			root.put("type", Protocol.CHAT);
			root.put("response", response);
			result = root.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

}