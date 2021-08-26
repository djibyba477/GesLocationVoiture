package controllers;

import java.time.LocalDate;

import dao.FactureImpl;
import dao.Ifacture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FactureController {
	Ifacture fac = new FactureImpl();
	@FXML
	private TextField txtVoiture;

	@FXML
	private TextField txtPrenom;

	@FXML
	private TextField txtPrix;

	@FXML
	private Button btnImprimer;

	@FXML
	private DatePicker txtDate;

	@FXML
	private TextField txtNom;

	@FXML
	private TextField txtRestant;

	@FXML
	private TextField txtRecherche;

	@FXML
	private Button btnRechercher;

	@FXML
	void click(ActionEvent event) {
		Button button = (Button) event.getSource();
		if (button == btnRechercher) {
			if (!txtRecherche.getText().isEmpty()) {
				try {
					int pie = Integer.parseInt(txtRecherche.getText());
					if (fac.facture(pie) != null) {
						txtNom.setText("Nom : " + fac.facture(pie).getNom());
						txtPrenom.setText("Prenom : " + fac.facture(pie).getPrenom());
						txtPrix.setText("Prix : " + fac.facture(pie).getMontant() + "");
						txtRestant.setText("Restant : " + fac.facture(pie).getRestant() + "");
						txtVoiture.setText("Voiture : " + fac.facture(pie).getVoiture());
						txtRecherche.clear();
						btnImprimer.setVisible(true);
					} else {
						btnImprimer.setVisible(false);
						txtNom.clear();
						txtPrenom.clear();
						txtPrix.clear();
						txtRestant.clear();
						txtVoiture.clear();
					}
				} catch (Exception e) {
					alerter("Le piece est constitué uniquement du numerique", "Information", 'i');
				}
			}
		}
	}

	public void alerter(String Content, String title, char type) {
		Alert alert = new Alert(null);
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

	public void initialize() {
		txtDate.setValue(LocalDate.now());
		btnImprimer.setVisible(false);
	}

}
