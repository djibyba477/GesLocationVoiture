package dao;

import java.util.List;

import models.Authentification;

public interface iAuthentification {
	public int connexion(Authentification authentification);
	public boolean ajouter(Authentification authentification);
	public boolean modifier(Authentification authentification);
	public boolean supprimer(int id);
	public List<Authentification> list();
}
