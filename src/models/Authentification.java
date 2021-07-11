package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Authentification {
	private SimpleStringProperty login,password,role;
		private SimpleIntegerProperty id;
	public Authentification(String login, String password, String role) {
		this.login = new SimpleStringProperty(login);
		this.password = new SimpleStringProperty(password);
		this.role = new SimpleStringProperty(role);
	}
	public Authentification(int id,String login, String password, String role) {
		this.id = new SimpleIntegerProperty(id);
		this.login = new SimpleStringProperty(login);
		this.password = new SimpleStringProperty(password);
		this.role = new SimpleStringProperty(role);
	}
	public String getLogin() {
		return login.get();
	}
	public String getPassword() {
		return password.get();
	}
	public String getRole() {
		return role.get();
	}
	public int getId() {
		return id.get();
	}
	
	
}
