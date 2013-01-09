
package bohnanza.standard.client;

/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Login Controller.
 */
public class LoginController extends AnchorPane implements Initializable {

	@FXML
	TextField host;
	@FXML
	TextField username;
	@FXML
	Button login;
	@FXML
	Label error;

	private ClientGUI application;


	public void setApp(ClientGUI application){
		this.application = application;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		error.setText("");
	}

	public void doLogin(ActionEvent event) {
		if (application == null){
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
		} else {
			error.setText("Logging in...");
			application.testLogin(host.getText(), username.getText());
		}
	}
	
	public void checkLogin(boolean ok){
		if(ok)
			application.goToGame();
		else
			username.setText("Username already taken");
	}

}