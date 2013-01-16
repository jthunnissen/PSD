package bohnanza.standard.core;

import java.util.HashMap;

import org.json.JSONObject;

public interface ToJSON {

	public abstract JSONObject toJSON(HashMap<Integer, Card> cardIndex);
}
