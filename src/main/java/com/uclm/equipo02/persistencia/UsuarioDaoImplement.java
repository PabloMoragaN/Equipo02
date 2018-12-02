package com.uclm.equipo02.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.BsonString;
import org.bson.BsonValue;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.uclm.equipo02.Auxiliar.Utilidades;
import com.uclm.equipo02.modelo.Usuario;

public class UsuarioDaoImplement{

	private final String name = "nombre";
	private final String password = "pwd";
	private final String email = "email";
	private final String rol = "rol";
	private final String dni = "dni";
	

	public boolean login(Usuario usuario) {

		MongoCollection<Document> usuarios = obtenerUsuarios();
		Document criterio = new Document();
		criterio.append(email, new BsonString(usuario.getEmail()));
		criterio.append(password, new BsonString(Utilidades.encrypt(usuario.getPassword())));
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document usuarioBson = resultado.first();
		if (usuarioBson==null) {
			return false;
		}
		return true;
	}


	//Inserta un nuevo usuario en la BBDD
	public void insert(Usuario usuario) throws Exception {
		if(!selectNombre(usuario)) {
			Document bso = new Document();
			bso.append(name, new BsonString(usuario.getNombre()));
			bso.append(password, new BsonString(usuario.getPassword()));
			bso.append(email, new BsonString(usuario.getEmail()));
			bso.append(rol, new BsonString(usuario.getRol()));
			bso.append(dni, new BsonString(usuario.getDni()));
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
	
public Usuario selectNombre(String nombreParam) {
		
		MongoCollection<Document> usuarios = obtenerUsuarios();
		Document criterio = new Document();
		criterio.append(name, new BsonString(nombreParam));
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document usuario = resultado.first();
		Usuario result;
		if (usuario==null) {
			return null;
		}
		else {
			
			String nombreUser = usuario.getString(name);
			String pwdUser = usuario.getString(password);
			String mailUser = usuario.getString(email);
			String rolUser = usuario.getString(rol);
			String dniUser = usuario.getString(dni);
			result = new Usuario(nombreUser, pwdUser, mailUser, rolUser,dniUser);
		}
		return result;
}

	public String devolverRol(Usuario usuario) {
		MongoCollection<Document> usuarios = obtenerUsuarios();
		Document criterio = new Document();
		criterio.append(dni, new BsonString(usuario.getDni()));
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document usuariobso = resultado.first();
		if (usuario==null){
			return null;
		}else {
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
			String nombreFinal= usuariobso.getString(name);
			usuario.setNombre(nombreFinal);
		}
		return usuario.getNombre();
	}
	public String devolverDni(Usuario usuario) {
		MongoCollection<Document> usuarios = obtenerUsuarios();
		Document criterio = new Document();
		criterio.append(email, new BsonString(usuario.getEmail()));
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document usuariobso = resultado.first();
		if (usuario==null){
			return null;
		}else {
			String dniUser = usuariobso.getString(dni);
			usuario.setDni(dniUser);

		}
		return usuario.getDni();
		
	}

	//Obtener todos los usuarios
	public MongoCollection<Document> obtenerUsuarios() {
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
	
	//Devuelve los usuarios que son gestores
		public List<Usuario> obtenerGestores() {
			MongoCollection<Document> usuarios = obtenerUsuarios();
			FindIterable<Document> resultado=usuarios.find();
			String nombre;
			Document usuario;
			Iterator<Document> lista=resultado.iterator();
			List<Usuario> retorno=new ArrayList<Usuario>();
			while(lista.hasNext()) {
				usuario=lista.next();
				nombre=usuario.getString(name);
				Usuario user = new Usuario();
				
				if(user.getRol().equals("Gestor de incidencias")){
						retorno.add(user);
				}
			}
			return retorno;
		}

	//Borrar usuario
	public void delete (Usuario usuario){
		//List<Usuario> todos=selectAll();
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


	public String devolverMail(Usuario usuario) {
		MongoCollection<Document> usuarios = obtenerUsuarios();
		Document criterio = new Document();
		criterio.append(dni, new BsonString(usuario.getDni()));
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document usuariobso = resultado.first();
		if (usuario==null){
			return null;
		}else {

			String mailFinal=usuariobso.getString(email);

			usuario.setRol(mailFinal);
		}
		return usuario.getRol();
	}




public void updatePwd(Usuario usuario) throws Exception{
	MongoCollection<Document> usuarios = obtenerUsuarios();
	Document criterio = new Document();
	criterio.append(name, new BsonString(usuario.getNombre()));
	FindIterable<Document> resultado=usuarios.find(criterio);
	Document usuarioBso = resultado.first();
	if (usuarioBso==null)
		throw new Exception("Fallo la actualizacion de los datos del usuario.");

/**usuario.getPassword() hay que desencriptar**/
	
	Document actualizacion= new Document("$set", new Document(password, new BsonString(Utilidades.encrypt(usuario.getPassword()))));
	usuarios.findOneAndUpdate(usuarioBso, actualizacion);
}

	public void updateRol(Usuario usuario, String rolNuevo) throws Exception{
		MongoCollection<Document> usuarios = obtenerUsuarios();
		Document criterio = new Document();
		criterio.append(dni, new BsonString(usuario.getDni()));
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document usuarioBso = resultado.first();
		if (usuarioBso==null)
			throw new Exception("Fallo la actualizacion de los datos del usuario.");

		Document actualizacion= new Document("$set", new Document(rol, new BsonString(rolNuevo)));
		usuarios.findOneAndUpdate(usuarioBso, actualizacion);
	}
	public void updateNombre(Usuario usuario, String nombreNuevo) throws Exception{
		MongoCollection<Document> usuarios = obtenerUsuarios();
		Document criterio = new Document();
		criterio.append(email, new BsonString(usuario.getEmail()));
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document usuarioBso = resultado.first();
		if (usuarioBso==null)
			throw new Exception("Fallo la actualizacion de los datos del usuario.");

		Document actualizacion= new Document("$set", new Document(name, new BsonString(nombreNuevo)));
		usuarios.findOneAndUpdate(usuarioBso, actualizacion);
		
	}
	public void updateEmail(Usuario usuario, String emailNuevo) throws Exception{
		MongoCollection<Document> usuarios = obtenerUsuarios();
		Document criterio = new Document();
		criterio.append(dni, new BsonString(usuario.getDni()));
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document usuarioBso = resultado.first();
		if (usuarioBso==null)
			throw new Exception("Fallo la actualizacion de los datos del usuario.");

		Document actualizacion= new Document("$set", new Document(email, new BsonString(emailNuevo)));
		usuarios.findOneAndUpdate(usuarioBso, actualizacion);
		
	}

}