package com.mycompany.cuentas.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object controller) throws IOException {
		String uri = request.getRequestURI();
		if (uri.endsWith("loginForm") || uri.endsWith("efectuaLogin")
				|| uri.contains("resources")) {
			return true;
		}

		if (request.getSession().getAttribute("usuarioLogueado") != null) {
			return true;
		} else {
			response.sendRedirect("loginForm");
			return false;
		}
	}

}
