package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Reglement {
	private SimpleIntegerProperty id,montant,prix,restant;
	private SimpleStringProperty nom,prenom,voiture,statut,debut,fin;

	public int getMontant() {
		return montant.get();
	}
	public int getId() {
		return id.get();
	}
	public Reglement(int id, int montant,
			int location) {
		this.montant = new SimpleIntegerProperty(montant);
		this.id =  new SimpleIntegerProperty(location);
	}
	public Reglement(int location, int montant) {
		this.montant = new SimpleIntegerProperty(montant);
		this.id =  new SimpleIntegerProperty(location);
	}
	public int getPrix() {
		return prix.get();
	}
	public Reglement(int id, String nom, String prenom,
			String voiture,String debut,String fin,int prix,int montant,String statut,int restant ) {
		this.id = new SimpleIntegerProperty(id);
		this.montant = new SimpleIntegerProperty(montant);
		this.prix = new SimpleIntegerProperty(prix);
		this.restant = new SimpleIntegerProperty(restant);
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.voiture = new SimpleStringProperty(voiture);
		this.statut = new SimpleStringProperty(statut);
		this.debut = new SimpleStringProperty(debut);
		this.fin = new SimpleStringProperty(fin);
	}
	public int getRestant() {
		return restant.get();
	}
	public String getNom() {
		return nom.get();
	}
	public String getPrenom() {
		return prenom.get();
	}
	public String getVoiture() {
		return voiture.get();
	}
	public String getStatut() {
		return statut.get();
	}
	public String getDebut() {
		return debut.get();
	}
	public String getFin() {
		return fin.get();
	}

	
}
