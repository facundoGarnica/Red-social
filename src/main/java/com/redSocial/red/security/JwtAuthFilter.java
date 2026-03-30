package com.redSocial.red.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.redSocial.red.Repository.UsuarioRepository;
import com.redSocial.red.Repository.UsuarioRolRepository;
import com.redSocial.red.model.Usuario;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;

        // 1. Obtener token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtUtil.extraerUsername(token);
        }

        // 2. Validar token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            Usuario usuario = usuarioRepository.findByNombreUsuario(username)
                    .orElse(null);

            if (usuario != null && jwtUtil.validarToken(token, username)) {

                  // Obtener roles del usuario y convertirlos a authorities
                List<SimpleGrantedAuthority> authorities = usuarioRolRepository
                        .findByUsuario(usuario)
                        .stream()
                        .map(ur -> new SimpleGrantedAuthority(ur.getRol().getNombre()))
                        .collect(Collectors.toList());

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                usuario,
                                null,
                                authorities // ← roles cargados
                        );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 3. continuar la request
        filterChain.doFilter(request, response);
    }
}