package bohnanza.core;

import java.util.HashMap;

import org.json.JSONObject;


public interface ToJSON {

	public JSONObject toJSON(HashMap<Integer, Card> cardIndex);
}
