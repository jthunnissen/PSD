package server;

import java.util.ArrayList;

import main.Card;
import main.Field;
import main.Game;
import main.Player;

import org.json.CardPOJO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.PlayerPOJO;

import actions.Action;

public class Protocol {

	private static final String CURRENTPLAYER = "currentplayer";
	private static final String PLAYERS = "players";
	private static final String PLAYER_NAME = "name";
	private static final String PLAYER_SCORE = "score";
	private static final String PLAYER_HAND = "hand";
	private static final String PLAYER_FIELDS = "fields";
	private static final String CARD_NAME = "name";
	private static final String CARD_SCORE = "score";
	
	
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
			result = root.toString();
			
			// Actions
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
					// Fields
					JSONArray jsonFields = players.getJSONObject(i).getJSONArray(PLAYER_FIELDS);
					ArrayList<CardPOJO> playerFields = new ArrayList<CardPOJO>();
					for(int k=0; k< jsonFields.length(); k++){
						String cardName = jsonFields.getJSONObject(k).getString(CARD_NAME);
						String cardScore = jsonFields.getJSONObject(k).getString(CARD_SCORE);
						playerFields.add(new CardPOJO(cardName, cardScore));
					}
					
					result.add(new PlayerPOJO(playerName, playerScore, playerHand, playerFields));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}

}
