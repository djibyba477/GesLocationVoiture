package models;

public class Diagram {
	private final String nom;
	private final double somme;

	public String getNom() {
		return nom;
	}

	public double getSomme() {
		return somme;
	}

	public Diagram(String nom, double somme) {
		this.nom = nom;
		this.somme = somme;
	}

}
