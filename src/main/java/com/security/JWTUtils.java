package com.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.entity.Actor;
import com.services.ActorService;
import com.services.RedactorService;
import com.services.VisitanteService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JWTUtils {

    @Autowired
    private ActorService actorService;

    @Autowired
    @Lazy
    private VisitanteService visitanteService;

    @Autowired
    @Lazy
    private RedactorService redactorService;

    @Value("${jwt.secret}")
    private String JWT_FIRMA;

    @Value("${jwt.expiration}")
    private long EXTENCION_TOKEN;

    public String getToken(HttpServletRequest request) {
        String tokenBearer = request.getHeader("Authorization");
        if (StringUtils.hasText(tokenBearer) && tokenBearer.startsWith("Bearer ")) {
            return tokenBearer.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_FIRMA).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("JWT ha experido o no es valido");
        }
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date fechaActual = new Date();
        Date fechaExpiracion = new Date(fechaActual.getTime() + EXTENCION_TOKEN);
        String rol = authentication.getAuthorities().iterator().next().getAuthority();
        String token = Jwts.builder().setSubject(username).setIssuedAt(fechaActual).setExpiration(fechaExpiracion)
                .claim("rol", rol).signWith(SignatureAlgorithm.HS512, JWT_FIRMA).compact();
        return token;
    }

    public String getUsernameOfToken(String token) {
        return Jwts.parser().setSigningKey(JWT_FIRMA).parseClaimsJws(token).getBody().getSubject();
    }

    public <T> T userLogin() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        Optional<Actor> actorO = actorService.findByUsername(username);
        if (!actorO.isPresent()) {
            return null;
        }
        Actor actor = actorO.get();
        switch (actor.getRol()) {
            case VISITANTE:
                return (T) visitanteService.findByUsername(username).orElse(null);
            case REDACTOR:
                return (T) redactorService.findByUsername(username).orElse(null);
            default:
                return null;
        }
    }

}