package bohnanza.alcabohne.model;

import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import bohnanza.core.Action;
import bohnanza.core.Card;
import bohnanza.core.GameBase;

public class NormalPlayer extends AlCabohnePlayer {

	protected NormalPlayer(String name) {
		super(name);
	}

	@Override
	public JSONObject toJSON(List<Class<? extends Action<? extends GameBase>>> list, HashMap<Integer, Card> cardIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
