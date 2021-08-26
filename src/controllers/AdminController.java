package controllers;

import dao.AuthentificationImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Authentification;

public class AdminController {
	Authentification au = null;
	public static boolean etat = false;
	AuthentificationImpl auImpl = new AuthentificationImpl();
	Stage primaryStage = new Stage();
	Alert alert = new Alert(null);
	@FXML
	private ComboBox<String> cbxRole;

	@FXML
	private TextField txtLogin;
	@FXML
	private TableColumn<Authentification, Integer> colId;

	@FXML
	private TableColumn<Authentification, String> colRole;

	@FXML
	private TableColumn<Authentification, String> colLogin;

	@FXML
	private TableColumn<Authentification, String> colPassword;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Button btnRafraichir;

	@FXML
	private Button btnValider;

	@FXML
	private Button btnAnnuler;

	@FXML
	private TableView<Authentification> TabUtilisateur;

	@FXML
	private Button btnSupprimer;

	@FXML
	void OnAction(ActionEvent event) {
		Button btn = (Button) event.getSource();
		if (btn == btnAnnuler)
			vider();
		else {
			if (cbxRole.getSelectionModel().isEmpty() || txtLogin.getText().isEmpty()
					|| txtPassword.getText().isEmpty()) {
				alerter("Tous les champs sont obligatoire", "Information", 'w');
			} else {
				au = new Authentification(txtLogin.getText().trim(), txtPassword.getText().trim(),
						cbxRole.getSelectionModel().getSelectedItem().trim());
				if (auImpl.ajouter(au)) {
					alerter("ajouter avec success", "Information", 'i');
					chargerTabl();
					vider();
				} else {
					alerter("erreur connexion", "Information", 'e');
				}
			}
		}
	}

	@FXML
	private Button btnModifier;

	public void vider() {
		txtLogin.clear();
		txtPassword.clear();
		cbxRole.getSelectionModel().clearSelection();
		btnSupprimer.setVisible(false);
		btnModifier.setVisible(false);
	}

	public void initialize() {
		cbxRole.getItems().addAll("Directeur", "Administrateur", "Caissier", "Chef caissier");
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
		colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
		colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
		chargerTabl();
		btnSupprimer.setVisible(false);
		btnModifier.setVisible(false);

	}

	public void chargerTabl() {
		TabUtilisateur.getItems().clear();
		auImpl.list().stream().forEach(TabUtilisateur.getItems()::add);
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
	void verifier(MouseEvent event) {
		btnSupprimer.setVisible(true);
		btnModifier.setVisible(true);
		if (!TabUtilisateur.getSelectionModel().isEmpty()) {
			au = new Authentification(TabUtilisateur.getSelectionModel().getSelectedItem().getId(),
					TabUtilisateur.getSelectionModel().getSelectedItem().getLogin(),
					TabUtilisateur.getSelectionModel().getSelectedItem().getPassword(),
					TabUtilisateur.getSelectionModel().getSelectedItem().getRole());
			AdminEditController.setAuthentification(au);
		}
	}

	@FXML
	void btnAction(ActionEvent event) {
		Button btn = (Button) event.getSource();
		if (btn == btnSupprimer) {
			if (!TabUtilisateur.getSelectionModel().isEmpty()) {
				Alert alert = new Alert(null);
				alert.setAlertType(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation");
				alert.setContentText("Voulez vous supprimer?");
				alert.showAndWait().ifPresent(x -> {
					if (x == ButtonType.OK || x == ButtonType.YES) {
						if (auImpl.supprimer(au.getId())) {
							alerter("suppression reussie", "information", 'i');
							vider();
							au = null;
						} else {
							alerter("suppression a echouée", "information", 'e');
						}
					}
				});
			} else {
				alerter("veuillez selectionner un element du tableau", "information", 'w');
			}
		} else if (event.getSource() == btnModifier) {
			if (!TabUtilisateur.getSelectionModel().isEmpty()) {
				fenetre("../views/AdminEdit.fxml", "Modification");
			} else {
				alerter("veuillez selectionner un element du tableau", "information", 'w');
			}

		} else {
			chargerTabl();
		}
		chargerTabl();
	}

	private void fenetre(String path, String title) {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource(path));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.centerOnScreen();
			primaryStage.setTitle(title);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
