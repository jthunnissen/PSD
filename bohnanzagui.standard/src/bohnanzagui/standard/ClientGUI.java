package bohnanzagui.standard;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.json.JSONObject;
import org.json.OfferPOJO;
import org.json.Protocol;

/** This class is the runnable class, that starts a client GUI 
 *
 * @author Anne van de Venis
 * @version 1.0
 */
public class ClientGUI extends Application {
	/**
	 * Stage that contains active panel
	 */
	Stage stage;
	/**
	 * Thread that contains connection with server
	 */
	Client client;
	/**
	 * Controller for the game view
	 */
	GameController gameController;
	/**
	 * Controller for the login view
	 */
	LoginController loginController;
	/**
	 * Username of this player
	 */
	String username = "";
	/**
	 * Previous gamestate
	 */
	private String oldResponse;

	/**
	 * States for the this client
	 */
	public static final int AWAITING_USERNAMECHECK = 0;
	public static final int AWAITING_START = 3;
	public static final int AWAITING_GAMEUPDATE = 1;
	public static final int AWAITING_OFFER = 2;
	/**
	 * Initial state, waiting for username check
	 */
	public int state = AWAITING_USERNAMECHECK;

	public static void main(String[] args) {
		Application.launch(ClientGUI.class, (java.lang.String[]) null);
	}

	/**
	 * Set username for this client
	 * @param username Username of this player
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Returns username for this client
	 * @return The username for this client
	 */
	public String getUsername() {
		return username;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setOnCloseRequest((new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent arg0) {
					java.lang.System.exit(0);
				}
			}));

			stage = primaryStage;
			gotoLogin();
			primaryStage.show();
		} catch(Exception ex) {
			Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Change the view to the login panel
	 */
	private void gotoLogin() {
		try {
			loginController = (LoginController) replaceSceneContent("Login.fxml");
			loginController.setApp(this);
		} catch(Exception ex) {
			Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Change the view to the game panel
	 * @param type Type of the game (StandardGame or AlCabohneGame)
	 */
	public void goToGame(int type) {
		try {
			if(type == 1) {
				gameController = (StandardGameController) replaceSceneContent("Game.fxml");
			} else {
				gameController = (GameController) replaceSceneContent("Game.fxml");
			}
			gameController.setApp(this);

		} catch(Exception ex) {
			Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Sends username to server to be validated
	 * @param host
	 * @param username
	 */
	public void testLogin(String host, String username) {
		if(client == null) {
			setUsername(username);
			client = new Client(this, host);
		}
		client.sendToServer("NEWPLAYER " + username);
	}

	
	/** 
	 * Updates the GUI (on UI-thread)
	 * @param update response from server
	 * */
	public void update(final String update) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {

					JSONObject response = new JSONObject(update);
					String action = response.getString("type");
					if(state == ClientGUI.AWAITING_USERNAMECHECK && action.equals("usernamecheck")) {
						loginController.checkLogin(Protocol.usernameFromJSON(response));
					} else if(action.equals("waiting")) {
						loginController.username.setText("waiting for game...");
					} else if(action.equals(Protocol.CHAT)) {
						gameController.addChat(Protocol.chatFromJSON(response));
					} else if(action.equals(Protocol.ERROR)) {
						showError(response.getString("response"));
						if(state == ClientGUI.AWAITING_GAMEUPDATE) {
							gameController.update(Protocol.fromJSON(oldResponse.toString(), username));
						}
					} else if(action.equals(Protocol.PROPOSETRADE)) {
						OfferPOJO offer = Protocol.sendOfferFromJSON(response);
						((StandardGameController) gameController).viewOffer(offer);
					} else if(action.equals("gameupdate")) {
						if(state == ClientGUI.AWAITING_START) {
							goToGame(response.getInt(Protocol.GAME_TYPE));
							state = ClientGUI.AWAITING_GAMEUPDATE;
						}
						oldResponse = update;
						gameController.update(Protocol.fromJSON(update, username));
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	/**
	 * Show alertbox with a message
	 * @param message Message to be shown in alertbox
	 */
	public void showError(String message) {
		final Stage myDialog = new Stage();
		myDialog.initModality(Modality.WINDOW_MODAL);

		Text descriptionText = new Text(message);

		Button dismissButton = new Button("Dismiss");
		dismissButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				myDialog.close();
			}
		});

		Scene myDialogScene = new Scene(VBoxBuilder.create().children(descriptionText, dismissButton).alignment(Pos.CENTER).padding(new Insets(10)).build());

		myDialog.setScene(myDialogScene);
		myDialog.show();
	}

	/**
	 * Replace content of current panel
	 * @param fxml URL to FXML file to be shown
	 * @return
	 * @throws Exception
	 */
	private Initializable replaceSceneContent(String fxml) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("res/ui/" + fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(getClass().getClassLoader().getResource("res/ui/" + fxml));
		AnchorPane page = null;
		try {
			page = (AnchorPane) loader.load(in);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
		// store the stage height in case the user has resized the window
		double stageWidth = stage.getWidth();
		if(!Double.isNaN(stageWidth)) {
			stageWidth -= (stage.getWidth() - stage.getScene().getWidth());
		}
		double stageHeight = stage.getHeight();
		if(!Double.isNaN(stageHeight)) {
			stageHeight -= (stage.getHeight() - stage.getScene().getHeight());
		}
		// Scene scene = stage.getScene();
		// if (scene == null) {
		Scene scene = new Scene(page);
		if(!Double.isNaN(stageWidth)) {
			page.setPrefWidth(stageWidth);
		}
		if(!Double.isNaN(stageHeight)) {
			page.setPrefHeight(stageHeight);
		}
		stage.setScene(scene);
		// } else {
		// stage.getScene().setRoot(page);
		// }
		stage.sizeToScene();
		return (Initializable) loader.getController();
	}

	/**
	 * Send message to server
	 * @param string Message to be send to server
	 */
	public void sendToServer(String string) {
		client.sendToServer(string);
	}
}