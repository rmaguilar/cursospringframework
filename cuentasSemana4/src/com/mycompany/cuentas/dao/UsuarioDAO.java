package com.mycompany.cuentas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.cuentas.modelo.Usuario;
@Repository
public class UsuarioDAO {
	
	private Connection connection;

	@Autowired
	public UsuarioDAO(DataSource ds) {
		//try {
		//	connection = new ConnectionFactory().getConnection();
		//this.connection = connection;
		//} catch (SQLException e) {
		//	throw new RuntimeException(e);
		//}
		try {
			this.connection = ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean existeUsuario(Usuario usuario) {
		
		if(usuario == null) {
			throw new IllegalArgumentException("Usuario no debe ser nulo");
		}
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from usuarios where login = ? and password = ?");
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getPassword());
			ResultSet rs = stmt.executeQuery();

			boolean encontrado = rs.next();
			rs.close();
			stmt.close();

			//connection.close();
			
			return encontrado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void insertar(Usuario usuario) {
		if(usuario == null) {
			throw new IllegalArgumentException("Usuario no debe ser nulo");
		}
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement("insert into usuarios (login,password) values (?,?)");
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getPassword());
			stmt.execute();

			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}
}

