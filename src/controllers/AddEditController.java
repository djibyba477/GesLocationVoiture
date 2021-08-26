package controllers;

import dao.ClientImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import models.Client;

public class AddEditController {
	Alert alert = new Alert(null);
	private static Client cl = null, cli;
	private ClientImpl clImpl = new ClientImpl();
	@FXML
	private TextField txtId;

	@FXML
	private TextField txtNom;

	@FXML
	private TextField txtPrenom;

	@FXML
	private TextField txtPermi;

	@FXML
	private TextField txtPiece;

	@FXML
	private Button btnAjouter;

	@FXML
	void onClick(ActionEvent event) {
		if (btnAjouter.getText().equals("Ajouter")) {
			if (txtNom.getText().isEmpty() || txtPermi.getText().isEmpty() || txtPiece.getText().isEmpty()
					|| txtPrenom.getText().isEmpty()) {
				alerter("veuillez remplir les champs", "information", 'w');
			} else {
				cli = new Client(txtNom.getText(), txtPrenom.getText(), txtPiece.getText(), txtPermi.getText());
				if (clImpl.ajouter(cli)) {
					alerter("enregistrer avec success", "information", 'i');
					Stage stage = (Stage) Stage.getWindows().get(1);
					stage.close();
					vider();
				} else {
					alerter("erreur ajout ", "information", 'e');
				}
			}
		} else {
			if (txtId.getText().isEmpty() || txtNom.getText().isEmpty() || txtPermi.getText().isEmpty()
					|| txtPiece.getText().isEmpty() || txtPrenom.getText().isEmpty()) {
				alerter("veuillez remplir les champs", "information", 'w');
			} else {
				cli = new Client(Integer.parseInt(txtId.getText()), txtNom.getText(), txtPrenom.getText(),
						txtPiece.getText(), txtPermi.getText());
				if (clImpl.modifier(cli)) {
					alerter("modifier avec success", "information", 'i');
					Stage stage = (Stage) Stage.getWindows().get(1);
					stage.close();
					vider();
					cl = null;
				} else {
					alerter("erreur modification ", "information", 'e');
				}
			}
		}
	}

	public void initialize() {
		charger();
	}

	public void charger() {
		if (cl != null) {
			txtId.setText(cl.getId() + "");
			txtNom.setText(cl.getNom());
			txtPermi.setText(cl.getPermi());
			txtPiece.setText(cl.getPiece());
			txtPrenom.setText(cl.getPrenom());
			btnAjouter.setText("Modifier");
		} else {
			txtId.clear();
			txtNom.clear();
			txtPermi.clear();
			txtPiece.clear();
			txtPrenom.clear();
			btnAjouter.setText("Ajouter");
		}
	}

	public static void setClient(Client client) {
		// TODO Auto-generated method stub
		cl = new Client(client.getId(), client.getNom(), client.getPrenom(), client.getPiece(), client.getPermi());
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

	public void vider() {
		txtId.clear();
		txtNom.clear();
		txtPrenom.clear();
		txtPermi.clear();
		txtPiece.clear();
	}
}
