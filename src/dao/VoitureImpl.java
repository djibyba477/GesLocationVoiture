package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Diagram;
import models.Voiture;

public class VoitureImpl implements iVoiture {
	public DB db = new DB();
	public Statement statement;
	public ResultSet resultset;

	@Override
	public List<Voiture> liste() {
		List<Voiture> list = new ArrayList<Voiture>();
		try {
			statement = db.getConnection().createStatement();
			resultset = statement.executeQuery("SELECT * from voiture");
			while (resultset.next()) {
				list.add(new Voiture(resultset.getInt(1), resultset.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean ajouter(Voiture voiture) {
		try {
			statement = db.getConnection().createStatement();
			if (!statement.execute("insert into voiture (nomComplet) value('" + voiture.getNomCompet() + "')")) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifier(Voiture voiture) {
		try {
			statement = db.getConnection().createStatement();
			if (!statement.execute("update voiture set nomComplet='" + voiture.getNomCompet() + "' where idV='"
					+ voiture.getId() + "'")) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean supprimer(Voiture voiture) {
		try {
			statement = db.getConnection().createStatement();
			if (!statement.execute("delete from voiture where idV='" + voiture.getId() + "'")) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Diagram> listD() {
		List<Diagram> list = new ArrayList<Diagram>();
		try {
			statement = db.getConnection().createStatement();
			resultset = statement.executeQuery(
					"select voiture.nomComplet,SUM(reglement.montant) FROM reglement,location,voiture WHERE reglement.idL=location.idL AND location.idV=voiture.idV GROUP BY reglement.idL");
			while (resultset.next()) {
				list.add(new Diagram(resultset.getString(1), resultset.getInt(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
