package br.com.alura.codechella.application.gateways;

import br.com.alura.codechella.domain.entities.usuario.User;

import java.util.List;

public interface UserRepository {
    User cadastrarUsuario(User usuario);

    List<User> listarTodos();
}
