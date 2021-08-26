package controllers;

import dao.ReglementImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Reglement;

public class ReglementController {
	private ReglementImpl re = new ReglementImpl();
	private Reglement r;
	private int somme;
	Alert alert = new Alert(null);
	@FXML
	private TextField txtRecherche;
	@FXML
	private TextField txtRestant;
	@FXML
	private TextField txtPrenom;
	@FXML
	private TextField txtNom;
	@FXML
	private Button btnRechercher;
	@FXML
	private TableView<Reglement> tabLocation;

	@FXML
	private TableColumn<Reglement, Integer> colId;

	@FXML
	private TableColumn<Reglement, String> colNom;

	@FXML
	private TableColumn<Reglement, String> colPrenom;
	@FXML
	private Button btnFacture;
	@FXML
	private TableColumn<Reglement, String> colVoiture;
	@FXML
	private TableColumn<Reglement, String> colDebut;

	@FXML
	private TableColumn<Reglement, String> colFin;
	@FXML
	private TableColumn<Reglement, String> colPrix;

	public void initialize() {
		colDebut.setCellValueFactory(new PropertyValueFactory<>("debut"));
		colFin.setCellValueFactory(new PropertyValueFactory<>("fin"));
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
		colVoiture.setCellValueFactory(new PropertyValueFactory<>("voiture"));
		charger();
	}

	public void charger() {
		tabLocation.getItems().clear();
		re.list().stream().forEach(tabLocation.getItems()::add);
	}

	@FXML
	void clickTab(MouseEvent event) {
		if (!tabLocation.getItems().isEmpty()) {
			somme = re.verification(tabLocation.getSelectionModel().getSelectedItem().getId());
			if (somme != -1) {
				if (somme == tabLocation.getSelectionModel().getSelectedItem().getPrix()) {
					txtRestant.setText("complet");
					txtPrenom.setText(tabLocation.getSelectionModel().getSelectedItem().getPrenom());
					txtNom.setText(tabLocation.getSelectionModel().getSelectedItem().getNom());
					btnValider.setVisible(false);
					txtMontant.setDisable(true);
				} else if (somme < tabLocation.getSelectionModel().getSelectedItem().getPrix()) {
					int restant = tabLocation.getSelectionModel().getSelectedItem().getPrix() - somme;
					txtRestant.setText(restant + "");
					txtPrenom.setText(tabLocation.getSelectionModel().getSelectedItem().getPrenom());
					txtNom.setText(tabLocation.getSelectionModel().getSelectedItem().getNom());
					txtMontant.setDisable(false);
					btnValider.setVisible(true);
				}
			} else {
				txtRestant.setText("Premier Reglement");
				txtMontant.setDisable(false);
				btnValider.setVisible(true);
				txtPrenom.setText(tabLocation.getSelectionModel().getSelectedItem().getPrenom());
				txtNom.setText(tabLocation.getSelectionModel().getSelectedItem().getNom());
			}
		}
	}

	public void rechercher(String val) {
		tabLocation.getItems().clear();
		re.list().stream()
				.filter((x) -> x.getNom().contains(val) || x.getPrenom().contains(val) || x.getVoiture().contains(val))
				.forEach(tabLocation.getItems()::add);
	}

	@FXML
	void click(ActionEvent event) {
		if (event.getSource() == btnValider) {
			if (txtMontant.getText().isEmpty()) {
				alerter("le montant doit etre défini", "information", 'w');
			} else {
				try {
					if (Integer.parseInt(txtMontant.getText()) > tabLocation.getSelectionModel().getSelectedItem()
							.getPrix()) {
						alerter("le montant doit etre inferieur ou egal au prix", "information", 'i');
						vider();
					} else {
						if (!tabLocation.getSelectionModel().getSelectedItem().getPrenom().isEmpty()) {
							r = new Reglement(tabLocation.getSelectionModel().getSelectedItem().getId(),
									Integer.parseInt(txtMontant.getText()));
							if (re.ajouter(r)) {
								vider();
								charger();
								alerter("payer avec success", "information", 'i');
							} else {
								alerter("erreur enregistrement", "information", 'e');
							}
						} else {
							alerter("veuillez selectionner une location au niveau du tableau", "information", 'w');
						}
					}
				} catch (NumberFormatException e) {
					alerter("le montant doit etre un entier", "information", 'w');
				}
			}
		} else if (event.getSource() == btnRechercher) {
			if (txtRecherche.getText().isEmpty()) {
				charger();
			} else {
				rechercher(txtRecherche.getText());
			}
		} else if (event.getSource() == btnFacture) {
			fenetre("../views/Facture.fxml", "Facture");

		}
		vider();
	}

	private void fenetre(String path, String title) {
		try {
			Stage primaryStage = new Stage();
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

	public void vider() {
		txtMontant.clear();
		txtRestant.clear();
		txtPrenom.clear();
		txtNom.clear();
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

	@FXML
	private TextField txtMontant;

	@FXML
	private Button btnValider;
	@FXML
	private Button btnAnnuler;

}
