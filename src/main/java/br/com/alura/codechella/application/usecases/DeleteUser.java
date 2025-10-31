package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.UserRepositoryGateway;

public class DeleteUser {
    private final UserRepositoryGateway userRepositoryGateway;

    public DeleteUser(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public void deleteUser(String cpf) {
        this.userRepositoryGateway.deleteUser(cpf);
    }
}
