package com.uclm.equipo02.modelo;

import com.uclm.equipo02.persistencia.DAOFichaje;
public class Fichaje {

	private String fechaFichaje, horaEntrada, horaSalida, nombreEmpleado;
	private boolean estado; //true=abierto false=cerrado
	
	DAOFichaje daofichaje=new DAOFichaje();
	
	
	public Fichaje(String nombreEmpleado, String fechaFichaje, String horaEntrada,boolean estado ) {
		this.nombreEmpleado=nombreEmpleado;
		this.fechaFichaje = fechaFichaje;
		this.horaEntrada = horaEntrada;
		this.estado = true;
		daofichaje.abrirFichaje(this);		
	}
	
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
}
	
	public void cerrarFichaje(String horaSalida, Usuario usuario) {
		this.horaSalida = horaSalida;
		daofichaje.cerrarFichaje(usuario, this);
	}
	
	
	public String getFechaFichaje() {
		return fechaFichaje;
	}
	public void setFechaFichaje(String fechaFichaje) {
		this.fechaFichaje = fechaFichaje;
	}
	public String getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraFichaje(String horaEntrada) {
		this.horaEntrada = horaEntrada;
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