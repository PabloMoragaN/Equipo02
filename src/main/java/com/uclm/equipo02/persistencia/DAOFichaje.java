package com.uclm.equipo02.persistencia;


import org.bson.BsonDocument;
import org.bson.Document;

import com.uclm.equipo02.modelo.Fichaje;
import com.uclm.equipo02.modelo.Usuario;

import com.mongodb.client.MongoCollection;

public class DAOFichaje {
	

	
	public MongoCollection<Document> getFichajes() {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> fichajes = broker.getCollection("Fichajes");
		return fichajes;
}
	public void abrirFichaje(Fichaje fichaje) {
		Document documento = new Document();
		
		documento.append("nombreEmpleado", fichaje.getNombreEmpleado());
		documento.append("fechaFichaje", fichaje.getFechaFichaje());
		documento.append("horaEntrada", fichaje.getHoraEntrada());
		documento.append("horaSalida", null);
		documento.append("estado", fichaje.getEstado());
		
		MongoCollection<Document> fichajes = getFichajes();
		fichajes.insertOne(documento);
	}

	public void cerrarFichaje(Usuario usuario, Fichaje fichaje) {
		MongoCollection<Document> fichajes = getFichajes();
		MongoBroker broker = MongoBroker.get();
		
		Document documento = new Document();
		Document filtro = new Document();
		Document cambio = new Document();
		filtro.put("nombre", usuario.getNombre());
		filtro.put("estado", true);
		cambio.put("horaSalida", fichaje.getHoraSalida());
		cambio.put("estado", false);
		documento.put("$set", cambio);
		broker.updateDoc(fichajes, filtro, documento);
		fichaje.setEstado(false);
	}

}