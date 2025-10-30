package br.com.alura.codechella.config;

import br.com.alura.codechella.application.gateways.UserRepositoryGateway;
import br.com.alura.codechella.application.usecases.CreateUser;
import br.com.alura.codechella.application.usecases.ListUsers;
import br.com.alura.codechella.application.usecases.UpdateUser;
import br.com.alura.codechella.infra.gateways.UserEntityMapper;
import br.com.alura.codechella.infra.gateways.UserRepositoryGatewayImpl;
import br.com.alura.codechella.infra.persistence.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração relacionada a usuários.
 *
 * @author Manoel Lima <manoelsslima@yahoo.com.br>
 * @since 2025-10-29
 */
@Configuration
public class UsuarioConfig {

    @Bean
        // dependency injection
    CreateUser createUser(UserRepositoryGateway userRepositoryGateway) {
        return new CreateUser(userRepositoryGateway);
    }

    @Bean
    ListUsers listUsers(UserRepositoryGateway userRepositoryGateway) {
        return new ListUsers(userRepositoryGateway);
    }

    // criado para satisfazer a dependência do CreateUser
    @Bean
    UserRepositoryGateway userRepositoryGateway(UserRepository userRepository, UserEntityMapper mapper) {
        return new UserRepositoryGatewayImpl(userRepository, mapper);
    }

    // criado para satisfazer a dependência do UserRepositoryGatewayImpl
    @Bean
    UserEntityMapper userEntityMapper() {
        return new UserEntityMapper();
    }

    @Bean
    UpdateUser updateUser(UserRepositoryGateway userRepositoryGateway) {
        return new UpdateUser(userRepositoryGateway);
    }
}
