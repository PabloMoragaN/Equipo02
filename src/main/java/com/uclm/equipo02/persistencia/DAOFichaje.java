package com.uclm.equipo02.persistencia;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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


	/**
	 * 
	 * 
	 * @method metodo usado para obtener la hora exacta en Espana
	 * 
	 **/

	public static String getCurrentTimeUsingCalendar() {
		Calendar cal = Calendar.getInstance();
		Date date=cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		String formattedTime=dateFormat.format(date);
		return formattedTime;

	}


	public void abrirFichaje(Fichaje fichaje) {
		Document documento = new Document();

		documento.append("nombreEmpleado", fichaje.getNombreEmpleado());
		documento.append("fechaFichaje", fichaje.getFechaFichaje());
		documento.append("horaEntrada", fichaje.getHoraEntrada());
		documento.append("horaSalida", null);
		documento.append("estado", fichaje.getEstado());

		MongoCollection<Document> fichajes = getFichajes();
		fichajes.insertOne(documento);//se podria cambiar a broker.insertDoc(fichajes, documento) para concordar con cerrarFichaje
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

	/**
	 * 
	 * @method Metodo de cierre de Fichajes, el metodo utiliza el criterio de nombredeEmpleado (el de la current sesion), 
	 * y la fecha del fichaje (asumiendo que no se necesitan fichajes entre dias) y cambia el estado y la horaSalida accediendo al mongoBroker
	 * que updatea el documento
	 */
	public void cerrarFichaje(Usuario usuario, Fichaje fichaje) {
		MongoCollection<Document> fichajes = getFichajes();
		MongoBroker broker = MongoBroker.get();

		Document criteria=new Document();

		criteria.put("nombreEmpleado", usuario.getNombre());
		criteria.put("fechaFichaje", fichaje.getFechaFichaje());

		Document changes=new Document();

		changes.put("estado", fichaje.getEstado());
		changes.put("horaSalida", fichaje.getHoraSalida());
		Document doc = new Document();
		doc.put("$set", changes);

		broker.updateDoc(fichajes, criteria, doc);


	}


}