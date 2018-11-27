package com.uclm.equipo02.modelo;

import com.uclm.equipo02.persistencia.DAOIncidencia;

public class Incidencia {
	private String nombreUsuario;
	private String dniUsuario;
	private String categoria;
	private String fechaCreacion;
	private String descripcion;
	private String estado;
	private String comentarioGestor;
	private DAOIncidencia dao = new DAOIncidencia();

	public Incidencia(String nombreUsuario, String dniUsuario, String categoria, String descripcion, String estado, 
			String fechaCreacion, String comentarioGestor) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.dniUsuario = dniUsuario;
		this.categoria = categoria;
		this.fechaCreacion = fechaCreacion;
		this.descripcion = descripcion;
		this.estado = estado;
		this.comentarioGestor = comentarioGestor;
	}

	public String getDniUsuario() {
		return dniUsuario;
	}

	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}

	public String getComentarioGestor() {
		return comentarioGestor;
	}

	public void setComentarioGestor(String comentarioGestor) {
		this.comentarioGestor = comentarioGestor;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public DAOIncidencia getDao() {
		return dao;
	}

	public void setDao(DAOIncidencia dao) {
		this.dao = dao;
	}

}
