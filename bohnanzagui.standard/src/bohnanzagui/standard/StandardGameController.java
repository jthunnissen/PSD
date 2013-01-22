package bohnanzagui.standard;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.CardPOJO;
import org.json.GamePOJO;
import org.json.OfferPOJO;
import org.json.Protocol;

/**
 * Game Controller.
 */
public class StandardGameController extends GameController {

	@FXML
	HBox aside;
	@FXML
	HBox tradearea;
	@FXML
	HBox offer;
	@FXML
	Button drawcard;
	@FXML
	Button drawfaceupcard;
	@FXML
	AnchorPane offerPane;
	@FXML
	AnchorPane actionsPane;

	private List<CardPOJO> offerList = new ArrayList<CardPOJO>();
	private CardPOJO offerItem;

	public void update(GamePOJO update){
		// Init
		nextPhase.setVisible(false);
		actionsPane.setVisible(false);
		offerPane.setVisible(false);
		aside.setOnDragOver(null);
		aside.setOnDragDropped(null);
		field1.setOnDragOver(null);
		field1.setOnDragDropped(null);
		field2.setOnDragOver(null);
		field2.setOnDragDropped(null);
		field3.setOnDragOver(null);
		field3.setOnDragDropped(null);
		labelfield1.setText("");
		labelfield2.setText("");
		labelfield3.setText("");
		
		// Players + scores
		updatePlayersList(update);
		// Hand view of this player
		updateThisPlayerHand(update);
		updateFaceUpCards(update);
		updateActionsView(update);
		if(this.isActivePlayer(update.getCurrentPlayer().getName())) {
			// Build actions view		
			
		} else if(update.getCurrentPlayer().getActions().contains(Protocol.PROPOSETRADE)) {
			actionsPane.setVisible(false);
			offerPane.setVisible(true);
		}

		// Build trading phase
		updateAsideCards(update);
	}
	
	private void updateAsideCards(GamePOJO update){
		aside.getChildren().clear();
		for(CardPOJO card : update.getThisPlayer().getAside()){
			ImageView cardView = new ImageView(card.getImage());
			cardView.setUserData(card);
			if(update.getThisPlayer().getActions().contains(Protocol.PLANTASIDEBEAN)){
				this.setupCardDraggable(cardView);	
			}
			aside.getChildren().add(cardView);
		}
	}

	private void updateFaceUpCards(GamePOJO update) {
		tradearea.getChildren().clear();
		for(CardPOJO card : update.getCurrentPlayer().getFaceUp()){
			ImageView cardView = new ImageView(card.getImage());
			tradearea.getChildren().add(cardView);
			if(update.getThisPlayer().getActions().contains(Protocol.SETASIDECARD)){
				setupSetAsideSource(cardView, card);
				setupSetAsideTarget(aside);
			} else if(update.getThisPlayer().getActions().contains(Protocol.PROPOSETRADE)) {
				setupMakeOffer(cardView, card);
			}
		}
	}

