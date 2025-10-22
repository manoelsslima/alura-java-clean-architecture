package br.com.alura.codechella.infra.gateways;

import br.com.alura.codechella.application.gateways.UserRepositoryGateway;
import br.com.alura.codechella.domain.entities.usuario.User;
import br.com.alura.codechella.infra.persistence.UserRepository;

import java.util.List;

/*
O pacote gateways na camada de infraestrutura (infra) é responsável por implementar
um gateway (a interface) definida na camada de aplicação (application), usando
uma tecnologia específica de persistência (neste caso, JPA). Essa implementação atua como um adaptador
entre a aplicação e o banco de dados, permitindo que a aplicação interaja com a persistência de dados
de forma abstrata, sem depender diretamente dos detalhes da tecnologia de banco de dados utilizada.
 */
public class UserRepositoryGatwayImpl implements UserRepositoryGateway {

    private final UserRepository repository;

    public UserRepositoryGatwayImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User cadastrarUsuario(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> listarTodos() {
        return repository.findAll();
    }
}
