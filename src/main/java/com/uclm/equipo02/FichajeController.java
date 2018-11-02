package com.uclm.equipo02;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uclm.equipo02.modelo.Fichaje;
import com.uclm.equipo02.modelo.Usuario;
import com.uclm.equipo02.persistencia.DAOFichaje;


@Controller
public class FichajeController {

	DAOFichaje fichajedao = new DAOFichaje();

	private final String alert = "alerta";
	private final String usuario_conect = "usuarioConectado";


	@RequestMapping("fichajes")
	public ModelAndView redir() {
		ModelAndView MV= new ModelAndView();
		MV.setViewName("fichajes");
		return MV;
	}

	@RequestMapping(value = "/abrirFichaje", method = RequestMethod.POST)
	public String abrirFichaje(HttpServletRequest request, Model model) throws Exception {
		String hora;
		String fecha;
		
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);

		hora=(java.time.LocalTime.now()).toString();
		fecha=(java.time.LocalDate.now()).toString();


		//nombre,fecha,hora,estado
		Fichaje fichaje = new Fichaje(usuario.getNombre(), fecha, hora,true);
		fichajedao.abrirFichaje(fichaje);
		model.addAttribute(alert, "Fichaje abierto");
		return "fichajes";
	} 

	@RequestMapping(value = "/cerrarFichaje", method = RequestMethod.POST)
	public String cerrarFichaje(HttpServletRequest request, Model model) throws Exception {
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		
		
		String hora,fecha;
		hora=(java.time.LocalTime.now()).toString();
		fecha=(java.time.LocalDate.now()).toString();
		
		Fichaje fichaje = new Fichaje(usuario.getNombre(), fecha, hora,false);
		fichajedao.cerrarFichaje(usuario, fichaje);
		return "fichajes";
	} 
	

}
