package controllers;



import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;

import dao.ClientImpl;
import dao.LocationImpl;
import dao.VoitureImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Client;
import models.Location;
import models.Voiture;


public class CaissierController {
	ClientImpl clImpl=new ClientImpl();
	VoitureImpl voitureImpl=new VoitureImpl();
	Voiture voiture=new Voiture(null);
	Alert alert=new Alert(null);
	Client client;
	LocationImpl loImpl=new LocationImpl();
	@FXML
    private Button btnNouvelleVoiture;
    @FXML
    private Button btnNouveauCl;
    @FXML
    private Button btnLocation;
    @FXML
    private Button btnReglement;
    @FXML
    private TableView<Client> tabClient;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnRafraichir;
    @FXML
    private TableColumn<Client, Integer> colid;

    @FXML
    private TableColumn<Client, String> colNom;
    
    @FXML
    private DatePicker txtDateD;

    @FXML
    private DatePicker txtDateR;


    @FXML
    private TableColumn<Client, String> colPrenom;

    @FXML
    private TableColumn<Client, String> colPiece;

    @FXML
    private TableColumn<Client, String> colPermi;
    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnAnnuler;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtPiece;

    @FXML
    private TextField txtPermi;

    @FXML
    private TextField txtPrix;

    @FXML
    private TextField txtRecherche;

    @FXML
    private Button btnRechercher;
    @FXML
    private TextField txtId;
    @FXML
    void rafraichirClick(ActionEvent event) {
    	charger();
    }
    @FXML
    private ComboBox<String> cbxVoiture;
    @FXML
    void selected(ActionEvent event) {
    	for (Voiture ve : voitureImpl.liste()) {
			if(ve.getNomCompet().equals(cbxVoiture.getSelectionModel().getSelectedItem())) {
				voiture = new Voiture(ve.getId(),ve.getNomCompet());
			}
		}
    }
 
    public void initialize() {
    	colid.setCellValueFactory(new PropertyValueFactory<>("id"));
    	colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    	colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    	colPiece.setCellValueFactory(new PropertyValueFactory<>("piece"));
    	colPermi.setCellValueFactory(new PropertyValueFactory<>("permi"));
    	charger();
    	chargerCbxVoiture();
    	btnModifier.setVisible(false);
    }
    
    public void rechercher(String val) {
    	tabClient.getItems().clear();
    	 clImpl.list().stream().filter((x)->x.getNom().contains(val) || x.getPrenom().contains(val)).forEach(tabClient.getItems()::add);
    }
    public void chargerCbxVoiture() {
    	cbxVoiture.getItems().clear();
    	for (Voiture v : voitureImpl.liste()) {
			cbxVoiture.getItems().add(v.getNomCompet());
		}
    	}
    public void charger() {
    	tabClient.getItems().clear();
    	clImpl.list().stream().forEach(tabClient.getItems()::add);
    }
    
    @FXML
    void btnAction(ActionEvent event) {
    	if(event.getSource()==btnRechercher) {
    		if(txtRecherche.getText().isEmpty()) {
    			charger();
    		}else {
    			rechercher(txtRecherche.getText());
    		}
    	}else if(event.getSource()==btnAnnuler) {
    		vider();
    	}else if(event.getSource()==btnAjouter) {
    		if(cbxVoiture.getSelectionModel().isEmpty() || txtNom.getText().isEmpty() || txtId.getText().isEmpty() || txtPermi.getText().isEmpty() || txtPiece.getText().isEmpty() || txtPrenom.getText().isEmpty() || txtPrix.getText().isEmpty()) {
    			alerter("veuillez selectioner le client au niveau du tableau et la voiture", "Information", 'w');
    		}else {
    			try {
    				LocalDate localDateD=txtDateD.getValue();
    		    	Date dateD=Date.valueOf(localDateD);
    		    	LocalDate localDateR=txtDateR.getValue();
    		    	Date dateR=Date.valueOf(localDateR);
    		    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        			Location lo=new Location(Integer.parseInt(txtId.getText()), voiture.getId(), format.format(dateD),format.format(dateR), Integer.parseInt(txtPrix.getText()));
        			if(loImpl.ajouter(lo)) {
        				alerter("Ajouter avec success", "information", 'w');
        				vider();
        			}else {
        				alerter("erreur ajout", "information", 'e');
        			}
    			}catch(NumberFormatException e) {
    				alerter("le prix est un entier", "information", 'w');
    			}
    			
    		}
    		//vider();
    	}else if(event.getSource()==btnModifier) {
    		AddEditController.setClient(client);
    		if(!tabClient.getSelectionModel().isEmpty()) {
    			fenetre("../views/AddEditClient.fxml", "Modifier Client");
    		}else {
    			alerter("veuillez selectionner le client a modifier", "information", 'w');
    		}
    	}else if(event.getSource()==btnNouveauCl) {
    		fenetre("../views/AddEditClient.fxml", "Ajouter Client");
    	}else if(event.getSource()==btnNouvelleVoiture) {
    		fenetre("../views/Voiture.fxml", "Voiture");
    	}else if(event.getSource()==btnReglement) {
    		fenetre("../views/Reglement.fxml", "Reglement");
    	}else if(event.getSource()==btnLocation) {
    		fenetre("../views/Location.fxml", "Location");
    	}
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
    public void vider() {
    		txtNom.clear();
        	txtPermi.clear();
        	txtPiece.clear();
        	txtPrenom.clear();
        	txtPrix.clear();
        	cbxVoiture.getSelectionModel().clearSelection();
        	txtId.clear();
        	txtDateD.setValue(LocalDate.now());
        	txtDateR.setValue(LocalDate.now());
        	btnModifier.setVisible(false);
    }
    
    @FXML
    void click(MouseEvent event) {
    	if(!tabClient.getSelectionModel().isEmpty()) {
    		txtNom.setText(tabClient.getSelectionModel().getSelectedItem().getNom());
    		txtPrenom.setText(tabClient.getSelectionModel().getSelectedItem().getPrenom());
    		txtPermi.setText(tabClient.getSelectionModel().getSelectedItem().getPermi());
    		txtPiece.setText(tabClient.getSelectionModel().getSelectedItem().getPiece());
    		txtId.setText(tabClient.getSelectionModel().getSelectedItem().getId()+"");
    		client=new Client(tabClient.getSelectionModel().getSelectedItem().getId(),tabClient.getSelectionModel().getSelectedItem().getNom(),tabClient.getSelectionModel().getSelectedItem().getPrenom(),tabClient.getSelectionModel().getSelectedItem().getPiece(),tabClient.getSelectionModel().getSelectedItem().getPermi());
    		btnModifier.setVisible(true);
    	}else {
    		client=null;
    	}
    }

    private void fenetre(String path,String title) {
    	try {
    		Stage primaryStage=new Stage(); 
			Parent root = FXMLLoader.load(getClass().getResource(path));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.centerOnScreen();
			primaryStage.setTitle(title);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    
 

}
