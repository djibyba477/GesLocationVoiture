package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Client {
	private SimpleStringProperty nom, prenom, piece, permi;
	private SimpleIntegerProperty id;

	public String getNom() {
		return nom.get();
	}

	public String getPrenom() {
		return prenom.get();
	}

	public String getPiece() {
		return piece.get();
	}

	public String getPermi() {
		return permi.get();
	}

	public int getId() {
		return id.get();
	}

	public Client(int id, String nom, String prenom, String piece, String permi) {
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.piece = new SimpleStringProperty(piece);
		this.permi = new SimpleStringProperty(permi);
		this.id = new SimpleIntegerProperty(id);
	}

	public Client(String nom, String prenom, String piece, String permi) {
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.piece = new SimpleStringProperty(piece);
		this.permi = new SimpleStringProperty(permi);
	}

}
