package bohnanzagui.standard;

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
import org.json.CardPOJO;
import org.json.GamePOJO;
import org.json.PlayerPOJO;
import org.json.Protocol;

/** Game Controller. */
public class GameController extends AnchorPane implements Initializable {

	@FXML
	ListView<String> players;
	@FXML
	protected HBox hand;
	@FXML
	protected ImageView field1;
	@FXML
	protected Label labelfield1;
	@FXML
	protected ImageView field2;
	@FXML
	protected Label labelfield2;
	@FXML
	protected ImageView field3;
	@FXML
	protected Label labelfield3;
	@FXML
	protected Button harvest1;
	@FXML
	protected Button harvest2;
	@FXML
	protected Button harvest3;
	@FXML
	protected Button buy3;
	@FXML
	protected Button nextPhase;
	@FXML
	TextArea chatbox;
	@FXML
	TextField chatmessage;
	@FXML
	Button sendmessage;

	protected ClientGUI application;
	protected Image defaultEmptyImage;

	public void setApp(ClientGUI application) {
		this.application = application;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		defaultEmptyImage = field1.getImage();
	}

	public void addChat(String text) {
		chatbox.setText(chatbox.getText() + "\n" + text);
	}

	public void update(GamePOJO update) {
	}

	public void updatePlayersList(GamePOJO update) {
		ArrayList<PlayerPOJO> playersPOJOS = update.getPlayers();
		ObservableList<String> items = FXCollections.observableArrayList();
		for(PlayerPOJO playerPOJO : playersPOJOS) {
			if(update.getCurrentPlayer().getName().equals(playerPOJO.getName())) {
				items.add(">>> " + playerPOJO.getName() + " - " + playerPOJO.getScore());
			} else {
				items.add(playerPOJO.getName() + " - " + playerPOJO.getScore());
			}
		}
		players.setItems(items);
	}

	public void updateThisPlayerHand(GamePOJO update) {
		hand.getChildren().clear();
		for(CardPOJO card : update.getThisPlayer().getHand()) {
			ImageView cardView = new ImageView(card.getImage());
			cardView.setUserData(card);
			hand.getChildren().add(cardView);
		}
	}

	public void harvest1() {
		application.sendToServer(Protocol.HARVEST + " 0");
	}

	public void harvest2() {
		application.sendToServer(Protocol.HARVEST + " 1");
	}

	public void harvest3() {
		application.sendToServer(Protocol.HARVEST + " 2");
	}

	public void buy3() {
		application.sendToServer(Protocol.BUYBEANFIELD);
	}

	public void drawcard() {
		application.sendToServer(Protocol.DRAWCARDS);
	}

	public void nextPhase() {
		application.sendToServer(Protocol.NEXTPHASE);
	}

	public void sendmessage() {
		if(chatmessage.getText().length() > 0) {
			application.sendToServer(Protocol.CHAT + " " + application.getUsername() + ": " + chatmessage.getText());
			chatmessage.setText("");
		}
	}

	public boolean isActivePlayer(String activePlayer) {
		return(activePlayer.equals(application.getUsername()));
	}

	protected void setupPlantTarget(final boolean isFromHand, final ImageView targetBox, final int fieldid) {
		targetBox.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				if(db.hasImage()) {
					event.acceptTransferModes(TransferMode.MOVE);
				}
				event.consume();
			}
		});

		targetBox.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				if(db.hasImage()) {
					targetBox.setImage(db.getImage());
					if(isFromHand) {
						application.sendToServer(Protocol.PLANTBEAN + " " + fieldid + " " + db.getString());
					} else {
						application.sendToServer(Protocol.PLANTASIDEBEAN + " " + fieldid + " " + db.getString());
					}

					event.setDropCompleted(true);
				} else {
					event.setDropCompleted(false);
				}
				event.consume();
			}
		});
	}

	protected void setupCardDraggable(final ImageView source) {
		source.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				content.putImage(source.getImage());
				content.putString("" + ((CardPOJO) source.getUserData()).getHashcode());
				db.setContent(content);

				event.consume();
			}
		});

	}
}