	private void setupSetAsideTarget(final HBox targetBox) {
		targetBox.setOnDragOver(new EventHandler <DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				if(db.hasImage()){
					event.acceptTransferModes(TransferMode.COPY);
				}
				event.consume();
			}
		});

		targetBox.setOnDragDropped(new EventHandler <DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				if(db.hasImage()){
					targetBox.getChildren().add(new ImageView(db.getImage()));
					application.sendToServer(Protocol.SETASIDECARD +" "+db.getString());
					event.setDropCompleted(true);
				}else{
					event.setDropCompleted(false);
				}
				event.consume();
			}
		});

	}

	private void setupSetAsideSource(final ImageView cardView, final CardPOJO card) {
		cardView.setOnDragDetected(new EventHandler <MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Dragboard db = cardView.startDragAndDrop(TransferMode.COPY);
				ClipboardContent content = new ClipboardContent();
				content.putImage(cardView.getImage());
				content.putString(""+card.getHashcode());
				db.setContent(content);
				event.consume();
			}
		});
	}

	public void updateActionsView(GamePOJO update){
		actionsPane.setVisible(true);
		ArrayList<String> actions = update.getThisPlayer().getActions();
		field1.setImage(defaultEmptyImage);
		field2.setImage(defaultEmptyImage);
		field3.setImage(defaultEmptyImage);
		
		if(update.getThisPlayer().getFields().get(0).getScore() > 0){
			field1.setImage(update.getThisPlayer().getFields().get(0).getImage());
			labelfield1.setText(""+update.getThisPlayer().getFields().get(0).getScore());
			System.out.println("CARD: "+ update.getThisPlayer().getFields().get(0).getName());
		} else {
			System.out.println("NO CARDS" + " " + update.getThisPlayer().getFields().get(0).getName()+ " " +update.getThisPlayer().getFields().get(0).getScore());
		}
		if(Integer.valueOf(update.getThisPlayer().getFields().get(1).getScore()) > 0){
			field2.setImage(update.getThisPlayer().getFields().get(1).getImage());
			labelfield2.setText(""+update.getThisPlayer().getFields().get(1).getScore());
		}
		if(update.getThisPlayer().getFields().size() > 2) {
			if(Integer.valueOf(update.getThisPlayer().getFields().get(2).getScore()) > 0){
				field3.setImage(update.getThisPlayer().getFields().get(2).getImage());
				labelfield3.setText(""+update.getThisPlayer().getFields().get(2).getScore());
			}
		}
		
		if(actions.contains(Protocol.BUYBEANFIELD)){
			harvest3.setVisible(false);
			buy3.setVisible(true);
		} else {
			buy3.setVisible(false);
		}

		drawcard.setVisible((actions.contains(Protocol.DRAWCARDS)) ? true : false);
		drawfaceupcard.setVisible((actions.contains(Protocol.DRAWFACEUPCARDS)) ? true : false);

		if(actions.contains(Protocol.HARVEST)){
			harvest1.setVisible(true);
			harvest2.setVisible(true);
			if(update.getThisPlayer().getFields().size() > 2){
				harvest3.setVisible(true);
			}
		} else {
			harvest1.setVisible(false);
			harvest2.setVisible(false);
			harvest3.setVisible(false);
		}
		nextPhase.setVisible((actions.contains(Protocol.NEXTPHASE)) ? true : false);
		if(actions.contains(Protocol.PLANTASIDEBEAN)){
			for(Node node : aside.getChildren()){
				setupCardDraggable((ImageView) node);
			}
			setupPlantTarget(false, field1, 0);
			setupPlantTarget(false, field2, 1);
			if(update.getThisPlayer().getFields().size() > 2){
				// Player has bought third field
				setupPlantTarget(false, field3, 2);
				harvest3.setVisible(true);
			}
		}
		if(actions.contains(Protocol.PLANTBEAN)){
			setupPlantTarget(true, field1, 0);
			setupPlantTarget(true, field2, 1);
			if(update.getThisPlayer().getFields().size() > 2){
				// Player has bought third field
				setupPlantTarget(true, field3, 2);
				harvest3.setVisible(true);
			}
			setupCardDraggable((ImageView) hand.getChildren().get(0));

		}
	}

	public void setupMakeOffer(final ImageView cardView, final CardPOJO card){
		cardView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("Click on: "+card.getName());
				offerPane.setVisible(true);
				setupOfferTarget(offer);
				for(Node node : hand.getChildren()){
					setupCardDraggable((ImageView) node);
				}
				if(offerItem != card) {
					offerItem = card;
					offer.getChildren().clear();
					offerList.clear();
				}};
		});

	}

	public void viewOffer(final OfferPOJO offer){
		final Stage myDialog = new Stage();
		myDialog.initModality(Modality.WINDOW_MODAL);
		
		StringBuilder cardsString = new StringBuilder();
		for(CardPOJO card :offer.getCards()){
			cardsString.append(card.getName());
			cardsString.append(",");
		}
		StringBuilder offerString = new StringBuilder();
		for(CardPOJO card :offer.getOffer()){
			offerString.append(card.getName());
			offerString.append(",");
		}
		Text descriptionText = new Text("Offer: "+ offerString.substring(0, offerString.length()-1).toString() + " for: "+ cardsString.toString());

		Button dismissButton = new Button("Dismiss");
		dismissButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				application.sendToServer(Protocol.DECLINETRADE + " " + offer.getInitiator());
				myDialog.close();
			}
		});

		Button okButton = new Button("OK");
		okButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				application.sendToServer(Protocol.ACCEPTTRADE+Protocol.sendOfferToJSON(Protocol.ACCEPTTRADE, offer.getInitiator(), offer.getCards(), offer.getOffer()));
				myDialog.close();
			}

		});

		Scene myDialogScene = new Scene(VBoxBuilder.create()
				.children(new Text("Offer received from "+offer.getInitiator()+"!"), descriptionText, dismissButton, okButton)
				.alignment(Pos.CENTER)
				.padding(new Insets(10))
				.build());

		myDialog.setScene(myDialogScene);
		myDialog.show();
	}

	public void sendOffer(){
		ArrayList<CardPOJO> cards = new ArrayList<CardPOJO>();
		cards.add(offerItem);
		application.sendToServer(Protocol.PROPOSETRADE + Protocol.sendOfferToJSON(Protocol.PROPOSETRADE, application.getUsername(), cards, offerList));
		offerList.clear();
		offerItem = null;
	}

	
	public void drawcard(){
		application.sendToServer(Protocol.DRAWCARDS);
	}

	public void drawfaceupcard(){
		application.sendToServer(Protocol.DRAWFACEUPCARDS);
	}

	void setupOfferTarget(final HBox targetBox){
		targetBox.setOnDragOver(new EventHandler <DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				if(db.hasImage()){
					event.acceptTransferModes(TransferMode.MOVE);
				}
				event.consume();
			}
		});
		targetBox.setOnDragDropped(new EventHandler <DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				if(db.hasImage()){
					targetBox.getChildren().add(new ImageView(db.getImage()));
					offerList.add(new CardPOJO("", 0, Integer.valueOf(db.getString())));
					event.setDropCompleted(true);
				}else{
					event.setDropCompleted(false);
				}
				event.consume();
			}
		});
	}

}