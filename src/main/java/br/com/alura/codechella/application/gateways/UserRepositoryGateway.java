package br.com.alura.codechella.application.gateways;

import br.com.alura.codechella.domain.entities.usuario.User;

import java.util.List;

/*
Gateway define o contrato e mantém o core independente de ‘framework’; casos de uso
depende apenas dessas interfaces.
 */
public interface UserRepositoryGateway {

    User addUser(User usuario);

    List<User> findAllUsers();
}
