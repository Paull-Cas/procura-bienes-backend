package com.microservice.authusuarios.services;

import com.microservice.authusuarios.dto.request.SignUpRequest;
import com.microservice.authusuarios.dto.response.UsuarioResponse;
import com.microservice.authusuarios.dto.response.UsuariosReponse;
import com.microservice.authusuarios.entities.Rol;
import com.microservice.authusuarios.entities.Usuario;
import com.microservice.authusuarios.mapper.UsuarioMapper;
import com.microservice.authusuarios.repository.IRolRepository;
import com.microservice.authusuarios.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void save(SignUpRequest signUpRequest) {
        if (usuarioRepository.existsByNombreUsuario(signUpRequest.getNombreUsuario())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: ¡Nombre de usuario ya existe!");
        }

        if (usuarioRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: ¡Email ya está en uso!");
        }

        Long idRol = signUpRequest.getIdRol();

        Rol rol = rolRepository.findById(idRol)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Rol no encontrado: " + idRol));

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreUsuario(signUpRequest.getNombreUsuario());
        nuevoUsuario.setAlias(signUpRequest.getAlias());
        nuevoUsuario.setEmail(signUpRequest.getEmail());
        nuevoUsuario.setContrasena(passwordEncoder.encode(signUpRequest.getContrasena()));
        nuevoUsuario.setEstado(signUpRequest.getEstado());
        nuevoUsuario.setUltimoLog(null);
        nuevoUsuario.setRol(rol);

        usuarioRepository.save(nuevoUsuario);
    }

    @Override
    public List<UsuariosReponse> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return UsuarioMapper.toResponseList(usuarios);
    }

    @Override
    public UsuariosReponse findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return UsuarioMapper.toResponse(usuario);
    }

    @Override
    public void update(Long idUsuario, SignUpRequest signUpRequest) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (usuarioRepository.existsByNombreUsuarioAndIdUsuarioNot(signUpRequest.getNombreUsuario(), idUsuario)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: ¡Nombre de usuario ya existe!");
        }

        if (usuarioRepository.existsByEmailAndIdUsuarioNot(signUpRequest.getEmail(), idUsuario)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: ¡Email ya está en uso!");
        }

        if (signUpRequest.getIdRol() != null) {
            Rol rol = rolRepository.findById(signUpRequest.getIdRol())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Rol no encontrado"));
            usuario.setRol(rol);
        }

        usuario.setNombreUsuario(signUpRequest.getNombreUsuario());
        usuario.setEmail(signUpRequest.getEmail());

        if (signUpRequest.getContrasena() != null && !signUpRequest.getContrasena().isEmpty()) {
            usuario.setContrasena(passwordEncoder.encode(signUpRequest.getContrasena()));
        }

        usuario.setEstado(signUpRequest.getEstado());

        usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
