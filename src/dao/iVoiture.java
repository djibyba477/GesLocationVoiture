package dao;

import java.util.List;

import models.Diagram;
import models.Voiture;

public interface iVoiture {
	public List<Voiture> liste();

	public boolean ajouter(Voiture voiture);

	public boolean modifier(Voiture voiture);

	public List<Diagram> listD();
}
