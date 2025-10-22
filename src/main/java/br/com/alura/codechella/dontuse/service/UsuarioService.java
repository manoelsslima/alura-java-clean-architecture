package br.com.alura.codechella.dontuse.service;

import br.com.alura.codechella.infra.persistence.UserEntity;

import java.util.List;

public interface UsuarioService {
    UserEntity cadastrarUsuario(UserEntity userEntity);

    List<UserEntity> listarTodos();
}
