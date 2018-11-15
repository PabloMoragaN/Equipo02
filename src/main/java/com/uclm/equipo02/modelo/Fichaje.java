package com.uclm.equipo02.modelo;

import com.uclm.equipo02.persistencia.DAOFichaje;
public class Fichaje {

	private String fechaFichaje, horaEntrada, horaSalida, emailEmpleado;

	private boolean estado; //true=abierto false=cerrado

	DAOFichaje daofichaje=new DAOFichaje();


	////Fichaj con una sola hora de fichaje  y el metodo cerraFIchaje --> horaEntrada=horaSalida
	///AnADIR HORA SALIDA Y ACTUALIZAR 


	public Fichaje(String emailEmpleado, String fechaFichaje, String horaEntrada,String horaSalida,boolean estado ) {
		this.emailEmpleado=emailEmpleado;
		this.fechaFichaje = fechaFichaje;
		this.horaEntrada = horaEntrada;
		this.horaSalida=horaSalida;
		this.estado = estado;	//Tru--> Fichaje Abierto False--> Fichaje Cerrado
	}



	

	public String getEmailFichaje() {
		return emailEmpleado;
	}

	public void setEmailFichaje(String emailEmpleado) {
		this.emailEmpleado = emailEmpleado;
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
	public void sethoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public String getHoraSalida() {
		return horaSalida;
	}
	public void sethoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}



}