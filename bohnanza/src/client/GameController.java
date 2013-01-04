package client;

/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

import org.json.CardPOJO;
import org.json.PlayerPOJO;

import server.Protocol;

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
	ImageView field1;
	@FXML
	ImageView field2;
	@FXML
	ImageView field3;
	@FXML
	Button action1;
	@FXML
	Button action2;
	@FXML
	Button action3_harvast;
	@FXML
	Button action3_buy;
	@FXML
	Button action_draw;

	private ClientGUI application;


	public void setApp(ClientGUI application){
		this.application = application;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void update(String update){
		//String[] commandos = update.split(" ");
		Protocol protocol = new Protocol(null);

		// Players + scores
		ArrayList<PlayerPOJO> playersPOJOS = protocol.fromJSONGetPlayers(update, null);
		ObservableList<String> items = FXCollections.observableArrayList();
		for(PlayerPOJO playerPOJO : playersPOJOS) {
			if(protocol.fromJSONCurrentPlayer(update).equals(playerPOJO.getName())){
				items.add(">>> "+playerPOJO.getName() + " - " + playerPOJO.getScore());
			} else {
				items.add(playerPOJO.getName() + " - " + playerPOJO.getScore());	
			}
			
		}
		players.setItems(items);


		// This playerPOJO
		hand.getChildren().clear();
		PlayerPOJO player = protocol.fromJSONGetPlayers(update, application.getUsername()).get(0);
		for(CardPOJO card : player.getHand()){
			ImageView cardView = new ImageView(card.getImage());
			this.setupGestureSource(cardView, card.getName());
			hand.getChildren().add(cardView);
			cardView = new ImageView(card.getImage());
			hand.getChildren().add(cardView);
		}

		setupGestureTarget(field1, 1);
		setupGestureTarget(field2, 2);
		if(player.getFields().size() > 2){
			// Player has bought third field
			setupGestureTarget(field3, 3);
			action3_harvast.setVisible(true);
			action3_buy.setVisible(false);
		} else {
			// Default FMXL
			action3_harvast.setVisible(false);	
		}
	}

	public void harvast1(){
		application.getClient().sendToServer("HARVAST 1");
	}
	
	public void harvast2(){
		application.getClient().sendToServer("HARVAST 2");
	}
	
	public void harvast3(){
		application.getClient().sendToServer("HARVAST 3");
	}
	
	public void buy3(){
		application.getClient().sendToServer("BUYFIELD 3");
	}
	
	public void drawCard(){
		application.getClient().sendToServer("DRAWCARD");
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
					application.getClient().sendToServer("PLANT "+fieldid+" "+db.getString());
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
