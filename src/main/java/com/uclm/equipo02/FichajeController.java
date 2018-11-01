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


@Controller
public class FichajeController {

	Usuario empleado = new Usuario();

	Fichaje fichaje;
	
	private final String alert = "alerta";


	@RequestMapping("fichajes")
	public ModelAndView redir() {
		ModelAndView MV= new ModelAndView();
		MV.setViewName("fichajes");
		return MV;
	}

	@RequestMapping(value = "/abrirFichaje", method = RequestMethod.POST)
	public ModelAndView abrirFichaje(HttpServletRequest request, Model model) throws Exception {
		String hora, mensaje;
		String fecha;


		hora=(java.time.LocalTime.now()).toString();
		fecha=(java.time.LocalDate.now()).toString();

		mensaje = "Has abierto tu fichaje";
		//nombre,fecha,hora,estado
		fichaje = new Fichaje(empleado.getNombre(), fecha, hora,true);
		model.addAttribute(alert, "Fichaje abierto");
		return new ModelAndView("fichajes", "mensaje", mensaje);
	} 

	@RequestMapping(value = "/cerrarFichaje", method = RequestMethod.POST)
	public ModelAndView cerrarFichaje(HttpServletRequest request, Model model) throws Exception {
		String hora, mensaje;
		hora=(java.time.LocalTime.now()).toString();
		mensaje = "Has cerrado tu fichaje";
		fichaje.cerrarFichaje(hora, empleado);
		return new ModelAndView("fichajes", "mensaje", mensaje);
	} 

}
