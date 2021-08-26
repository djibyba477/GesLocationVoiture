package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Reglement;

public class ReglementImpl implements iReglement {
	public DB db = new DB();
	public Statement statement;
	public ResultSet resultSet;

	@Override
	public List<Reglement> list() {
		List<Reglement> list = new ArrayList<Reglement>();
		try {
			statement = db.getConnection().createStatement();
			resultSet = statement.executeQuery(
					"SELECT location.idL,client.nom,client.prenom,voiture.nomComplet,location.prix,location.dateD,location.dateF FROM `location`,client,voiture WHERE location.idC=client.idC\r\n"
							+ " and location.idV=voiture.idV");
			while (resultSet.next()) {
				list.add(new Reglement(resultSet.getInt("idL"), resultSet.getString("nom"),
						resultSet.getString("prenom"), resultSet.getString("nomComplet"), resultSet.getString("dateD"),
						resultSet.getString("dateF"), resultSet.getInt("prix")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean ajouter(Reglement reglement) {
		try {
			statement = db.getConnection().createStatement();
			if (!statement.execute("insert into reglement (montant,idL) value('" + reglement.getMontant() + "','"
					+ reglement.getId() + "')"))
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int verification(int id) {
		try {
			statement = db.getConnection().createStatement();
			resultSet = statement.executeQuery(
					"SELECT sum(reglement.montant) as montant FROM location,reglement WHERE reglement.idL=location.idL and location.idL='"
							+ id + "'");
			if (resultSet.next()) {
				if (resultSet.getInt(1) == 0)
					return -1;
				else
					return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
