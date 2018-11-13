package com.uclm.equipo02;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uclm.equipo02.modelo.Fichaje;
import com.uclm.equipo02.modelo.Usuario;
import com.uclm.equipo02.persistencia.DAOFichaje;


import org.bson.Document;

@Controller
public class FichajeController {

	DAOFichaje fichajedao = new DAOFichaje();

	
	private final String usuario_conect = "usuarioConectado";
	private final String errorMessage = "errorMessage";
	private final String fichajes = "fichajes";


	/*@RequestMapping(fichajes)
	public ModelAndView redir() {
		ModelAndView MV= new ModelAndView();
		MV.setViewName(fichajes);
		return MV;
	}*/

	@RequestMapping(value = "/abrirFichaje", method = RequestMethod.POST)
	public String abrirFichaje(HttpServletRequest request, Model model) throws Exception {
		String hora;
		String fecha;

		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);


		hora=fichajedao.getCurrentTimeUsingCalendar();
		fecha=(java.time.LocalDate.now()).toString();


		Fichaje fichaje = new Fichaje(usuario.getNombre(), fecha, hora,null,true);

		if(!fichajedao.validezAbierto(fichaje)) {///FUNCIONA PERO NO SALE EL MENSAJE
			model.addAttribute(errorMessage, "No puedes abrir otro fichaje, necesitas cerrar tu fichaje actual");
			
		}else {
			fichajedao.abrirFichaje(fichaje);
		}
		return fichajes;
	} 



	/**HACER VALIDEZ ABRIR, PARA PODER ABRIR DOS O MAS FICHAJES EN UN MISMO DIA, TODOS LOS FICHAJES DE ESE DIA DEBEN DE ESTAR CERRADOS
	COMPROBAR QUE AL CERRAR EL SEGUNDO FICHAJE DEL DIA NO ACTUALIZE EL PRIMER FICHAJE DEL DIA


	AL CREAR UN SEGUNDO FICHAJE LO CREA BIEN PERO SI VAS A CERRAR ESE SEGUNDO FICHAJE TE ACTUALZIA LA HORA DE SALIDA DEL PRIMERO,
	TENDRIA QUE COGER EL HORA DE FICHAJE MAS ACTUAL Y ACTUALIZAR Y CERRAR ESE
	
	COMPROBAR TAMBIEN QUE SE CIERRE EL ULTIMO FICHAJE aunque eso croe que se comprueba con el hehco de no poder abrir ningun 
	fichaje hasta que se cierre el que esta abierto
	**/

	@RequestMapping(value = "/cerrarFichaje", method = RequestMethod.POST)
	public String cerrarFichaje(HttpServletRequest request, Model model) throws Exception {
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		String fecha;
		fecha=(java.time.LocalDate.now()).toString();

		/**Al no poder trabajar mas de 8 horas los fichajes entre dias quedan descartados, el criterio de busqueda se rige por nombre
		de empleado y la fecha del dia actual para poder cerrar el fichaje, si los criterios de aception cambian y debemos hacer fichajes entre dias
		se arreglaria introduciendo un ID al fichaje como criterio de busqueda en la BBD**/
		String horaentrada;
		horaentrada=fichajedao.getHoraEntrada(usuario.getNombre(),fecha);


		String horaactual;
		horaactual=fichajedao.getCurrentTimeUsingCalendar();
		fecha=(java.time.LocalDate.now()).toString();

		Fichaje fichaje = new Fichaje(usuario.getNombre(), fecha,horaentrada,horaactual,false);;
		
		if(fichajedao.validezCerrado(fichaje)) {///FUNCIONA PERO NO SALE EL MENSAJE
			fichajedao.cerrarFichaje(usuario, fichaje);
	
			
		}else {
			
			model.addAttribute(errorMessage, "No puedes cerrar ningun fichaje, necesitas fichar para cerrar un fichaje");
		}
		return fichajes;

	} 
	
	
	/**
	 * @method 
	 */
	
	/**@RequestMapping(value = "/listarFichajesEmpleado", method = RequestMethod.POST)
	public String listarFichajesEmpleado(HttpServletRequest request,Model model, @ModelAttribute("fichajes") List<Document> listaFichajes) throws Exception {		
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		
		
		String nombreEmpleado = usuario.getNombre();
		
		listaFichajes = usuario.getFichajesEmpleado(nombreEmpleado);
		model.addAttribute(fichajes, listaFichajes);

		return fichajes;
		} 
**/
	
	@RequestMapping(value = "/listarFichajesEmpleado", method = RequestMethod.POST)
	public String listarFichajesEmpleado(HttpServletRequest request,Model model) throws Exception {		
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		
		List<Document> listaFichajes=new ArrayList<Document>();
		String nombreEmpleado = usuario.getNombre();
		
		listaFichajes = usuario.getFichajesEmpleado(nombreEmpleado);
		model.addAttribute(fichajes, listaFichajes);

		return fichajes;
		} 
	
	
	


}
