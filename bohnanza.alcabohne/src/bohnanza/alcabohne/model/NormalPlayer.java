package bohnanza.alcabohne.model;
import java.util.Collection;
import java.util.HashMap;

import org.json.JSONObject;

import bohnanza.core.Action;
import bohnanza.core.BaseGame;
import bohnanza.core.Card;

public class NormalPlayer extends AlCabohnePlayer {

	protected NormalPlayer(String name) {
		super(name);
	}

	@Override
	public JSONObject toJSON(Collection<Class<? extends Action<? extends BaseGame>>> list, HashMap<Integer, Card> cardIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
