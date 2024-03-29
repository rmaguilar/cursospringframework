package com.mycompany.cuentas.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.cuentas.ConnectionFactory;

@Controller
public class InfraController {

	@RequestMapping("/tablas")
	public String crearBaseDeDatos() throws SQLException {
		Connection c = new ConnectionFactory().getConnection();
		
		PreparedStatement st1 = c.prepareStatement("drop table cuentas if exists;");
		st1.execute();
		
		PreparedStatement st11 = c.prepareStatement("create table cuentas (id int identity, "
				+ "descripcion varchar(255), pago boolean, valor double, fechaPago date, tipo varchar(20));");
		st11.execute();
		
		PreparedStatement st2 = c.prepareStatement("drop table usuarios if exists;");
		st2.execute();
		
		PreparedStatement st22 = c.prepareStatement("create table usuarios (login varchar(255), "
				+ "password varchar(255));");
		st22.execute();
		
		PreparedStatement st3 = c.prepareStatement("insert into usuarios(login, password) values ('rmaguilar','online');");
		st3.execute();
		
		PreparedStatement st4 = c.prepareStatement("insert into usuarios(login, password) values ('joedayz','online');");
		st4.execute();
		
		
		
		c.close();
		return "infra-ok";
	}
}
