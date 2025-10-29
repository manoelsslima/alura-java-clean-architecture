package br.com.alura.codechella.infra.controller;

import br.com.alura.codechella.application.usecases.CreateUser;
import br.com.alura.codechella.domain.entities.usuario.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    // inject use case (application/usecases)
    private final CreateUser createUser;

    public UserController(CreateUser createUser) {
        this.createUser = createUser;
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        // returns a domain User entity
        User savedUser = createUser.addUser(new User(userDto.cpf(), userDto.nome(), userDto.nascimento(), userDto.email()));
        return new UserDto(savedUser.getCpf(), savedUser.getNome(), savedUser.getNascimento(), savedUser.getEmail());
    }

}
