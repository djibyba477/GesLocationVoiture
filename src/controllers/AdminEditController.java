package controllers;

import dao.AuthentificationImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import models.Authentification;



public class AdminEditController {
	static Authentification au;
	boolean etat=false;
    @FXML
    private ComboBox<String> cbxRole;

    @FXML
    private TextField txtLogin;

    @FXML
    private Button btnModifier;

    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private TextField txtId;

    public void initialize() {
    	cbxRole.getItems().addAll("Directeur","Administrateur","Caissier","Chef caissier");
    	txtId.setText(au.getId()+"");
    	txtLogin.setText(au.getLogin());
    	txtPassword.setText(au.getPassword());
    	cbxRole.setValue(au.getRole());
     }

	public static void setAuthentification(Authentification aut) {
		
		au=new Authentification(aut.getId(), aut.getLogin(), aut.getPassword(),aut.getRole());
	}
	  @FXML
	    void click(ActionEvent event) {
		  	if(event.getSource()==btnModifier) {
		  		if(txtLogin.getText().isEmpty() || txtPassword.getText().isEmpty() || cbxRole.getSelectionModel().isEmpty()) {
			  		alerter("veuillez remplir les champs", "information", 'w');
			  	}else {
			  		AuthentificationImpl auimpl=new AuthentificationImpl();
			  		au=new Authentification(Integer.parseInt(txtId.getText()), txtLogin.getText().trim(), txtPassword.getText().trim(), cbxRole.getSelectionModel().getSelectedItem().trim());
			  		if(auimpl.modifier(au)) {
			  			alerter("modifier avec success", "information", 'i');
			  			au=null;
			  			Stage e= (Stage) Stage.getWindows().get(1);
			  			e.close();
			  		}else {
			  			alerter("modifier a echouee", "information", 'e');
			  		}
			  	}
		  	}
	    }
	  public void alerter(String Content,String title,char type) {
		  Alert alert=new Alert(null);
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
