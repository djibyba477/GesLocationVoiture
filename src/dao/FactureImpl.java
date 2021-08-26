package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import models.Facture;

public class FactureImpl implements Ifacture {
	private DB db = new DB();

	@Override
	public Facture facture(int piece) {
		Facture facture;
		try {
			Statement statement = db.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT client.prenom,client.nom,voiture.nomComplet,location.prix,location.prix-SUM(reglement.montant) as restant FROM client,voiture,reglement,location WHERE location.idC=client.idC and location.idV=voiture.idV and reglement.idL=location.idL AND client.piece='"
							+ piece + "'");
			if (resultSet.next()) {
				return facture = new Facture(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4), resultSet.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
