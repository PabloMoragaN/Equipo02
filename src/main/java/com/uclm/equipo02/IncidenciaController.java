package com.uclm.equipo02;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uclm.equipo02.modelo.Incidencia;
import com.uclm.equipo02.modelo.Usuario;
import com.uclm.equipo02.persistencia.DAOIncidencia;


@Controller
public class IncidenciaController {
	
	private final String usuario_conect = "usuarioConectado";
	DAOIncidencia incidenciaDao = new DAOIncidencia();
	
	@RequestMapping(value = "/crearIncidencia", method = RequestMethod.POST)
	public String crearIncidencia(HttpServletRequest request, Model model) throws Exception {
		
		Usuario usuario;
	    usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
	   
		String nombreUsuario = usuario.getNombre();
		String categoria = request.getParameter("listaTiposIncidencia");
		String fechaCreacion =(java.time.LocalDate.now()).toString();
		String descripcion = request.getParameter("textoIncidencia");
		String estado = "Pendiente";
		String comentarioGestor = "";
		
		Incidencia incidencia = new Incidencia(nombreUsuario, categoria, descripcion, estado, 
				fechaCreacion, comentarioGestor);
		
		try {
			incidenciaDao.insert(incidencia);
		} catch (Exception e) {

		}
		
		return "interfazCrearIncidencia";
	}
}
