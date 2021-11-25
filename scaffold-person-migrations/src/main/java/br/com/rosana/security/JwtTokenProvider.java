package br.com.rosana.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.rosana.exception.InvalidJwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenProvider {

	//CLASSE DO TOKEN, PARA VALIDAÇÃO, PEGAR O USERNAME DO TOKEN, CRIAR TOKEN, ETC
	
	@Value("${security.jwt.token.secret-key:secret}") //valor padrão
	private String secretKey = "secret";
	
	@Value("${security.jwt.token.expire-length:3600000}") //se eu nao definir nenhum valor, a variável vai assumir o valor que está depois dos dois pontos
	private long validityInMilliseconds = 3600000;
	//posso também setar esses valores de secret key e validity in milliseconds no application.properties
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostConstruct
	public void init() {

		//codifica a secretKey
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(String username, List<String> roles) {

		//cria um token a partir de username, roles, secretKey e data atual
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("roles", roles);
		
		Date now = new Date();
		Date validity = new Date(now.getTime()+validityInMilliseconds);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
	
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
	
	private String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
		//para entender melhor o que é feito aqui, pode-se verificar no site da JWT como é o modelo do token:
		//basicamente, aqui ele vai setar a secretKey no JWTS parser, pegar o token e decodificar ele.
		//então, vai pegar o body e pegar o subject. OU seja, vai pegar o username QUE ESTÁ no token
	}
	
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");

		if (bearerToken!=null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
			//vai pegar o token sem pegar a parte inicial "Bearer "
			//o token geralmente vem concatenado assim: Bearer sdkjhdkjsahdkja
		}
		return null;
	}
	
	public boolean validateToken(String token) {
		//vai validar o token (retorna true se estiver válido)
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			if (claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
			return true;
		} catch(Exception e) {
			throw new InvalidJwtAuthenticationException("Expired or invalid token");
		}

	}
	
}
