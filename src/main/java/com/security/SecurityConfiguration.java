package com.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private JWTAuthenticationFilter JWTAuthenticationFilter;

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {
		return authConf.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
	        .authorizeHttpRequests(auth -> auth
	        		//Rutas publicas
		            .requestMatchers(HttpMethod.POST, "/actor/visitante").anonymous()
		            .requestMatchers(HttpMethod.GET, "/articulo", "/articulo/**").anonymous()

		            //Rutas Registrado
		            .requestMatchers(HttpMethod.PUT, "/actor").hasAnyAuthority("VISITANTE", "REDACTOR")
		            .requestMatchers(HttpMethod.DELETE, "/actor").hasAnyAuthority("VISITANTE", "REDACTOR")
		            .requestMatchers(HttpMethod.GET, "/comentario/**").hasAnyAuthority("VISITANTE", "REDACTOR")

		            //Rutas Usuario Registrado como visitantes
		           
		            .requestMatchers(HttpMethod.PUT, "/comentario/**").hasAnyAuthority("VISITANTE")
		            .requestMatchers(HttpMethod.DELETE, "/comentario/**").hasAnyAuthority("VISITANTE")
		            .requestMatchers(HttpMethod.POST, "/comentario/**").hasAnyAuthority("VISITANTE")

		          //Rutas Usuario Registrado como Redactor
		            .requestMatchers(HttpMethod.PUT, "/articulo/**").hasAnyAuthority("REDACTOR")
		            .requestMatchers(HttpMethod.DELETE, "/articulo/**").hasAnyAuthority("REDACTOR")
		            .requestMatchers(HttpMethod.POST, "/articulo").hasAnyAuthority("REDACTOR")
		            
		            .requestMatchers(HttpMethod.GET, "/actor/allUsers").hasAnyAuthority("REDACTOR")
		            .requestMatchers(HttpMethod.PUT, "/articulo/*/ban").hasAnyAuthority("REDACTOR")
		            .requestMatchers(HttpMethod.PUT, "/articulo/*/unban").hasAnyAuthority("REDACTOR")



		            
	            .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

	            .anyRequest().authenticated() 
	        );

	    // Equipamos el filtro del JWT antes del filtro por defecto de Spring
	    http.addFilterBefore(JWTAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		// Permitir peticiones solo desde este origen (frontend)
		configuration.setAllowedOriginPatterns(List.of("*"));

		// Permitir estos métodos HTTP
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

		// Permitir estas cabeceras en las peticiones
		configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

		// Permitir enviar cookies o credenciales en las peticiones
		configuration.setAllowCredentials(true);

		// Asociar esta configuración a todas las rutas (/**)
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}