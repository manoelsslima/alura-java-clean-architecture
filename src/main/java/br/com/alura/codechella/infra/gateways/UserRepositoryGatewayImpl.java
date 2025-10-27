package br.com.alura.codechella.infra.gateways;

import br.com.alura.codechella.application.gateways.UserRepositoryGateway;
import br.com.alura.codechella.domain.entities.usuario.User;
import br.com.alura.codechella.infra.persistence.UserEntity;
import br.com.alura.codechella.infra.persistence.UserRepository;

import java.util.List;

/*
O pacote gateways na camada de infraestrutura (infra) é responsável por implementar
um gateway (a interface) definida na camada de aplicação (application), usando
uma tecnologia específica de persistência (neste caso, JPA). Essa implementação atua como um adaptador
entre a aplicação e o banco de dados, permitindo que a aplicação interaja com a persistência de dados
de forma abstrata, sem depender diretamente dos detalhes da tecnologia de banco de dados utilizada.

O nome da classe poderia ser alterado para UserRepositoryGatewayJpaImpl para deixar mais claro que é uma implementação
específica usando JPA, mas isso depende das convenções de nomenclatura adotadas no projeto.

Essa classe implementa a do application/gateways/UserRepositoryGateway.java mas utilizando o
UserRepository do pacote infra/persistence.

O User não deve ser o do domain/entities/User.java. Deve ser uma class de entidade JPA,
ou seja, uma classe anotada com @Entity que representa a tabela no banco de dados.

Precisamos criar um mapper para converter entre a entidade JPA e o domínio User.
 */
public class UserRepositoryGatewayImpl implements UserRepositoryGateway {

    private final UserRepository repository;
    private final UsuarioEntityMapper mapper;

    public UserRepositoryGatewayImpl(UserRepository repository, UsuarioEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User cadastrarUsuario(User user) {
        UserEntity userEntity = mapper.toEntity(user);
        repository.save(userEntity);
        return mapper.toDomain(userEntity);
    }

    @Override
    public List<User> listarTodos() {
        //return repository.findAll();
        return null;
    }
}
