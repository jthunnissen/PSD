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

	private ClientGUI application;


	public void setApp(ClientGUI application){
		this.application = application;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//error.setText("");
		ObservableList<String> data =FXCollections.observableArrayList (
				"Player1");
		players.setItems(data);
	}

	public void update(String update){
		//String[] commandos = update.split(" ");
		Protocol protocol = new Protocol(null);

		// Players + scores
		ArrayList<PlayerPOJO> playersPOJOS = protocol.fromJSONGetPlayers(update, null);
		ObservableList<String> items = FXCollections.observableArrayList();
		for(PlayerPOJO playerPOJO : playersPOJOS) {
			items.add(playerPOJO.getName() + " - " + playerPOJO.getScore());
		}
		players.setItems(items);


		// This player
		PlayerPOJO player = protocol.fromJSONGetPlayers(update, application.getUsername()).get(0);
		for(CardPOJO card : player.getHand()){
			ImageView cardView = new ImageView(card.getImage());
			this.setupGestureSource(cardView);
			hand.getChildren().add(cardView);
			cardView = new ImageView(card.getImage());
			hand.getChildren().add(cardView);
		}

		setupGestureTarget(field1);
		setupGestureTarget(field2);



	}


	void setupGestureTarget(final ImageView targetBox){

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

					event.setDropCompleted(true);
				}else{
					event.setDropCompleted(false);
				}

				event.consume();
			}
		});

	}

	void setupGestureSource(final ImageView source){

		source.setOnDragDetected(new EventHandler <MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				/* allow any transfer mode */
				Dragboard db = source.startDragAndDrop(TransferMode.COPY);

				/* put a image on dragboard */
				ClipboardContent content = new ClipboardContent();

				Image sourceImage = source.getImage();
				content.putImage(sourceImage);
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
