
package bohnanza.standard.client;

/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
import org.json.PlayerPOJO;
import org.json.Protocol;


/**
 * Game Controller.
 */
public class GameController extends AnchorPane implements Initializable {

	@FXML
	ListView<String> players;
	@FXML
	TextField username;
	@FXML
	Button login;
	@FXML
	Label error;
	@FXML
	HBox hand;
	@FXML
	HBox aside;
	@FXML
	HBox tradearea;
	@FXML
	HBox offer;
	@FXML
	ImageView field1;
	@FXML
	Label labelfield1;
	@FXML
	ImageView field2;
	@FXML
	Label labelfield2;
	@FXML
	ImageView field3;
	@FXML
	Label labelfield3;
	@FXML
	Button harvest1;
	@FXML
	Button harvest2;
	@FXML
	Button harvest3;
	@FXML
	Button buy3;
	@FXML
	Button drawcard;
	@FXML
	Button drawfaceupcard;
	@FXML
	Button nextPhase;
	@FXML
	TextArea chatbox;
	@FXML
	TextField chatmessage;
	@FXML
	Button sendmessage;
	@FXML
	AnchorPane offerPane;
	@FXML
	AnchorPane actionsPane;

	private ClientGUI application;
	private ArrayList<CardPOJO> offerList = new ArrayList<CardPOJO>();
	private CardPOJO offerItem;
	private Image defaultEmptyImage;

	public void setApp(ClientGUI application){
		this.application = application;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		defaultEmptyImage = field1.getImage();
	}

