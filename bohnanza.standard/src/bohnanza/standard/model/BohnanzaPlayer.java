package bohnanza.standard.model;

import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.Protocol;
import bohnanza.core.Action;
import bohnanza.core.BeanField;
import bohnanza.core.Card;
import bohnanza.core.GameBase;
import bohnanza.core.Player;
import bohnanza.core.shared.actions.DrawCards;
import bohnanza.core.shared.actions.Harvest;
import bohnanza.core.shared.actions.NextPhase;
import bohnanza.core.shared.actions.NextPlayer;
import bohnanza.core.shared.actions.PlantBean;
import bohnanza.standard.actions.AcceptTrade;
import bohnanza.standard.actions.BuyBeanField;
import bohnanza.standard.actions.DeclineTrade;
import bohnanza.standard.actions.DrawFaceUpCards;
import bohnanza.standard.actions.PlantAsideBean;
import bohnanza.standard.actions.ProposeTrade;
import bohnanza.standard.actions.SetAsideCard;

public class BohnanzaPlayer extends Player {

	public BohnanzaPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JSONObject toJSON(
			List<Class<? extends Action<? extends GameBase>>> list,
			HashMap<Integer, Card> cardIndex) {
		JSONObject result = new JSONObject();
		try {
			result.put(Protocol.PLAYER_NAME, getName());
			result.put(Protocol.PLAYER_SCORE, String.valueOf(calcScore()));

			JSONArray jsonCards = new JSONArray();
			for(Card card : getHand()) {
				jsonCards.put(card.toJSON(cardIndex));
			}
			result.put(Protocol.PLAYER_HAND, jsonCards);

			JSONArray jsonFaceUps = new JSONArray();
			for(Card card : getFaceUpCards()) {
				jsonFaceUps.put(card.toJSON(cardIndex));
			}
			result.put(Protocol.PLAYER_FACEUP, jsonFaceUps);

			JSONArray jsonAsideCards = new JSONArray();
			for(Card card : getSetAsideCards()) {
				jsonAsideCards.put(card.toJSON(cardIndex));
			}
			result.put(Protocol.PLAYER_ASIDE, jsonAsideCards);

			JSONArray jsonFields = new JSONArray();
			for(BeanField field : getBeanFields()) {
				jsonFields.put(field.toJSON(cardIndex));
			}
			result.put(Protocol.PLAYER_FIELDS, jsonFields);

			// Actions
			JSONArray jsonActions = new JSONArray();
			// List<Class<? extends Action>> actions = .getActions(player);
			if(list.size() > 0) {
				if(list.contains(AcceptTrade.class))
					jsonActions.put(Protocol.ACCEPTTRADE);
				if(list.contains(BuyBeanField.class))
					jsonActions.put(Protocol.BUYBEANFIELD);
				if(list.contains(DeclineTrade.class))
					jsonActions.put(Protocol.DECLINETRADE);
				if(list.contains(DrawCards.class))
					jsonActions.put(Protocol.DRAWCARDS);
				if(list.contains(DrawFaceUpCards.class))
					jsonActions.put(Protocol.DRAWFACEUPCARDS);
				if(list.contains(Harvest.class))
					jsonActions.put(Protocol.HARVEST);
				if(list.contains(NextPhase.class))
					jsonActions.put(Protocol.NEXTPHASE);
				if(list.contains(NextPlayer.class))
					jsonActions.put(Protocol.NEXTPLAYER);
				if(list.contains(PlantAsideBean.class))
					jsonActions.put(Protocol.PLANTASIDEBEAN);
				if(list.contains(PlantBean.class))
					jsonActions.put(Protocol.PLANTBEAN);
				if(list.contains(ProposeTrade.class))
					jsonActions.put(Protocol.PROPOSETRADE);
				if(list.contains(SetAsideCard.class))
					jsonActions.put(Protocol.SETASIDECARD);
			}
			result.put(Protocol.PLAYER_ACTIONS, jsonActions);
		} catch(JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
