package dao;

import java.util.List;

import models.Location;

public interface iLocation {
	public List<Location> list();

	public boolean ajouter(Location location);
}
