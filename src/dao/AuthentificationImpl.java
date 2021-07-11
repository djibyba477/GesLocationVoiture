package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import models.Authentification;

public class AuthentificationImpl implements iAuthentification{
private DB db=new DB();
ResultSet resultatSet;
	@Override
	public int connexion(Authentification authentification) {
		try {
			Statement statement=db.getConnection().createStatement();
			resultatSet=statement.executeQuery("select idU from utilisateur where login='"+authentification.getLogin()+"' and password='"+authentification.getPassword()+"' and role='"+authentification.getRole()+"'");
			if(resultatSet.next()) {
				return resultatSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}
	@Override
	public boolean ajouter(Authentification authentification) {
		try {
			Statement statement=db.getConnection().createStatement();	
			if(!statement.execute("INSERT INTO utilisateur(login,password,role) values('"+authentification.getLogin()+"','"+authentification.getPassword()+"','"+authentification.getRole()+"')")) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean modifier(Authentification authentification) {
		try {
			Statement statement=db.getConnection().createStatement();	
			if(!statement.execute("update utilisateur set login='"+authentification.getLogin()+"', password='"+authentification.getPassword()+"', role='"+authentification.getRole()+"' where idU='"+authentification.getId()+"'")) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean supprimer(int id) {
		try {
			Statement statement=db.getConnection().createStatement();	
			if(!statement.execute("delete from utilisateur where idU='"+id+"'")) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<Authentification> list() {
		List<Authentification> list=new ArrayList<>();
		try {
			Statement statement =db.getConnection().createStatement();
			resultatSet = statement.executeQuery("select * from utilisateur");
			while(resultatSet.next()) {
				list.add(new Authentification(resultatSet.getInt(1), resultatSet.getString(2), resultatSet.getString(3),resultatSet.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
