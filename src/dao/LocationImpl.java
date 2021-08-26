package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Location;

public class LocationImpl implements iLocation {
	public DB db = new DB();
	public Statement statement;
	public ResultSet resultset;

	@Override
	public List<Location> list() {
		List<Location> list = new ArrayList<Location>();
		try {
			statement = db.getConnection().createStatement();
			resultset = statement.executeQuery(
					"SELECT idL,nom,prenom,nomComplet,dateD,dateF,prix FROM location,voiture,client WHERE location.idV=voiture.idV and location.idC=client.idC");
			while (resultset.next()) {
				list.add(new Location(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5), resultset.getString(6), resultset.getInt(7)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean ajouter(Location location) {
		try {
			statement = db.getConnection().createStatement();
			if (!statement.execute("insert into location(idC,idV,dateD,dateF,prix) values('" + location.getIdCl()
					+ "','" + location.getVoiture() + "','" + location.getDateD() + "','" + location.getDateF() + "','"
					+ location.getSomme() + "')")) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
