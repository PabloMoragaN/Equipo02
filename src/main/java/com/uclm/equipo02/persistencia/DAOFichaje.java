package com.uclm.equipo02.persistencia;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
		
		documento.append("id",fichaje.getidFichaje());
		documento.append("nombreEmpleado", fichaje.getNombreEmpleado());
		documento.append("fechaFichaje", fichaje.getFechaFichaje());
		documento.append("horaEntrada", fichaje.getHoraEntrada());
		documento.append("horaSalida", null);
		documento.append("estado", fichaje.getEstado());

		MongoCollection<Document> fichajes = getFichajes();
		fichajes.insertOne(documento);//se podria cambiar a broker.insertDoc(fichajes, documento) para concordar con cerrarFichaje
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
		
		criteria.put("id", fichaje.getidFichaje());
		criteria.put("nombreEmpleado", usuario.getNombre());
		criteria.put("fechaFichaje", fichaje.getFechaFichaje());



		Document changes=new Document();

		changes.put("estado", fichaje.getEstado());
		changes.put("horaSalida", fichaje.getHoraSalida());
		Document doc = new Document();
		doc.put("$set", changes);

		broker.updateDoc(fichajes, criteria, doc);


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

		}
		return horaentrada;

	}
	
	public int getLastID() {
		int counter = 0;
		Document documento = new Document();
		MongoCursor<Document> elementos = getFichajes().find().iterator();
		while(elementos.hasNext()) {
			counter++;
		}
		
		return counter;
		
	}


	/**
	 * 
	 * @method Comprueba que si hay un fichaje abierto, no puedas abrir otro fichaje antes de cerrar el anterior
	 * 
	 */
	public boolean validezAbierto(Fichaje fichaje) {
		Document documento = new Document();
		MongoCursor<Document> elementos = getFichajes().find().iterator();
		while(elementos.hasNext()) {
			documento = elementos.next();
			if(documento.get("nombreEmpleado").toString().equalsIgnoreCase(fichaje.getNombreEmpleado()))//usuario sesion
				if(documento.get("fechaFichaje").toString().equals(fichaje.getFechaFichaje()))
					if(documento.get("estado").toString().equals(Boolean.toString(true)))
						return false;

		}
		return true;
	}


	/**
	 * 
	 * @method Comprueba si no hay algun fichaje abierto que se puede cerrar. Si no hay ninguno abierto, tienes que crear uno nuevo.
	 * Tambien comprueba que se actualiza el ultimo fichaje creado
	 */

	public boolean validezCerrado(Fichaje fichaje) {
		Document documento = new Document();
		MongoCursor<Document> elementos = getFichajes().find().iterator();
		while(elementos.hasNext()) {
			documento = elementos.next();
			if(documento.get("id").toString().equalsIgnoreCase(String.valueOf(fichaje.getidFichaje())))
				if(documento.get("nombreEmpleado").toString().equalsIgnoreCase(fichaje.getNombreEmpleado()))//usuario sesion
					if(documento.get("fechaFichaje").toString().equals(fichaje.getFechaFichaje()))
						//if(documento.get("horaEntrada").toString().equalsIgnoreCase(fichaje.getHoraEntrada()))
						if(documento.get("estado").toString().equals(Boolean.toString(true))) 
							return true;

		}

		return false;
	}

	public boolean creacionVarios(Fichaje fichaje) {
		Document documento = new Document();
		MongoCursor<Document> elementos = getFichajes().find().iterator();
		while(elementos.hasNext()) {
			documento = elementos.next();
			if(documento.get("nombreEmpleado").toString().equalsIgnoreCase(fichaje.getNombreEmpleado()))
				if(documento.get("fechaFichaje").toString().equals(fichaje.getFechaFichaje()))
					if(documento.get("estado").toString().equals(Boolean.toString(fichaje.getEstado())))
						return true;

		}
		return false;
	}




	public List<Document> fichajesEmpleado(String nombreEmpleado){
		List<Document> fichajesempleado = new ArrayList<Document>();
		Document documento = new Document();
		MongoCursor<Document> elementos = getFichajes().find().iterator();
		while(elementos.hasNext()) {
			documento = elementos.next();
			if(documento.get("nombreEmpleado").toString().equalsIgnoreCase(nombreEmpleado))
				fichajesempleado.add(documento);
		}

		return fichajesempleado;
	}


}