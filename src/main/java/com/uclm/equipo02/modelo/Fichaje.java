package com.uclm.equipo02.modelo;

import com.uclm.equipo02.persistencia.DAOFichaje;
public class Fichaje {

	private String fechaFichaje, horaEntrada, horaSalida, nombreEmpleado;

	private boolean estado; //true=abierto false=cerrado

	DAOFichaje daofichaje=new DAOFichaje();


	////Fichaj con una sola hora de fichaje  y el metodo cerraFIchaje --> horaEntrada=horaSalida
	///AnADIR HORA SALIDA Y ACTUALIZAR 
	//Document updateQuery=new Document("usuario.getNombre()","usuario.getNombre()");
	//fichajes.updateOne(updateQuery,new Document("set",new Document("usuario.getNombre")))

	public Fichaje(String nombreEmpleado, String fechaFichaje, String horaEntrada,String horaSalida,boolean estado ) {
		this.nombreEmpleado=nombreEmpleado;
		this.fechaFichaje = fechaFichaje;
		this.horaEntrada = horaEntrada;
		this.horaSalida=horaSalida;
		this.estado = estado;	//Tru--> Fichaje Abierto False--> Fichaje Cerrado
	}



	

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
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