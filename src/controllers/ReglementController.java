package controllers;

import java.io.File;
import java.io.IOException;

import dao.ReglementImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Reglement;

public class ReglementController {
	private ReglementImpl re=new ReglementImpl();
	private Reglement r;
	Alert alert=new Alert(null);
    @FXML
    private TableView<Reglement> tabLocation;

    @FXML
    private TableColumn<Reglement, Integer> colId;

    @FXML
    private TableColumn<Reglement, String> colNom;

    @FXML
    private TableColumn<Reglement, String> colPrenom;

    @FXML
    private TableColumn<Reglement, String> colVoiture;
    @FXML
    private TableColumn<Reglement, String> colDebut;

    @FXML
    private TableColumn<Reglement, String> colFin;
    @FXML
    private TableColumn<Reglement, String> colPrix;

    @FXML
    private TableColumn<Reglement, String> colStatut;

    @FXML
    private TableColumn<Reglement, String> colRestant;
    
    public void initialize() {
    	colDebut.setCellValueFactory(new PropertyValueFactory<>("debut"));
    	colFin.setCellValueFactory(new PropertyValueFactory<>("fin"));
    	colId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    	colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    	colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    	colRestant.setCellValueFactory(new PropertyValueFactory<>("restant"));
    	colStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
    	colVoiture.setCellValueFactory(new PropertyValueFactory<>("voiture"));
    	charger();
    }
    public void charger() {
    	tabLocation.getItems().clear();
    	re.list().stream().forEach(tabLocation.getItems()::add);
    }
    @FXML
    void clickTab(MouseEvent event) {
    	if(!tabLocation.getItems().isEmpty() && tabLocation.getSelectionModel().getSelectedItem().getRestant()>0) {
			txtRestant.setText(tabLocation.getSelectionModel().getSelectedItem().getRestant()+"");
		}
    }
    @FXML
    void click(ActionEvent event) {
    	if(event.getSource()==btnValider) {
    		if(txtMontant.getText().isEmpty()) {
        		alerter("le montant doit etre défini", "information", 'w');
        	}else {
        		try {
        			if(Integer.parseInt(txtMontant.getText())>Integer.parseInt(txtRestant.getText())) {
        				alerter("Le montant est inferieur au restant", "information", 'w');
        			}else {
        				r=new Reglement(tabLocation.getSelectionModel().getSelectedItem().getId(), Integer.parseInt(txtMontant.getText()));
        				if(re.ajouter(r)) {
        					vider();
                			charger();
        					alerter("reglement enregistrer avec success", "information", 'i');
        				}else {
        					alerter("erreur enregistrement", "information", 'e');
        				}
        			}
        		}catch(NumberFormatException e) {
        			alerter("le montant doit etre un entier", "information", 'w');
        		}
        	}
    	}else {
    		vider();
    	}
    }
    public void vider() {
    	txtMontant.clear();
    	txtRestant.clear();
    }
    public void alerter(String Content,String title,char type) {
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

    @FXML
    private TextField txtRestant;

}
