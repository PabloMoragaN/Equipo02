package com.uclm.equipo02.persistencia;


import org.bson.BsonDocument;
import org.bson.Document;

import com.uclm.equipo02.modelo.Fichaje;
import com.uclm.equipo02.modelo.Usuario;

import com.mongodb.client.MongoCollection;

public class DAOFichaje {
	MongoCollection<Document> fichajes;
	MongoBroker broker;
	

	public DAOFichaje() {
		broker = MongoBroker.get();
		fichajes = MongoBroker.getCollection("Fichajes");
	}



	public void abrirFichaje(Fichaje fichaje) {
		Document documento = new Document();
		broker.insertDoc(fichajes, documento
				.append("nombreEmpleado", fichaje.getNombreEmpleado())
				.append("fechaFichaje", fichaje.getFechaFichaje())
				.append("horaEntrada", fichaje.getHoraEntrada())
				.append("horaSalida", null)
				.append("estado", fichaje.getEstado()));
	}

	public void cerrarFichaje(Usuario usuario, Fichaje fichaje) {
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