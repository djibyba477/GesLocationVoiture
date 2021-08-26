package controllers;

import dao.AuthentificationImpl;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.Authentification;

public class AuthentificationController {
	Authentification au;
	AuthentificationImpl auImpl = new AuthentificationImpl();
	Stage primaryStage = new Stage();
	Alert alert = new Alert(null);
	@FXML
	private TextField txtLogin;

	@FXML
	private TextField txtPassword;

	@FXML
	private Button btnConnexion;

	@FXML
	private ComboBox<String> cbxRole;

	@FXML
	void connexion(ActionEvent event) {
		if (txtLogin.getText().isEmpty() || txtPassword.getText().isEmpty() || cbxRole.getSelectionModel().isEmpty()) {
			alerter("Tous les champs sont obligatoire", "Information", 'w');
		} else {
			au = new Authentification(txtLogin.getText().trim(), txtPassword.getText().trim(),
					cbxRole.getSelectionModel().getSelectedItem().trim());
			if (auImpl.connexion(au) != -1) {
				Stage statge = (Stage) Stage.getWindows().get(0);
				statge.close();
				controller(cbxRole.getSelectionModel().getSelectedItem().trim());
			} else {
				vider();
				alerter("erreur connexion", "Information", 'e');
			}
		}
	}

	public void alerter(String Content, String title, char type) {
		switch (type) {
		case 'i':
			alert.setAlertType(AlertType.INFORMATION);
			alert.setTitle(title);
			alert.setContentText(Content);
			alert.show();
			break;
		case 'e':
			alert.setAlertType(AlertType.ERROR);
			alert.setTitle(title);
			alert.setContentText(Content);
			alert.show();
			break;
		case 'w':
			alert.setAlertType(AlertType.WARNING);
			alert.setTitle(title);
			alert.setContentText(Content);
			alert.show();
			break;
		}
	}

	private void fenetre(String path, String title) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(path));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle(title);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void controller(String nom) {
		switch (nom) {
		case "Administrateur":
			fenetre("../views/Admin.fxml", "Administrateur");
			break;
		case "Caissier":
			fenetre("../views/Caissier.fxml", "Caissier");
			break;
		case "Chef caissier":
			fenetre("../views/ChefCaissier.fxml", "Chef Caissier");
			break;
		case "Directeur":
			fenetre("../views/Directeur.fxml", "Directeur");
			break;
		}
	}

	public void vider() {
		txtLogin.setText("");
		txtPassword.setText("");
		cbxRole.getSelectionModel().clearSelection();
	}

	public void initialize() {
		cbxRole.getItems().addAll("Directeur", "Administrateur", "Caissier", "Chef caissier");

	}

}
