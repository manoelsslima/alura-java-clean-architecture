package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.UserRepositoryGateway;
import br.com.alura.codechella.domain.entities.usuario.User;

public class UpdateUser {

    private final UserRepositoryGateway userRepositoryGateway;

    public UpdateUser(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public void updateUser(String cpf, User user) {
        this.userRepositoryGateway.updateUser(cpf, user);
    }
}
