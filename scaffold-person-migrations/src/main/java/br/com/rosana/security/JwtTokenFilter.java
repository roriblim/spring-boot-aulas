package br.com.rosana.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtTokenFilter extends GenericFilterBean{

	@Autowired
	private JwtTokenProvider tokenProvider; //note que o filter tem um atributo do tipo da nossa classe do token
	//e é autowired, ou seja, nao preciso instanciar um objeto de JwtTokenProvider
	
	public JwtTokenFilter(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	/*aqui, no doFilter, nós obtemos o token que veio no cabeçalho da request e verificamos se 
	 * ele é diferente de null e válido. Se ele for válido então tenta-se obter a autenticação 
	 * e se estiver tudo OK com o token então a autenticação é setada no contexto.*/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// Filtra todo o tráfego HTTP 
		// Obtém o token do cabeçalho da request
		String token = tokenProvider.resolveToken((HttpServletRequest) request);
		
		// Tenta obter a autenticação à partir do token obtido
		if (token != null && tokenProvider.validateToken(token)) {
			Authentication auth = tokenProvider.getAuthentication(token);
			if (auth != null) {
				
				// Se estiver tudo OK com o token então a autenticação é setada no contexto
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		//aplica o filtro
		chain.doFilter(request, response);
	}

}
