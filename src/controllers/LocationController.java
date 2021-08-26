package controllers;

import dao.LocationImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Location;

public class LocationController {
	public LocationImpl lo = new LocationImpl();
	@FXML
	private TableView<Location> tabLocation;

	@FXML
	private TableColumn<Location, Integer> colId;
	@FXML
	private TableColumn<Location, String> colVoiture;
	@FXML
	private TableColumn<Location, String> colNom;

	@FXML
	private TableColumn<Location, String> colPrenom;

	@FXML
	private TableColumn<Location, String> colDebut;

	@FXML
	private TableColumn<Location, String> colFin;
	@FXML
	private TableColumn<Location, Integer> colPrix;

	public void initialize() {
		colDebut.setCellValueFactory(new PropertyValueFactory<>("dateD"));
		colFin.setCellValueFactory(new PropertyValueFactory<>("dateF"));
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		colVoiture.setCellValueFactory(new PropertyValueFactory<>("v"));
		colPrix.setCellValueFactory(new PropertyValueFactory<>("somme"));
		charger();
	}

	@FXML
	private Button btnRafraichir;

	@FXML
	void click(ActionEvent event) {
		charger();
	}

	public void charger() {
		tabLocation.getItems().clear();
		lo.list().stream().forEach(tabLocation.getItems()::add);
	}
}
