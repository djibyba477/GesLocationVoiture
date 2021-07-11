package dao;

import java.util.List;

import models.Reglement;

public interface iReglement {
	public List<Reglement> list();
	public boolean ajouter(Reglement reglement);
}
