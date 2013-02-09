package mocks;
import java.util.Collection;
import java.util.HashMap;

import org.json.JSONObject;

import bohnanza.core.Action;
import bohnanza.core.Card;
import bohnanza.core.GameBase;
import bohnanza.core.Player;


public class PlayerMock extends Player {

	public PlayerMock(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JSONObject toJSON(
			Collection<Class<? extends Action<? extends GameBase>>> list,
			HashMap<Integer, Card> cardIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
