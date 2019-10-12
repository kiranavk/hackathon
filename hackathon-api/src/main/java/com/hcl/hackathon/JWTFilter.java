package com.hcl.hackathon;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(JWTFilter.class);
	
	private final List<String> excludePatterns = Arrays.asList("/token");
	PathMatcher pathMatcher = new AntPathMatcher();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String prefix = "Bearer ";
		String header = request.getHeader("Authorization");
		if(header == null || !header.startsWith("Bearer ")) {
			throw new AuthException("Authorization is not valid");
		}
		final String token = header.substring(prefix.length());
		logger.debug("Token: "+token);
		
		setPayloadInRequest(request, token);
		filterChain.doFilter(request, response);
	}
	
	private void setPayloadInRequest(HttpServletRequest request, String token) {
		request.setAttribute("token", token);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return excludePatterns.stream().anyMatch((url) -> pathMatcher.match(url, request.getServletPath()));
	}

}
