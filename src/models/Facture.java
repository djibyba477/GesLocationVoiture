package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Facture {
	private SimpleStringProperty nom, prenom, voiture;
	private SimpleIntegerProperty prix, restant;

	public String getNom() {
		return nom.get();
	}

	public String getPrenom() {
		return prenom.get();
	}

	public String getVoiture() {
		return voiture.get();
	}

	public int getMontant() {
		return prix.get();
	}

	public int getRestant() {
		return restant.get();
	}

	public Facture(String prenom, String nom, String voiture, int montant, int restant) {
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.voiture = new SimpleStringProperty(voiture);
		this.prix = new SimpleIntegerProperty(montant);
		this.restant = new SimpleIntegerProperty(restant);
	}

}
