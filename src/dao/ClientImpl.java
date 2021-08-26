package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Client;

public class ClientImpl implements iClient {
	public DB db = new DB();
	public Statement statement;
	public ResultSet resultset;

	@Override
	public List<Client> list() {
		List<Client> list = new ArrayList<Client>();
		try {
			statement = db.getConnection().createStatement();
			resultset = statement.executeQuery("SELECT * FROM client");
			while (resultset.next()) {
				list.add(new Client(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4), resultset.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean ajouter(Client client) {
		try {
			statement = db.getConnection().createStatement();
			if (!statement.execute("insert into client(nom,prenom,piece,permi) value('" + client.getNom() + "','"
					+ client.getPrenom() + "','" + client.getPiece() + "','" + client.getPermi() + "')")) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean modifier(Client client) {
		try {
			statement = db.getConnection().createStatement();
			if (!statement.execute("update client set nom='" + client.getNom() + "', prenom='" + client.getPrenom()
					+ "',piece='" + client.getPiece() + "',permi='" + client.getPermi() + "' where idC='"
					+ client.getId() + "'")) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
