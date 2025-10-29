package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.UserRepositoryGateway;
import br.com.alura.codechella.domain.entities.usuario.User;

/**
 * O caso de uso depende apenas do gateway (interface) e não da implementação concreta.
 */
public class CreateUser {

    private final UserRepositoryGateway userRepositoryGateway;

    public CreateUser(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public User addUser(User user) {
        return userRepositoryGateway.cadastrarUsuario(user);
    }
}
