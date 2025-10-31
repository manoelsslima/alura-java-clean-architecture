package br.com.alura.codechella.infra.gateways;

import br.com.alura.codechella.application.gateways.UserRepositoryGateway;
import br.com.alura.codechella.domain.entities.usuario.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryInMemory implements UserRepositoryGateway {

    private final List<User> users = new ArrayList<>();

    @Override
    public User addUser(User user) {
        this.users.add(user);
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return this.users;
    }

    @Override
    public void updateUser(String cpf, User user) {
        this.users.stream().filter(u -> u.getCpf().equals(cpf)).findFirst().ifPresent(u -> {
            u.setNome(user.getNome());
            u.setNascimento(user.getNascimento());
            u.setEmail(user.getEmail());
        });
    }

    @Override
    public void deleteUser(String cpf) {
        this.users.stream().filter(u -> u.getCpf().equals(cpf)).findFirst().ifPresent(this.users::remove);
    }
}
