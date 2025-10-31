package br.com.alura.codechella.infra.controller;

import br.com.alura.codechella.application.usecases.CreateUser;
import br.com.alura.codechella.application.usecases.DeleteUser;
import br.com.alura.codechella.application.usecases.ListUsers;
import br.com.alura.codechella.application.usecases.UpdateUser;
import br.com.alura.codechella.domain.entities.usuario.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    // inject use case (application/usecases)
    private final CreateUser createUser;
    private final ListUsers listUsers;
    private final UpdateUser updateUser;
    private final DeleteUser deleteUser;

    public UserController(CreateUser createUser, ListUsers listUsers, UpdateUser updateUser, DeleteUser deleteUser) {
        this.createUser = createUser;
        this.listUsers = listUsers;
        this.updateUser = updateUser;
        this.deleteUser = deleteUser;
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        // returns a domain User entity
        User savedUser = createUser.addUser(new User(userDto.cpf(), userDto.nome(), userDto.nascimento(), userDto.email()));
        return new UserDto(savedUser.getCpf(), savedUser.getNome(), savedUser.getNascimento(), savedUser.getEmail());
    }

    @GetMapping
    public List<UserDto> findAllUsers() {
        return this.listUsers.findAllUsers().stream()
                .map(user -> new UserDto(user.getCpf(), user.getNome(), user.getNascimento(), user.getEmail())).toList();
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<String> updateUser(@PathVariable String cpf, @RequestBody UserDto userDto) {
        this.updateUser.updateUser(cpf, new User(userDto.cpf(), userDto.nome(), userDto.nascimento(), userDto.email()));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deleteUser(@PathVariable String cpf) {
        this.deleteUser.deleteUser(cpf);
        return ResponseEntity.noContent().build();
    }

}
