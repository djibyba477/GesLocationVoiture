package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Reglement;

public class ReglementImpl implements iReglement{
	public DB db=new DB();
	public Statement statement;
	public ResultSet resultSet;
	@Override
	public List<Reglement> list() {
		List<Reglement> list=new ArrayList<Reglement>();
		try {
			statement=db.getConnection().createStatement();
			resultSet=statement.executeQuery("select * from loc");
			while(resultSet.next()) {
				list.add(new Reglement(resultSet.getInt("id"),resultSet.getString("nom"),resultSet.getString("prenom"),resultSet.getString("nomComplet"),resultSet.getString("dateD"),resultSet.getString("dateF"),resultSet.getInt("prix"),resultSet.getInt("total"),resultSet.getString("statut"),resultSet.getInt("restant")));
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
			statement=db.getConnection().createStatement();
			if(!statement.execute("insert into reglement (montant,idL) value('"+reglement.getMontant()+"','"+reglement.getId()+"')"))return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
