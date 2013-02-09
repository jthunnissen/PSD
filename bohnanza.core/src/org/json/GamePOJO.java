package org.json;

import java.util.ArrayList;

/** This class is a data container for a gamestate in the GUi 
*
* @author Anne van de Venis
* @version 1.0
*/
public class GamePOJO {

	/**
	 * Active player in the game
	 */
	private PlayerPOJO currentPlayer;
	/**
	 * All players in the game
	 */
	private ArrayList<PlayerPOJO> players;
	/**
	 * All face-up cards in the game
	 */
	private ArrayList<CardPOJO> faceup;
	/**
	 * This player
	 */
	private PlayerPOJO thisPlayer;
	/**
	 * List of possible actions
	 */
	private ArrayList<String> actions;

	public GamePOJO(PlayerPOJO currentPlayer, PlayerPOJO thisPlayer, ArrayList<PlayerPOJO> players) {
		this.currentPlayer = currentPlayer;
		this.thisPlayer = thisPlayer;
		this.players = players;
	}

	/**
	 * Returns current player
	 * @return Current player
	 */
	public PlayerPOJO getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Returns this player
	 * @return This player
	 */
	public PlayerPOJO getThisPlayer() {
		return thisPlayer;
	}

	/**
	 * Returns all players
	 * @return All players
	 */
	public ArrayList<PlayerPOJO> getPlayers() {
		return players;
	}

	/**
	 * Returns all face-up cards
	 * @return All face-up cards
	 */
	public ArrayList<CardPOJO> getFaceup() {
		return faceup;
	}

	/**
	 * Returns all actions
	 * @return All acctions
	 */
	public ArrayList<String> getActions() {
		return actions;
	}

}
