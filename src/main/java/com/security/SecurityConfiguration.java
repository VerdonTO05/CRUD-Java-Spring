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
                // Rutas públicas
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/actorLogin").permitAll()
                .requestMatchers(HttpMethod.POST, "/chef").permitAll()
                .requestMatchers(HttpMethod.POST, "/cliente").permitAll()
                .requestMatchers(HttpMethod.GET, "/chef/listadoActivos").permitAll()
                .requestMatchers(HttpMethod.GET, "/chef/*").permitAll()
                .requestMatchers("/noticia", "/noticia/**").permitAll()
                // Rutas ADMINISTRADOR
                .requestMatchers("/admin/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/chef").hasAuthority("ADMINISTRADOR")
                .requestMatchers(HttpMethod.GET, "/cliente", "/cliente/*").hasAuthority("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/banear/**").hasAuthority("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/desbanear/**").hasAuthority("ADMINISTRADOR")
                // Rutas CHEF
                .requestMatchers(HttpMethod.PUT, "/chef").hasAuthority("CHEF")
                .requestMatchers(HttpMethod.DELETE, "/chef").hasAuthority("CHEF")
                .requestMatchers("/chef/miPerfil", "/chef/activar", "/chef/desactivar").hasAuthority("CHEF")
                .requestMatchers("/servicio/misServiciosChef").hasAuthority("CHEF")
                // Rutas CLIENTE
                .requestMatchers(HttpMethod.PUT, "/cliente").hasAuthority("CLIENTE")
                .requestMatchers(HttpMethod.DELETE, "/cliente").hasAuthority("CLIENTE")
                .requestMatchers("/cliente/miPerfil").hasAuthority("CLIENTE")
                .requestMatchers("/servicio/contratar/**").hasAuthority("CLIENTE")
                .requestMatchers("/servicio/puntuar/**").hasAuthority("CLIENTE")
                .requestMatchers("/servicio/misServiciosCliente").hasAuthority("CLIENTE")
                // Rutas SWAGGER
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/swagger-ui.html", "/swagger-ui/**").permitAll()
                // Resto de rutas requieren autenticación
                .anyRequest().authenticated());
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