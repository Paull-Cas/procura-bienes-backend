package com.microservice.authusuarios.services;
import com.microservice.authusuarios.dto.response.PermisosResponse;
import com.microservice.authusuarios.dto.response.UsuarioDashboardResponse;
import com.microservice.authusuarios.dto.response.UsuarioResponse;
import com.microservice.authusuarios.entities.Usuario;
import com.microservice.authusuarios.mapper.PermisosMapper;
import com.microservice.authusuarios.mapper.UsuarioDashboardMapper;
import com.microservice.authusuarios.mapper.UsuarioMapper;
import com.microservice.authusuarios.repository.IPermisoRepository;
import com.microservice.authusuarios.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IUsuarioRepository userRepository;

    @Autowired
    IPermisoRepository permisoRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));
        return UserDetailsImpl.build(usuario);
    }

    @Transactional
    public List<UsuarioResponse> obtenerUsuarios(List<Long> listIdRequerimientos){
        List<Usuario> usuarios = userRepository.findAllById(listIdRequerimientos);
        return UsuarioMapper.toDTOList(usuarios);
    }

    @Transactional
    public UsuarioDashboardResponse obtenerDatosDashboard(String email) {
        Usuario usuario = userRepository.findByEmail(email).orElse(null);
        List<PermisosResponse> permisos = PermisosMapper.toDTOList(permisoRepository.getPermisosByUser(email));
        if (usuario == null || permisos.isEmpty()) return null;
        return UsuarioDashboardMapper.toDTO(usuario, permisos);
    }
}
