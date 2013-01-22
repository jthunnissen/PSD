package bohnanza.alcabohne.model;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import bohnanza.core.Action;
import bohnanza.core.Card;

public class NormalPlayer extends AlCabohnePlayer {

	protected NormalPlayer(String name) {
		super(name);
	}

	@Override
	public JSONObject toJSON(List<Class<? extends Action>> list,
			HashMap<Integer, Card> cardIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
