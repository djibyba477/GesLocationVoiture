package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Voiture {
	private SimpleStringProperty nomCompet;
	private SimpleIntegerProperty id;

	public String getNomCompet() {
		return nomCompet.get();
	}

	public int getId() {
		return id.get();
	}

	public Voiture(int id, String nomCompet) {
		this.nomCompet = new SimpleStringProperty(nomCompet);
		this.id = new SimpleIntegerProperty(id);
	}

	public Voiture(String nomCompet) {
		this.nomCompet = new SimpleStringProperty(nomCompet);
	}

}
