package com.uclm.equipo02;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uclm.equipo02.modelo.Usuario;
import com.uclm.equipo02.persistencia.UsuarioDaoImplement;

@Controller

public class HomeController {

	
	private final String usuario_login = "login";
	private final String usuario_conect = "usuarioConectado";
	private final String alert = "alerta";

	UsuarioDaoImplement userDao = new UsuarioDaoImplement();


	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String iniciarSesion(HttpServletRequest request, Model model) throws Exception {
		//String cadenaUrl = usuarioServ;
		String email = request.getParameter("txtUsuarioEmail");
		String password = request.getParameter("txtUsuarioPassword");
		if (email.equals("") || password.equals("")) {
			model.addAttribute(alert, "Por favor rellene los campos");
			return usuario_login;
		}
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setPassword(password);
		
		if (userDao.login(usuario) && request.getSession().getAttribute(usuario_conect) == null){
			usuario.setRol(userDao.devolverRol(usuario));

			if(usuario.getRol().equalsIgnoreCase("empleado")) {
				request.getSession().setAttribute(usuario_conect, usuario);
				return "fichajes";
			}else if (usuario.getRol().equalsIgnoreCase("administrador")){
				request.getSession().setAttribute(usuario_conect, usuario);
				return "interfazAdministrador";
			}

		}else{
			model.addAttribute(alert, "Usuario y/o clave incorrectos");
			return usuario_login;
		}
		return usuario_login;
	}

	public ModelAndView cambiarVista(String nombreVista) {
		ModelAndView vista = new ModelAndView(nombreVista);
		return vista;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView cerrarSesion(HttpServletRequest request) throws Exception {
		HttpSession sesion = request.getSession();

		System.out.println("Sesion antes de invalidar: " + sesion);
		sesion.invalidate();
		System.out.println("Invalidamos la sesion: " + sesion);

		return cambiarVista(usuario_login);
	}
}
