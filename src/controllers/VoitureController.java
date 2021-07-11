package controllers;

import dao.VoitureImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Voiture;

public class VoitureController {
	public VoitureImpl vo=new VoitureImpl();
	public Alert alert=new Alert(null);
	public Voiture v;
    @FXML
    private TableView<Voiture> tabVoiture;

    @FXML
    private TableColumn<Voiture, Integer> colId;

    @FXML
    private TableColumn<Voiture, String> colNom;
    
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtId;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnAnnuler;
    @FXML
    void btnAction(ActionEvent event) {
    	if(event.getSource()==btnAnnuler) {
    		vider();
    	}else if(event.getSource()==btnAjouter) {
    		if(btnAjouter.getText().equals("Ajouter")) {
    			if(txtNom.getText().isEmpty()) {
        			alerter("le nom doit etre rempli", "information", 'w');
        		}else {
        			v=new Voiture(txtNom.getText());
        			if(vo.ajouter(v)) {
        				alerter("ajouter avec success ", "information", 'i');
        				charger();
        				vider();
        			}else {
        				alerter("erreur ajout", "information", 'e');
        			}
        		}
    		}else {
    			if(txtNom.getText().isEmpty() || txtId.getText().isEmpty()) {
        			alerter("selection une voiture sur le tableau", "information", 'w');
        		}else {
        			v=new Voiture(Integer.parseInt(txtId.getText()),txtNom.getText());
        			if(vo.modifier(v)) {
        				alerter("modifier avec success ", "information", 'i');
        				charger();
        				vider();
        			}else {
        				alerter("erreur modification", "information", 'e');
        			}
        		}
    		}
    		
    	}else {
    		if(tabVoiture.getSelectionModel().isEmpty()) {
    			alerter("Selectionner l'element à supprimer", "information", 'w');
    		}else {
    			v=new Voiture(Integer.parseInt(txtId.getText()),txtNom.getText());
    			alert.setTitle("confirmation");
    			alert.setAlertType(AlertType.CONFIRMATION);
    			alert.setContentText("voulez vous vraiment supprimer");
    			alert.showAndWait().ifPresent((x)->{
    				if(x==ButtonType.OK || x==ButtonType.YES) {
    					if(vo.supprimer(v)) {
    	    				alerter("supprimer avec success", "information", 'i');
    	    				tabVoiture.getSelectionModel().clearSelection();
    	    				charger();
    	    			}else {
    	    				alerter("erreur suppression", "information", 'e');
    	    			}
    				}
    			});
    		}
    	}
    }
    public void vider() {
    	txtId.clear();
    	txtNom.clear();
    	btnAjouter.setText("Ajouter");
    }
    public void initialize() {
    	charger();
    	colId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	colNom.setCellValueFactory(new PropertyValueFactory<>("nomCompet"));
    	
    }
    @FXML
    void tabClick(MouseEvent event) {
    	if(!tabVoiture.getSelectionModel().isEmpty()) {
    		txtNom.setText(tabVoiture.getSelectionModel().getSelectedItem().getNomCompet());
    		txtId.setText(tabVoiture.getSelectionModel().getSelectedItem().getId()+"");
    		btnAjouter.setText("Modifier");
    	}
    }
    public void charger() {
    	tabVoiture.getItems().clear();
    	vo.liste().stream().forEach(tabVoiture.getItems()::add);
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
}
