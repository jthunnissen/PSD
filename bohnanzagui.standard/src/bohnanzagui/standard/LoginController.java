package bohnanzagui.standard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/** This class holds the Controller for the login window 
*
* @author Anne van de Venis
* @version 1.0
*/
public class LoginController extends AnchorPane implements Initializable {

	@FXML
	TextField host;
	@FXML
	public TextField username;
	@FXML
	Button login;
	@FXML
	Label error;

	/**
	 * ClientGUI that controls GUI
	 */
	private ClientGUI application;

	/**
	 * Sets application that controls GUI
	 * @param application  
	 */
	public void setApp(ClientGUI application) {
		this.application = application;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		error.setText("");
	}

	/**
	 * Handler when a users wants to join the game
	 * @param event
	 */
	public void doLogin(ActionEvent event) {
		if(application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
		} else {
			error.setText("Logging in...");
			application.testLogin(host.getText(), username.getText());
		}
	}

	/**
	 * Handler that is called when username verification has completed
	 * @param ok
	 */
	public void checkLogin(boolean ok) {
		if(ok) {
			application.state = ClientGUI.AWAITING_START;
		} else
			username.setText("Username already taken");
	}

}