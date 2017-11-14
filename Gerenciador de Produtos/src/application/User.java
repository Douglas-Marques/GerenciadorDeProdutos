package application;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class User {
	protected String name, login, password;
	
	public User(String name, String login, String password) {
		this.name = name;
		this.login = login;
		this.password = password;
		try {
			FileOutputStream out = new FileOutputStream(new File("Usuario.txt"), true);
			BufferedOutputStream buff = new BufferedOutputStream(out);
			PrintStream ps = new PrintStream(buff);
			long att = System.currentTimeMillis();
			ps.println("Usuário criado: " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date(att)));
			ps.println(name + " * " + login + " * " + password);
			ps.flush();
			ps.close();
		} catch (IOException e) {
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", login=" + login + ", password=" + password + "]";
	}
}