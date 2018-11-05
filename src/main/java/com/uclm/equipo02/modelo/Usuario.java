package com.uclm.equipo02.modelo;

import java.util.List;

import org.bson.Document;

import com.uclm.equipo02.persistencia.DAOFichaje;

public class Usuario {
	private String nombre;
	private String password;
	private String email;
	private String rol;
	private DAOFichaje dao = new DAOFichaje();

	public Usuario(String nombre, String password, String email, String rol) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.email = email;
		this.rol = rol;
	}
	
	public List<Document> getFichajesEmpleado(String nombreEmpleado){
		return dao.fichajesEmpleado(nombreEmpleado);
}
	
	public Usuario(String nombre) {
		this.nombre=nombre;
	}
	public Usuario() {

	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
}