package com.mycompany.cuentas.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.cuentas.dao.CuentaDAO;
import com.mycompany.cuentas.modelo.Cuenta;

@Controller
public class CuentaController {
	
	private CuentaDAO dao;
	
	@Autowired
	public CuentaController(CuentaDAO dao){
		this.dao = dao;
	}

	@RequestMapping("/form")
	public String initFormulario() {
		return "cuenta/formulario";
	}

	@RequestMapping("/agregarCuenta")
	public String guardarFormulario(@Valid Cuenta cuenta, BindingResult result) {

		if (result.hasErrors()) {
			return "cuenta/formulario";
		}

		System.out.println("La cuenta agregada es: " + cuenta.getDescripcion());
		//CuentaDAO dao = new CuentaDAO();
		dao.agregar(cuenta);
		return "cuenta/cuenta-agregada";
	}

	@RequestMapping("/listarCuentas")
	public String listarCuentas(Model mv) {
		//CuentaDAO dao = new CuentaDAO();
		List<Cuenta> cuentas = dao.listar();

		mv.addAttribute("cuentas", cuentas);
		return "cuenta/lista";
	}

	@RequestMapping("/eliminarCuenta")
	public String remove(Cuenta cuenta) {
		//CuentaDAO dao = new CuentaDAO();
		dao.eliminar(cuenta);
		// return "forward:listarCuentas";
		return "redirect:listarCuentas";
	}

	@RequestMapping("/muestraCuenta")
	public String muestra(Long id, Model model) {
		//CuentaDAO dao = new CuentaDAO();
		model.addAttribute("cuenta", dao.buscarPorId(id));
		return "cuenta/muestra";
	}

	@RequestMapping("/modificarCuenta")
	public String modificar(Cuenta cuenta) {
		//CuentaDAO dao = new CuentaDAO();
		dao.modificar(cuenta);
		return "redirect:listarCuentas";
	}

	@RequestMapping("/pagarCuenta")
	public void pagar(Long id, HttpServletResponse response){
		//CuentaDAO dao = new CuentaDAO();
		dao.pagar(id);
		response.setStatus(200);
	}
}
