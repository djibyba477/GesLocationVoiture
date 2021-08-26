package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Location {
	private SimpleStringProperty dateF, dateD, nom, prenom, v;
	private SimpleIntegerProperty id, somme, voiture, idCl;

	public int getIdCl() {
		return idCl.get();
	}

	public String getDateD() {
		return dateD.get();
	}

	public int getId() {
		return id.get();
	}

	public String getV() {
		return v.get();
	}

	public String getDateF() {
		return dateF.get();
	}

	public int getVoiture() {
		return voiture.get();
	}

	public String getNom() {
		return nom.get();
	}

	public String getPrenom() {
		return prenom.get();
	}

	public int getSomme() {
		return somme.get();
	}

	public Location(int id, int idCl, int voiture, String dateD, String dateF, int somme) {
		this.dateF = new SimpleStringProperty(dateF);
		this.dateD = new SimpleStringProperty(dateD);
		this.id = new SimpleIntegerProperty(id);
		this.idCl = new SimpleIntegerProperty(idCl);
		this.voiture = new SimpleIntegerProperty(voiture);
		this.somme = new SimpleIntegerProperty(somme);
	}

	public Location(int idCl, int voiture, String dateD, String dateF, int somme) {
		this.dateF = new SimpleStringProperty(dateF);
		this.dateD = new SimpleStringProperty(dateD);
		this.idCl = new SimpleIntegerProperty(idCl);
		this.voiture = new SimpleIntegerProperty(voiture);
		this.somme = new SimpleIntegerProperty(somme);
	}

	public Location(String nom, String prenom, int voiture, String dateF, int somme) {
		this.dateF = new SimpleStringProperty(dateF);
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.voiture = new SimpleIntegerProperty(voiture);
		this.somme = new SimpleIntegerProperty(somme);
	}

	public Location(int id, String nom, String prenom, String v, String dateD, String dateF, int somme) {
		this.dateF = new SimpleStringProperty(dateF);
		this.dateD = new SimpleStringProperty(dateD);
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.v = new SimpleStringProperty(v);
		this.id = new SimpleIntegerProperty(id);
		this.somme = new SimpleIntegerProperty(somme);
	}

}
