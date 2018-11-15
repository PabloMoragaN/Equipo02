package com.uclm.equipo02.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.BsonString;
import org.bson.BsonValue;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.uclm.equipo02.modelo.Usuario;

public class UsuarioDaoImplement{

	private final String name = "nombre";
	private final String password = "pwd";
	private final String email = "email";
	private final String rol = "rol";

	public UsuarioDaoImplement() {
		super();
	}
	//Inserta un nuevo usuario en la BBDD
	public void insert(Usuario usuario) throws Exception {
		if(!selectNombre(usuario)) {
			Document bso = new Document();
			bso.append(name, new BsonString(usuario.getNombre()));
			bso.append(password, new BsonString(usuario.getPassword()));
			bso.append(email, new BsonString(usuario.getEmail()));
			bso.append(rol, new BsonString(usuario.getRol()));
			MongoCollection<Document> usuarios = obtenerUsuarios();
			usuarios.insertOne(bso);
		}else
			throw new Exception("Cuenta existente");
	}
	//Devuelve un true si existe y false si no existe
	private boolean selectNombre(Usuario usuario) {
		MongoCollection<Document> usuarios = obtenerUsuarios();
		Document criterio = new Document();
		criterio.append(name, new BsonString(usuario.getNombre()));
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document usuarioBson = resultado.first();
		if (usuarioBson == null) {
			return false;
		}
		return true;
	}

	public String devolverRol(Usuario usuario) {
		MongoCollection<Document> usuarios = obtenerUsuarios();
		Document criterio = new Document();
		criterio.append(email, new BsonString(usuario.getEmail()));
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document usuariobso = resultado.first();
		if (usuario==null){
			return null;
		}else {
			/*
			BsonValue nombre=usuariobso.get(rol);
			BsonString rolbso=nombre.asString();
			String rolFinal=rolbso.getValue();
			*/
			String rolUser = usuariobso.getString(rol);
			usuario.setRol(rolUser);
			
		}
		return usuario.getRol();
	}
	public String devolverUser(Usuario usuario) {
		MongoCollection<Document> usuarios = obtenerUsuarios();
		Document criterio = new Document();
		criterio.append(email, new BsonString(usuario.getEmail()));
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document usuariobso = resultado.first();
		if (usuario==null || usuariobso ==null){
			return null;
		}else {
			/*
			BsonValue nombre=usuariobso.get(name);
			BsonString namebso=nombre.asString();
			String nombreFinal=namebso.getValue();
			*/
			String nombreFinal= usuariobso.getString(name);
			usuario.setNombre(nombreFinal);
		}
		return usuario.getNombre();
	}

	//Obtener todos los usuarios
	private MongoCollection<Document> obtenerUsuarios() {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> usuarios = broker.getCollection("Usuarios");
		return usuarios;
	}


	//Devuelve los usuarios que no son administradores
	public List<Usuario> list() {
		MongoCollection<Document> usuarios = obtenerUsuarios();
		FindIterable<Document> resultado=usuarios.find();
		String nombre;
		Document usuario;
		Iterator<Document> lista=resultado.iterator();
		List<Usuario> retorno=new ArrayList<Usuario>();
		while(lista.hasNext()) {
			usuario=lista.next();
			nombre=usuario.getString(name);
			//if(administradorDao.selectNombre(nombre)==null)retorno.add(new Usuario(nombre));
		}
		return retorno;
	}

	//Borrar usuario
	public void delete (Usuario usuario){
		List<Usuario> todos=selectAll();
		Document bso = new Document();
		bso.append(name, new BsonString(usuario.getNombre()));
		MongoCollection<Document> usuarios = obtenerUsuarios();
		usuarios.deleteOne(bso);
	}
	//Devuelve una lista de todos los usuarios
	public List<Usuario> selectAll() {
		MongoCollection<Document> usuarios = obtenerUsuarios();
		FindIterable<Document> resultado=usuarios.find();
		String nombre;
		Document usuario;
		Iterator<Document> lista=resultado.iterator();
		List<Usuario> retorno=new ArrayList<Usuario>();
		while(lista.hasNext()) {
			usuario=lista.next();
			nombre=usuario.getString(name);
			retorno.add(new Usuario(nombre));
		}
		return retorno;
	}
	//Actualizar nombre de usuario
	public void updateNombre(String antiguo, String nuevo) {
		List <Usuario> todos=selectAll();
		BsonString nombreAnt=new BsonString(antiguo);
		BsonString nombreNue=new BsonString(nuevo);
		Iterator <Usuario> it=todos.iterator();
		Usuario aux;
		MongoCollection<Document> usuarios = obtenerUsuarios();
		Document criterio = new Document();
		FindIterable<Document> resultado;
		Document usuario;
		Document actualizacion = null;

		while(it.hasNext()) {
			aux=it.next();
			criterio.append(name, new BsonString(aux.getNombre()));
			resultado=usuarios.find(criterio);
			usuario = resultado.first();
			usuarios.findOneAndUpdate(usuario, actualizacion);
		}

		usuarios = obtenerUsuarios();
		criterio = new Document();
		criterio.append(name, nombreAnt);
		resultado=usuarios.find(criterio);
		usuario = resultado.first();
		actualizacion= new Document("$set", new Document(name, nombreNue));
		usuarios.findOneAndUpdate(usuario, actualizacion);
	}
	public boolean login(Usuario usuario) {

		MongoCollection<Document> usuarios = obtenerUsuarios();
		Document criterio = new Document();
		criterio.append(email, new BsonString(usuario.getEmail()));
		criterio.append(password, new BsonString(usuario.getPassword()));
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document usuarioBson = resultado.first();
		if (usuarioBson==null) {
			return false;
		}
		return true;
	}


}