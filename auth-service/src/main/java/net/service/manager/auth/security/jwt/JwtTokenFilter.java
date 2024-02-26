package net.service.manager.auth.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.service.manager.auth.crud.entities.UserAccount;
import net.service.manager.auth.crud.services.UserAccountService;
import net.service.manager.auth.generic.exceptions.AuthenticationException;
import net.service.manager.auth.security.SecurityUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

/*
Filter the Requests
---------------------
Let’s define a filter that executes once per request. 
So we create AuthTokenFilter class that extends OncePerRequestFilter and override doFilterInternal() method.

What we do inside doFilterInternal():
– get JWT from the Authorization header (by removing Bearer prefix)
– if the request has JWT, validate it, parse username from it
– from username, get UserDetails to create an Authentication object
– set the current UserDetails in SecurityContext using setAuthentication(authentication) method.

If you want to get UserDetails, just use SecurityContext like this:
UserDetails userDetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

// userDetails.getUsername()
// userDetails.getPassword()
// userDetails.getAuthorities()
*/

/* Filtre requis par Spring Security. Il sera exécuté pour chaque requête envoyée à l'application. Il a pour but de
- recupérer le token JWT envoyé dans les requetes, le verifier, le valider, recuperer les claims(username+roles)
  afin d'authentifier le user dans le context de Spring Security. Ce qui va permettre à Spring Security
  d'autoriser/interdire l'accès à la ressource.
- Sinon, renvoyer au client un message et code d'erreur.
*/
@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtUtils jwtUtils;
	private final UserAccountService userAccountService;

	public JwtTokenFilter(JwtUtils jwtUtils, UserAccountService userAccountService) {
		this.jwtUtils = jwtUtils;
		this.userAccountService = userAccountService;
	}

	// cette mtd s'exécute a chaque fois que le backend reçoit une requete
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)	throws ServletException, IOException {
		try {
			// extract token information
			String jwt = jwtUtils.getJwtFromRequest(request);
			log.info("jwt=" + jwt);

			if (StringUtils.hasText(jwt) && jwtUtils.validateToken(jwt)) {
				log.info("JWT is valided=" + jwt);
		        // recup username+roles
		        String username = jwtUtils.getUserNameFromToken(jwt);
				log.info("Username from JWT accessToken: " + username);
		        if (StringUtils.hasText(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserAccount user = userAccountService.findByUsername(username);
					Collection<? extends GrantedAuthority> authorities = SecurityUtils.buildUserAuthorities(user.getRoles());
					log.info("roles=" + authorities);
	                SecurityContext context = SecurityContextHolder.createEmptyContext();
	                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
	                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                context.setAuthentication(authToken);
	                SecurityContextHolder.setContext(context);
		        }
			}
		} catch (Exception ex) {
			log.info("Could not set user authentication in security context");
			throw new AuthenticationException("Could not set user authentication in security context");
		}
		
        // demander a Spring de passer au filtre suivant, tenant compte qu'il ne reconnait pas le user
        filterChain.doFilter(request, response);
    }
}
