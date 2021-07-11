package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class FactureController {

    @FXML
    private TextField txtVoiture;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtMontant;

    @FXML
    private TextField txtDateFin;

    @FXML
    private Button btnImprimer;

    @FXML
    private ImageView img;

    @FXML
    private DatePicker txtDate;

    @FXML
    void click(ActionEvent event) {

    }

}
