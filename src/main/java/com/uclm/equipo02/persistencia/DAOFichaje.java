package com.uclm.equipo02.persistencia;


import org.bson.BsonDocument;
import org.bson.Document;

import com.uclm.equipo02.modelo.Fichaje;
import com.uclm.equipo02.modelo.Usuario;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

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


	public String getHoraEntrada(String nombreEmpleado, String fechaFichaje) {
		String horaentrada="";
		Document documento = new Document();
		MongoCursor<Document> elementos = getFichajes().find().iterator();
		while(elementos.hasNext()) {
			documento = elementos.next();
			if(documento.get("nombreEmpleado").toString().equalsIgnoreCase(nombreEmpleado))
				if(documento.get("fechaFichaje").toString().equals(fechaFichaje))
					horaentrada=documento.getString("horaEntrada");
					System.out.println("Hora Entrada"+horaentrada);

		}
		return horaentrada;

	}

	/***
	 * 
	 * @method comprueba que el fichaje este abierto y sea en la misma fecha 
	 *
	 */
	public boolean validezCerrado(String nombreEmpleado, String fecha, boolean estado) {
		Document documento = new Document();
		MongoCursor<Document> elementos = getFichajes().find().iterator();
		while(elementos.hasNext()) {
			documento = elementos.next();
			if(documento.get("nombreEmpleado").toString().equalsIgnoreCase(nombreEmpleado))
				if(documento.get("fechaFichaje").toString().equals(fecha))
					if(documento.get("estado").toString().equals(Boolean.toString(estado)))
						return true;

		}
		return false;
	}

	public void cerrarFichaje(Usuario usuario, Fichaje fichaje) {
		MongoCollection<Document> fichajes = getFichajes();
		MongoBroker broker = MongoBroker.get();
		
		/*Document updateQuery=new Document();
		updateQuery.append("nombreEmpleado","usuario.getNombre()");
		updateQuery.append("fechaFichaje",fichaje.getFechaFichaje());
		fichajes.updateOne(updateQuery,new Document("set",new Document("horaSalida",fichaje.getHoraSalida())));
		fichajes.updateOne(updateQuery,new Document("set",new Document("estado",fichaje.getEstado())));*/
		
		
		Document criteria=new Document();
		
		criteria.put("nombreEmpleado", usuario.getNombre());
		criteria.put("fechaFichaje", fichaje.getFechaFichaje());
		
		Document changes=new Document();
		
		changes.put("horaSalida", fichaje.getHoraSalida());
		changes.put("estado", fichaje.getEstado());
		
		Document doc = new Document();
		doc.put("$set", changes);
		
		broker.updateDoc(fichajes, criteria, doc);
		
		
	}


}