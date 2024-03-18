package com.semillero.ubuntu.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.semillero.ubuntu.entities.Usuario;
import com.semillero.ubuntu.enums.Rol;
import com.semillero.ubuntu.repositories.UsuarioRepositorio;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.*;

import static com.semillero.ubuntu.security.TokenJwtConfig.SECRET_KEY;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UsuarioRepositorio repository;

    public CustomAuthenticationSuccessHandler(UsuarioRepositorio repository) {
        this.repository = repository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String email = (String) oauth2User.getAttributes().get("email");
        String foto = (String) oauth2User.getAttributes().get("picture");
        String name = (String) oauth2User.getAttributes().get("name");

        Optional<Usuario> us = repository.findByEmail(email);
        if (us.isEmpty()) {
            Usuario usuarioDb = new Usuario();
            usuarioDb.setEmail(email);
            usuarioDb.setRole(Rol.ADMIN);
            usuarioDb.setNombre(name);
            repository.save(usuarioDb);
        }
        boolean isAdmin = true;

        Claims claims = Jwts.claims();
        List<GrantedAuthority> permisos = new ArrayList<>();
        permisos.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        claims.put("authorities", new ObjectMapper().writeValueAsString(permisos));
        claims.put("foto", foto);
        claims.put("nombre", name);
        claims.put("isAdmin",true);

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .signWith(SECRET_KEY)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .compact();
//
//        ResponseCookie cookie = ResponseCookie.from("AUTH-TOKEN", token)
//                .httpOnly(false)
//                .maxAge(7 * 24 * 3600)
//                .path("http://localhost:5173")
//                .secure(false)
//                .build();
//        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Credentials", "true");


        Map<String, Object> body = new HashMap<>();
        body.put("token", token);

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.sendRedirect("http://localhost:5173/Admin/"+token);
        response.setStatus(200);
        response.setContentType("application/json");

    }
}
