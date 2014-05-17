package com.mycompany.cuentas.modelo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class Contacto {

	@NotEmpty
	private String nombre;
	
	@NotEmpty
	@Pattern(regexp = ".+@.+\\.[a-z]+")
	private String email;

	// getters y setters
	public void enviarEmail() {
		// codigo para envío de email
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
