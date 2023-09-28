package com.code.factory.stundetrestapi.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest req, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {


		response.setCharacterEncoding("UTF-8");
		
		String message = "Error en la autenticación: "+"Usuario no autenticado o el token expiro " + e.getMessage();
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		response.getWriter().write(new ObjectMapper().writeValueAsString(message));
		
	}

}
