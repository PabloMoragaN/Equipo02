package com.uclm.equipo02.modelo;

import com.uclm.equipo02.persistencia.DAOFichaje;
public class Fichaje {

	private String fechaFichaje, horaFichaje, horaSalida, nombreEmpleado;
	private boolean estado; //true=abierto false=cerrado
	
	DAOFichaje daofichaje=new DAOFichaje();
	
	
	////Fichaj con una sola hora de fichaje  y el metodo cerraFIchaje --> horaFichaje=horaSalida
	///AnADIR HORA SALIDA Y ACTUALIZAR 
	//Document updateQuery=new Document("usuario.getNombre()","usuario.getNombre()");
	//fichajes.updateOne(updateQuery,new Document("set",new Document("usuario.getNombre")))
	
	public Fichaje(String nombreEmpleado, String fechaFichaje, String horaFichaje,String horaSalida,boolean estado ) {
		this.nombreEmpleado=nombreEmpleado;
		this.fechaFichaje = fechaFichaje;
		this.horaFichaje = horaFichaje;
		this.horaSalida=horaSalida;
		this.estado = estado;	//Tru--> Fichaje Abierto False--> Fichaje Cerrado
	}
	
	
	
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
}

	public boolean fichajeCierre(String nombreEmpleado, String fechaFichaje, boolean estado) {
		return daofichaje.validezCerrado(nombreEmpleado, fechaFichaje, estado);
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