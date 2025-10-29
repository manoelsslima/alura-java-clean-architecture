package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.UserRepositoryGateway;
import br.com.alura.codechella.domain.entities.usuario.User;

import java.util.List;

public class ListUsers {

    private final UserRepositoryGateway userRepositoryGateway;

    public ListUsers(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public List<User> findAllUsers() {
        return this.userRepositoryGateway.findAllUsers();
    }
}
