package com.uclm.equipo02.modelo;

import com.uclm.equipo02.persistencia.DAOFichaje;
public class Fichaje {

	private String fechaFichaje, horaFichaje, horaSalida, nombreEmpleado;
	private boolean estado; //true=abierto false=cerrado
	
	DAOFichaje daofichaje=new DAOFichaje();
	
	
	////Fichaj con una sola hora de fichaje  y el metodo cerraFIchaje --> horaFichaje=horaSalida
	
	public Fichaje(String nombreEmpleado, String fechaFichaje, String horaFichaje,boolean estado ) {
		this.nombreEmpleado=nombreEmpleado;
		this.fechaFichaje = fechaFichaje;
		this.horaFichaje = horaFichaje;
		this.estado = true;	
	}
	
	
	
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
}
	
	public void cerrarFichaje(String horaSalida, Usuario usuario) {
		this.horaFichaje = horaSalida;
		daofichaje.cerrarFichaje(usuario, this);
	}
	
	
	public String getFechaFichaje() {
		return fechaFichaje;
	}
	public void setFechaFichaje(String fechaFichaje) {
		this.fechaFichaje = fechaFichaje;
	}
	public String getHoraEntrada() {
		return horaFichaje;
	}
	public void setHoraFichaje(String horaEntrada) {
		this.horaFichaje = horaEntrada;
	}
	public String getHoraSalida() {
		return horaSalida;
	}
	public void setHoraCierre(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	
}