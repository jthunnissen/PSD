
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
import org.json.PlayerPOJO;

import bohnanza.standard.server.Protocol;


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
	ImageView field2;
	@FXML
	ImageView field3;
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
	TextArea chatbox;
	@FXML
	TextField chatmessage;
	@FXML
	Button sendmessage;

	private ClientGUI application;
	private ArrayList<String> offerList = new ArrayList<String>();
	private CardPOJO offerItem;


	public void setApp(ClientGUI application){
		this.application = application;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void addChat(String text){
		chatbox.setText(chatbox.getText() +"\n"+text);
	}

	public void update(GamePOJO update){
		// Players + scores
		updatePlayersList(update);
		// Hand view of this player
		updateThisPlayerHand(update);

		if(this.isActivePlayer(update.getCurrentPlayer().getName())) {
			// Build actions view		
			updateActionsView(update);
		}


		// Build trading phase

		// Actions window
		if(isActivePlayer(update.getCurrentPlayer().getName())){

		}

		// Trading area - Face up cards from active player
		PlayerPOJO activePlayer = update.getCurrentPlayer();

		if(tradearea.getChildren().size() > 0){
			tradearea.getChildren().clear();
		}
		for(CardPOJO card : activePlayer.getFaceUp()){
			ImageView cardView = new ImageView(card.getImage());
			this.setupGestureSource(cardView, card.getName());
			tradearea.getChildren().add(cardView);
			cardView = new ImageView(card.getImage());
			tradearea.getChildren().add(cardView);
			initOffer(cardView, card);
		}
	}

	public void updatePlayersList(GamePOJO update) {
		ArrayList<PlayerPOJO> playersPOJOS = update.getPlayers();
		ObservableList<String> items = FXCollections.observableArrayList();
		for(PlayerPOJO playerPOJO : playersPOJOS) {
			if(update.getCurrentPlayer().equals(playerPOJO.getName())){
				items.add(">>> "+playerPOJO.getName() + " - " + playerPOJO.getScore());
			} else {
				items.add(playerPOJO.getName() + " - " + playerPOJO.getScore());	
			}

		}
		players.setItems(items);
	}

	public void updateThisPlayerHand(GamePOJO update){
		hand.getChildren().clear();
		PlayerPOJO player = update.getThisPlayer();
		for(CardPOJO card : player.getHand()){
			ImageView cardView = new ImageView(card.getImage());
			this.setupGestureSource(cardView, card.getName());
			hand.getChildren().add(cardView);
			cardView = new ImageView(card.getImage());
		}
	}

	public void updateActionsView(GamePOJO update){
		ArrayList<String> actions = update.getThisPlayer().getActions();
		if(actions.contains(Protocol.BUYBEANFIELD)){
			harvest3.setVisible(false);
			buy3.setVisible(true);
		}
		if(actions.contains(Protocol.DRAWCARDS)){
			drawcard.setVisible(true);
		}
		if(actions.contains(Protocol.DRAWFACEUPCARDS)){
			// TODO
		}
		if(actions.contains(Protocol.HARVEST)){
			harvest1.setVisible(true);
			harvest2.setVisible(true);
			if(update.getThisPlayer().getFields().size() > 2){
				harvest3.setVisible(true);
			}
		}
		if(actions.contains(Protocol.PLANTASIDEBEAN)){
			// TODO
		}
		if(actions.contains(Protocol.PLANTBEAN)){
			setupGestureTarget(field1, 1);
			setupGestureTarget(field2, 2);
			if(update.getThisPlayer().getFields().size() > 2){
				// Player has bought third field
				setupGestureTarget(field3, 3);
				harvest3.setVisible(true);
			}
		}
	}


	public void initOffer(final ImageView cardView, final CardPOJO card){
		cardView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("Click on: "+card.getName());
				offer.setVisible(true);
				setupOfferTarget(offer);
				if(offerItem != card) {
					offerItem = card;
					offer.getChildren().clear();
					offerList.clear();
				}};
		});

	}

	public void viewOffer(final String player, String card, String offer){
		final Stage myDialog = new Stage();
		myDialog.initModality(Modality.WINDOW_MODAL);

		Text descriptionText = new Text(player +" offers "+ offer + " for: "+ card);

		Button dismissButton = new Button("Dismiss");
		dismissButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				application.client.sendToServer(Protocol.DECLINETRADE + " " + player);
				myDialog.close();
			}
		});

		Button okButton = new Button("OK");
		okButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				application.client.sendToServer(Protocol.ACCEPTTRADE + " " + player);
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
		StringBuilder result = new StringBuilder();
		for(String string : offerList) {
			result.append(string);
			result.append(",");
		}
		String cards= result.length() > 0 ? result.substring(0, result.length() - 1): "";
		application.getClient().sendToServer(Protocol.PROPOSETRADE + ";"+offerItem.getName() + ";"+cards);
	}

	public void harvest1(){
		application.getClient().sendToServer(Protocol.HARVEST+" 1");
	}

	public void harvest2(){
		application.getClient().sendToServer(Protocol.HARVEST+" 2");
	}

	public void harvest3(){
		application.getClient().sendToServer(Protocol.HARVEST+" 3");
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
					offerList.add(db.getString());
					event.setDropCompleted(true);
				}else{
					event.setDropCompleted(false);
				}
				event.consume();
			}
		});
	}

	void setupGestureTarget(final ImageView targetBox, final int fieldid){
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
					application.getClient().sendToServer(Protocol.PLANTBEAN +" "+ fieldid+" "+db.getString());
					event.setDropCompleted(true);
				}else{
					event.setDropCompleted(false);
				}
				event.consume();
			}
		});
	}

	void setupGestureSource(final ImageView source, final String cardName){

		source.setOnDragDetected(new EventHandler <MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				/* allow any transfer mode */
				Dragboard db = source.startDragAndDrop(TransferMode.COPY);

				/* put a image on dragboard */
				ClipboardContent content = new ClipboardContent();

				Image sourceImage = source.getImage();
				content.putImage(sourceImage);
				content.putString(cardName);
				db.setContent(content);

				event.consume();
			}
		});
		source.setOnDragDone(new EventHandler<DragEvent>() {
			@Override	
			public void handle(DragEvent event) {
				hand.getChildren().remove(source);
				event.consume();
			}

		});
	}
}