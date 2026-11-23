package com.microservice.authusuarios.services;

import com.microservice.authusuarios.dto.request.SignUpRequest;
import com.microservice.authusuarios.dto.response.UsuarioResponse;
import com.microservice.authusuarios.dto.response.UsuariosReponse;

import java.util.List;

public interface IUsuarioService {

    void save(SignUpRequest signUpRequest);

    List<UsuariosReponse> findAll();

    UsuariosReponse findById(Long id);

    void update(Long idUsuario, SignUpRequest signUpRequest);

    void delete(Long id);
}
