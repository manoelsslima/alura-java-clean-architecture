package br.com.alura.codechella.infra.gateways;

import br.com.alura.codechella.domain.entities.usuario.User;
import br.com.alura.codechella.infra.persistence.UserEntity;

public class UsuarioEntityMapper {

    /*
    Classe responsável por converter entre a entidade JPA UserEntity e o domínio User.
     */
    public UserEntity toEntity(User user) {
        return new UserEntity(user.getCpf(), user.getNome(), user.getNascimento(), user.getEmail());
    }

    public User toDomain(UserEntity entity) {
        return new User(entity.getCpf(), entity.getNome(), entity.getNascimento(), entity.getEmail());
    }
}
