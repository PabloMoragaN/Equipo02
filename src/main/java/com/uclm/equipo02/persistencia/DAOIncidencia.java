package com.uclm.equipo02.persistencia;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.bson.Document;

import com.uclm.equipo02.modelo.Fichaje;
import com.uclm.equipo02.modelo.Incidencia;
import com.uclm.equipo02.modelo.Usuario;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class DAOIncidencia{



	public static MongoCollection<Document> getIncidencias() {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> incidencias = broker.getCollection("Incidencias");
		return incidencias;
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

	public void insert(Incidencia incidencia) {
		Document documento = new Document();

		documento.append("nombreUsuario", incidencia.getNombreUsuario());
		documento.append("dniUsuario", incidencia.getDniUsuario());
		documento.append("categoria", incidencia.getCategoria());
		documento.append("fechaCreacion", incidencia.getFechaCreacion());
		documento.append("descripcion", incidencia.getDescripcion());
		documento.append("estado", incidencia.getEstado());
		documento.append("comentarioGestor", incidencia.getComentarioGestor());

		MongoCollection<Document> incidencias = getIncidencias();
		incidencias.insertOne(documento);
	}
	
	public static Date parserFecha(String fecha) {
		Date fechaparseada=new Date();
		
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			fechaparseada=format.parse(fecha);
			return fechaparseada;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return fechaparseada;
		
	}
	
	public static List<Document> getIncidenciasGestor() {


		List<Document> incidenciasGestor = new ArrayList<Document>();
		Document documento = new Document();
		MongoCursor<Document> elementos = getIncidencias().find().iterator();

		while(elementos.hasNext()) {
			documento = elementos.next();
			if(documento.get("estado").toString().equalsIgnoreCase("Pendiente"))
				
				incidenciasGestor.add(documento);
		}
		
		return incidenciasGestor;
	}
	
	public boolean existeIncidenciasEspera() {
		boolean bool=false;
		Document documento = new Document();
		MongoCursor<Document> elementos = getIncidencias().find().iterator();
		while(elementos.hasNext()) {
			documento = elementos.next();
				
					if(documento.get("estado").toString().equalsIgnoreCase("Pendiente")) {
						bool=true;
	
				}
				
		}
		return bool;
		
	}

}