	public void addChat(String text){
		chatbox.setText(chatbox.getText() +"\n"+text);
	}

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
				this.setupPlantSource(cardView);	
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
					application.getClient().sendToServer(Protocol.SETASIDECARD +" "+db.getString());
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
				content.putString(card.getName());
				db.setContent(content);
				event.consume();
			}
		});
		cardView.setOnDragDone(new EventHandler<DragEvent>() {
			@Override	
			public void handle(DragEvent event) {
				if(event.isAccepted()){
					tradearea.getChildren().remove(cardView);
					event.consume();
				}
			}

		});
	}



	public void updatePlayersList(GamePOJO update) {
		ArrayList<PlayerPOJO> playersPOJOS = update.getPlayers();
		ObservableList<String> items = FXCollections.observableArrayList();
		for(PlayerPOJO playerPOJO : playersPOJOS) {
			if(update.getCurrentPlayer().getName().equals(playerPOJO.getName())){
				items.add(">>> "+playerPOJO.getName() + " - " + playerPOJO.getScore());
			} else {
				items.add(playerPOJO.getName() + " - " + playerPOJO.getScore());	
			}
		}
		players.setItems(items);
	}

	public void updateThisPlayerHand(GamePOJO update){
		hand.getChildren().clear();
		for(CardPOJO card : update.getThisPlayer().getHand()){
			ImageView cardView = new ImageView(card.getImage());
			cardView.setUserData(card);
			hand.getChildren().add(cardView);
		}
	}

	public void updateActionsView(GamePOJO update){
		actionsPane.setVisible(true);
		ArrayList<String> actions = update.getThisPlayer().getActions();
		field1.setImage(defaultEmptyImage);
		field2.setImage(defaultEmptyImage);
		field3.setImage(defaultEmptyImage);
		
		if(Integer.valueOf(update.getThisPlayer().getFields().get(0).getScore()) > 0){
			field1.setImage(update.getThisPlayer().getFields().get(0).getImage());
			labelfield1.setText(update.getThisPlayer().getFields().get(0).getScore());
		}
		if(Integer.valueOf(update.getThisPlayer().getFields().get(1).getScore()) > 0){
			field2.setImage(update.getThisPlayer().getFields().get(1).getImage());
			labelfield2.setText(update.getThisPlayer().getFields().get(1).getScore());
		}
		if(update.getThisPlayer().getFields().size() > 2) {
			if(Integer.valueOf(update.getThisPlayer().getFields().get(2).getScore()) > 0){
				field3.setImage(update.getThisPlayer().getFields().get(2).getImage());
				labelfield3.setText(update.getThisPlayer().getFields().get(2).getScore());
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
				setupPlantSource((ImageView) node);
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
			setupPlantSource((ImageView) hand.getChildren().get(0));

		}
	}

	public void setupMakeOffer(final ImageView cardView, final CardPOJO card){
		cardView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("Click on: "+card.getName());
				offerPane.setVisible(true);
				setupOfferTarget(offer);
				for(Node node : hand.getChildren()){
					setupPlantSource((ImageView) node);
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
			cardsString.append(card.getName());
			cardsString.append(",");
		}
		Text descriptionText = new Text("Offer: "+ offerString.toString() + " for: "+ cardsString.toString());

		Button dismissButton = new Button("Dismiss");
		dismissButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				application.client.sendToServer(Protocol.DECLINETRADE + " ");
				myDialog.close();
			}
		});

		Button okButton = new Button("OK");
		okButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				application.client.sendToServer(Protocol.ACCEPTTRADE+Protocol.sendOfferToJSON(Protocol.ACCEPTTRADE, offer.getInitiator(), offer.getCards(), offer.getOffer()));
				myDialog.close();
			}

		});

		Scene myDialogScene = new Scene(VBoxBuilder.create()
				.children(new Text("Hello! it's My Dialog."), descriptionText, dismissButton, okButton)
				.alignment(Pos.CENTER)
				.padding(new Insets(10))
				.build());

		myDialog.setScene(myDialogScene);
		myDialog.show();
	}

	public void sendOffer(){
		ArrayList<CardPOJO> cards = new ArrayList<CardPOJO>();
		cards.add(offerItem);
		application.getClient().sendToServer(Protocol.PROPOSETRADE + Protocol.sendOfferToJSON(Protocol.PROPOSETRADE, application.getUsername(), cards, offerList));
		offerList.clear();
		offerItem = null;
	}

	public void harvest1(){
		application.getClient().sendToServer(Protocol.HARVEST+" 0");
	}

	public void harvest2(){
		application.getClient().sendToServer(Protocol.HARVEST+" 1");
	}

	public void harvest3(){
		application.getClient().sendToServer(Protocol.HARVEST+" 2");
	}

	public void buy3(){
		application.getClient().sendToServer(Protocol.BUYBEANFIELD);
	}

	public void drawcard(){
		application.getClient().sendToServer(Protocol.DRAWCARDS);
	}

	public void drawfaceupcard(){
		application.getClient().sendToServer(Protocol.DRAWFACEUPCARDS);
	}

	public void nextPhase(){
		application.getClient().sendToServer(Protocol.NEXTPHASE);
	}

	public void sendmessage(){
		if(chatmessage.getText().length()>0){
			application.getClient().sendToServer(Protocol.CHAT + " "+application.getUsername()+": " +chatmessage.getText());
			chatmessage.setText("");
		}
	}

	public boolean isActivePlayer(String activePlayer){
		return (activePlayer.equals(application.getUsername()));
	}

	void setupOfferTarget(final HBox targetBox){
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
					offerList.add(new CardPOJO(db.getString(), ""));
					event.setDropCompleted(true);
				}else{
					event.setDropCompleted(false);
				}
				event.consume();
			}
		});
	}

	void setupPlantTarget(final boolean isFromHand, final ImageView targetBox, final int fieldid){
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
					targetBox.setImage(db.getImage());
					if(isFromHand){
						application.getClient().sendToServer(Protocol.PLANTBEAN +" "+ fieldid+" "+db.getString());
					} else {
						application.getClient().sendToServer(Protocol.PLANTASIDEBEAN +" "+ fieldid+" "+db.getString());
					}
					
					event.setDropCompleted(true);
				}else{
					event.setDropCompleted(false);
				}
				event.consume();
			}
		});
	}

	void setupPlantSource(final ImageView source){

		source.setOnDragDetected(new EventHandler <MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				/* allow any transfer mode */
				Dragboard db = source.startDragAndDrop(TransferMode.COPY);

				/* put a image on dragboard */
				ClipboardContent content = new ClipboardContent();

				Image sourceImage = source.getImage();
				content.putImage(sourceImage);
				content.putString(((CardPOJO)source.getUserData()).getName());
				db.setContent(content);

				event.consume();
			}
		});
		source.setOnDragDone(new EventHandler<DragEvent>() {
			@Override	
			public void handle(DragEvent event) {
				if(event.isAccepted()){
					hand.getChildren().remove(source);
					event.consume();
				}
			}

		});
	}
}