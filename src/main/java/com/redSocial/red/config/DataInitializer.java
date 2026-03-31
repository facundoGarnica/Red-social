package com.redSocial.red.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.redSocial.red.DTO.Request.UsuarioRequest;
import com.redSocial.red.DTO.Request.UsuarioRolRequest;
import com.redSocial.red.Repository.RolRepository;
import com.redSocial.red.Repository.UsuarioRepository;
import com.redSocial.red.model.Rol;
import com.redSocial.red.service.UsuarioRolService;
import com.redSocial.red.service.UsuarioService;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private UsuarioRolService usuarioRolService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("🚀 INICIANDO DATA INITIALIZER...");

        try {
            // Crear Roles base
            crearRolSiNoExiste("ROLE_ADMIN");
            crearRolSiNoExiste("ROLE_USUARIO");

            // Crear Usuario Administrador
            crearAdminSiNoExiste();

            // Crear Usuario Normal
            crearUsuarioSiNoExiste();

        } catch (Exception e) {
            System.err.println("❌ ERROR EN DATA INITIALIZER: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void crearRolSiNoExiste(String nombre) {
        if (rolRepository.findByNombre(nombre).isEmpty()) {
            Rol rol = new Rol();
            rol.setNombre(nombre);
            rolRepository.save(rol);
        }
    }

    private void crearAdminSiNoExiste() {
        if (usuarioRepository.findByNombreUsuario("admin").isEmpty()) {
            UsuarioRequest adminReq = new UsuarioRequest();
            adminReq.setNombreUsuario("admin");
            adminReq.setEmail("admin@redsocial.com");
            adminReq.setPassword("admin123");
            
            // El service guarda y asigna ROLE_USUARIO por defecto
            var adminResp = usuarioService.guardar(adminReq);
            
            // Asignamos manualmente el ROLE_ADMIN extra
            Rol rolAdmin = rolRepository.findByNombre("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Rol ADMIN no encontrado"));
            
            UsuarioRolRequest asignacionAdmin = new UsuarioRolRequest();
            asignacionAdmin.setUsuarioId(adminResp.getId());
            asignacionAdmin.setRolId(rolAdmin.getId());
            
            usuarioRolService.guardar(asignacionAdmin);
        }
    }

    private void crearUsuarioSiNoExiste() {
        if (usuarioRepository.findByNombreUsuario("user").isEmpty()) {
            UsuarioRequest userReq = new UsuarioRequest();
            userReq.setNombreUsuario("user");
            userReq.setEmail("user@redsocial.com");
            userReq.setPassword("user123");
            
            // usuarioService.guardar
            usuarioService.guardar(userReq);
        }
    }
}