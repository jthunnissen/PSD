package client;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ClientGUI extends Application {

	Stage stage;
	Client client;
	GameController game;
	String username = "";

	public static void main(String[] args) {
		System.out.println("Hello");
		Application.launch(ClientGUI.class, (java.lang.String[])null);
	}
	
	public Client getClient(){
		return client;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getUsername(){
		return username;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			gotoLogin();
			primaryStage.show();
		} catch (Exception ex) {
			Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void gotoLogin() {
		try {
			LoginController login = (LoginController) replaceSceneContent("Login.fxml");
			login.setApp(this);
		} catch (Exception ex) {
			Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void goToGame(String host, String username){
		if(client == null){
			setUsername(username);
			client = new Client(this, host, username);
		}
		try {
			game = (GameController) replaceSceneContent("Game.fxml");
			game.setApp(this);
		} catch (Exception ex) {
			Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * This method is needed to update UI on UI-thread.
	 * @param update
	 */
	public void update(final String update)
	{
		Platform.runLater(new Runnable() {
			@Override public void run() {
				try{
					game.update(update);
				} catch (Exception ex) {
					ex.printStackTrace();
				}     
			}
		});
	}


	private Initializable replaceSceneContent(String fxml) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		InputStream in = ClientGUI.class.getResourceAsStream(fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(ClientGUI.class.getResource(fxml));
		AnchorPane page = null;
		try {
			page = (AnchorPane) loader.load(in);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			in.close();
		}
		// store the stage height in case the user has resized the window
		double stageWidth = stage.getWidth();
		if (!Double.isNaN(stageWidth)) {
			stageWidth -= (stage.getWidth() - stage.getScene().getWidth());
		}
		double stageHeight = stage.getHeight();
		if (!Double.isNaN(stageHeight)) {
			stageHeight -= (stage.getHeight() - stage.getScene().getHeight());
		}
		//        Scene scene = stage.getScene();
		//        if (scene == null) {
		Scene scene = new Scene(page);
		if (!Double.isNaN(stageWidth)) {
			page.setPrefWidth(stageWidth);
		}
		if (!Double.isNaN(stageHeight)) {
			page.setPrefHeight(stageHeight);
		}
		stage.setScene(scene);
		//        } else {
		//            stage.getScene().setRoot(page);
		//        }
		stage.sizeToScene();
		return (Initializable) loader.getController();
	}

}