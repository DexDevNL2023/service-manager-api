package net.service.manager.auth.security;

import net.service.manager.auth.crud.enums.RoleName;
import net.service.manager.auth.crud.services.UserAccountService;
import net.service.manager.auth.generic.config.MyEndpointsManagementConfig;
import net.service.manager.auth.generic.exceptions.RestAuthenticationEntryPoint;
import net.service.manager.auth.generic.exceptions.RestTokenAccessDeniedHandler;
import net.service.manager.auth.security.jwt.JwtTokenFilter;
import net.service.manager.auth.security.jwt.JwtUtils;
import net.service.manager.auth.security.oauth2.*;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.CacheControlConfig;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

/*

Let me explain the code above.

– @EnableWebSecurity allows Spring to find and automatically apply the class to the global Web Security.

– @EnableGlobalMethodSecurity provides AOP security on methods. It enables @PreAuthorize, @PostAuthorize, it also supports JSR-250. You can find more parameters in configuration in Method Security Expressions.

– We override the configure(HttpSecurity http) method from WebSecurityConfigurerAdapter interface. It tells Spring Security how we configure CORS and CSRF, when we want to require all users to be authenticated or not, which filter (AuthTokenFilter) and when we want it to work (filter before UsernamePasswordAuthenticationFilter), which Exception Handler is chosen (AuthEntryPointJwt).

– Spring Security will load User details to perform authentication & authorization. So it has UserDetailsService interface that we need to implement.

– The implementation of UserDetailsService will be used for configuring DaoAuthenticationProvider by AuthenticationManagerBuilder.userDetailsService() method.

– We also need a PasswordEncoder for the DaoAuthenticationProvider. If we don’t specify, it will use plain text.

*/

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig {
    private final MyEndpointsManagementConfig myEndpointsManagementConfig;
    private final JwtUtils jwtUtils;
    private final UserAccountService userAccountService;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    public SecurityConfig(MyEndpointsManagementConfig myEndpointsManagementConfig, JwtUtils jwtUtils, UserAccountService userAccountService, HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
        this.myEndpointsManagementConfig = myEndpointsManagementConfig;
        this.jwtUtils = jwtUtils;
        this.userAccountService = userAccountService;
        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Si Spring MVC est sur le chemin de classe et qu'aucun CorsConfigurationSource n'est fourni,
        // Spring Security utilisera la configuration CORS fournie à Spring MVC
        http.cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers
                        .cacheControl(CacheControlConfig::disable)
                        .frameOptions(FrameOptionsConfig::sameOrigin)
                        .httpStrictTransportSecurity(withDefaults())
                        .xssProtection(withDefaults())
                        .contentSecurityPolicy(csp -> csp.policyDirectives("default-src 'self'"))
                        .referrerPolicy(referrer -> referrer.policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN))
                        .permissionsPolicy(permissions ->
                                permissions.policy(
                                        "camera=(), fullscreen=(self), geolocation=(), gyroscope=(), magnetometer=(), microphone=(), midi=(), payment=(), sync-xhr=()"
                                )
                        )
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/api/auth/login")
                        .defaultSuccessUrl("/", true).permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/api/users/logout").permitAll()
                )
                .rememberMe(withDefaults())
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                        .accessDeniedHandler(new RestTokenAccessDeniedHandler())
                )
                //Access configuration
                .requiresChannel(channel ->
                        channel.anyRequest().requiresSecure())
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers("/index.html", "/*.js", "/*.txt", "/*.json", "/*.map", "/*.css").permitAll()
                        .requestMatchers("/*.pdf", "/*.csv", "/*.xlsx", "/*.xml", "/*.html", "/*.ico", "/*.png", "/*.svg", "/*.webapp").permitAll()
                        .requestMatchers("/templates/**").permitAll()
                        .requestMatchers("/static/**").permitAll()
                        .requestMatchers("/i18n/**").permitAll()
                        .requestMatchers("/data/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/management/health").permitAll()
                        .requestMatchers("/management/health/**").permitAll()
                        .requestMatchers("/management/info").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/oauth2/**").permitAll()
                        .requestMatchers("/management/**").hasAuthority(RoleName.ADMIN.getValue())
                        .requestMatchers(antMatcher("/h2-console/**")).permitAll()
                        .anyRequest().authenticated())
                //######## OAUTH2-Login configuration ########
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .oauth2Login(oAuth2 -> {
                            try {
                                oAuth2.authorizationEndpoint(authorizationEndpointConfig -> {
                                                    try {
                                                        authorizationEndpointConfig
                                                                .baseUri("/api/oauth2/authorize")
                                                                .authorizationRequestRepository(cookieAuthorizationRequestRepository());
                                                    } catch (Exception e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                }
                                        )
                                        .redirectionEndpoint(redirectionEndpointConfig -> redirectionEndpointConfig
                                                .baseUri("/api/oauth2/callback/**")
                                        )
                                        .userInfoEndpoint(userInfoEndpointConfig -> {
                                                    try {
                                                        userInfoEndpointConfig
                                                                .oidcUserService(cOidcUserService())
                                                                .userService(cOAuth2UserService());
                                                    } catch (Exception e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                }
                                        )
                                        .successHandler(oAuth2SuccessHandler())
                                        .failureHandler(oAuth2FailureHandler());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());
        // Add our custom Token based authentication filter
        http.addFilterBefore(authorizationFiler(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /*
     * Par défaut, Spring OAuth2 utilise
     * HttpSessionOAuth2AuthorizationRequestRepository pour enregistrer l'autorisation
     * demande. Mais comme notre service est apatride, nous ne pouvons pas le sauvegarder dans le
     * session. Nous enregistrerons plutôt la demande dans un cookie encodé en Base64.
     */
	@Bean
	public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() throws Exception {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}
    
    @Bean
    protected JwtTokenFilter authorizationFiler() throws Exception {
        return new JwtTokenFilter(jwtUtils, userAccountService);
    }
    
    @Bean
    protected OAuth2AuthenticationSuccessHandler oAuth2SuccessHandler() throws Exception {
        return new OAuth2AuthenticationSuccessHandler(userAccountService, cookieAuthorizationRequestRepository());
    }
    
    @Bean
    protected OAuth2AuthenticationFailureHandler oAuth2FailureHandler() throws Exception {
        return new OAuth2AuthenticationFailureHandler(httpCookieOAuth2AuthorizationRequestRepository);
    }
    
    @Bean
    protected CustomOAuth2UserService cOAuth2UserService() throws Exception {
        return new CustomOAuth2UserService(userAccountService);
    }
    
    @Bean
    protected CustomOidcUserService cOidcUserService() throws Exception {
        return new CustomOidcUserService(userAccountService);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(CorsEndpointProperties corsProperties) {
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(myEndpointsManagementConfig.getAllowedMapping(), corsProperties.toCorsConfiguration());
        return source;
    }
}
