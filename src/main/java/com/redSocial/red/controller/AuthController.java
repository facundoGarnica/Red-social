package com.redSocial.red.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.redSocial.red.DTO.Request.AuthRequest;
import com.redSocial.red.DTO.Request.UsuarioRequest;
import com.redSocial.red.DTO.Response.AuthResponse;
import com.redSocial.red.DTO.Response.UsuarioResponse;
import com.redSocial.red.Repository.UsuarioRepository;
import com.redSocial.red.model.Usuario;
import com.redSocial.red.security.JwtUtil;
import com.redSocial.red.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        String token = jwtUtil.generarToken(usuario.getNombreUsuario());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    // REGISTRAR
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsuarioRequest request) {
        try {
            UsuarioResponse usuario = usuarioService.guardar(request);
            String token = jwtUtil.generarToken(usuario.getNombreUsuario());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/me")
    public ResponseEntity<UsuarioResponse> me(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }

        String token = authHeader.substring(7); // quita "Bearer "
        String username = jwtUtil.extraerUsername(token); // extrae username del token

        UsuarioResponse response = usuarioService.obtenerUsuarioLogueado(username);

        return ResponseEntity.ok(response);
    }
}