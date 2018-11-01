package com.uclm.equipo02;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uclm.equipo02.modelo.Fichaje;
import com.uclm.equipo02.modelo.Usuario;

public class FichajeController {

	Usuario empleado = new Usuario();

	Fichaje fichaje;



	@RequestMapping(value = "/abrirFichaje", method = RequestMethod.POST)
	public ModelAndView abrirFichaje(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String hora, mensaje;
		String fecha;
		
		
		hora=(java.time.LocalTime.now()).toString();
		fecha=(java.time.LocalDate.now()).toString();
		
		mensaje = "Has abierto to fichaje";
		//nombre,fecha,hora,estado
		fichaje = new Fichaje(empleado.getNombre(), fecha, hora,true);
		return new ModelAndView("fichajes", "mensaje", mensaje);
	} 

	@RequestMapping(value = "/cerrarFichaje", method = RequestMethod.POST)
	public ModelAndView cerrarFichaje(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String hora, mensaje;
		hora=(java.time.LocalTime.now()).toString();
		mensaje = "Has cerrado tu fichaje";
		fichaje.cerrarFichaje(hora, empleado);
		return new ModelAndView("home", "mensaje", mensaje);
	} 


}
