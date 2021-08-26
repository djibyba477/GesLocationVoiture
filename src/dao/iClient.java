package dao;

import java.util.List;

import models.Client;

public interface iClient {
	public List<Client> list();

	public boolean ajouter(Client client);

	public boolean modifier(Client client);
}
