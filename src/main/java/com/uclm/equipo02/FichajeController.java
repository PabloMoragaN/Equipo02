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


		//nombre,fecha,horaEntrada,horaSalida,estado
		Fichaje fichaje = new Fichaje(usuario.getNombre(), fecha, hora,null,true);
		fichajedao.abrirFichaje(fichaje);
		return "fichajes";
	} 

	@RequestMapping(value = "/cerrarFichaje", method = RequestMethod.POST)
	public String cerrarFichaje(HttpServletRequest request, Model model) throws Exception {
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		String fecha;
		fecha=(java.time.LocalDate.now()).toString();
		
		//Al no poder trabajar mas de 8 horas los fichajes entre dias quedan descartados, el criterio de busqueda se rige por nombre
		//de empleado y la fecha del dia actual para poder cerrar el fichaje
		String horaentrada;
		horaentrada=fichajedao.getHoraEntrada(usuario.getNombre(),fecha);
		
		
		String horaactual;
		horaactual=(java.time.LocalTime.now()).toString();
		fecha=(java.time.LocalDate.now()).toString();
		
		Fichaje fichaje = new Fichaje(usuario.getNombre(), fecha,horaentrada,horaactual,false);
		if(!fichaje.fichajeCierre(usuario.getNombre(), fecha, false)) {
			return "fichajes";
		}else {
			fichajedao.cerrarFichaje(usuario, fichaje);
			return "fichajes";
		}
		
	} 
	

}
