package bohnanza.core;

import java.util.HashMap;

import org.json.JSONObject;

/**
 * This class is an interface for objects that should create a JSON Object from themselves.
 * This JSON Object is used for sending information about the objects over the web.
 * @author Anne
 *
 */
public interface ToJSON {

	/**
	 * Create JSON response
	 * @param cardIndex Lists of all cards that are send over the web.
	 * @return JSON response
	 */
	public JSONObject toJSON(HashMap<Integer, Card> cardIndex);
}
