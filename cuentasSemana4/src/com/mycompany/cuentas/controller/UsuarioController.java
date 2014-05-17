package com.mycompany.cuentas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.cuentas.dao.CuentaDAO;
import com.mycompany.cuentas.dao.UsuarioDAO;
import com.mycompany.cuentas.modelo.Usuario;

@Controller
public class UsuarioController {
	
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	public UsuarioController(UsuarioDAO dao){
		this.usuarioDAO = dao;
	}

	@RequestMapping("/loginForm")
	public String formulario() {
		return "usuario/login";
	}

	@RequestMapping("/efectuaLogin")
	public String efectuaLogin(Usuario usuario, HttpSession session) {
		//UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.existeUsuario(usuario)) {
			session.setAttribute("usuarioLogueado", usuario);
			return "menu";
		}
		return "redirect:loginForm";
	}

	@RequestMapping("/logout")
	public String formulario(HttpSession session) {
		session.invalidate();
		return "redirect:loginForm";
	}

}
