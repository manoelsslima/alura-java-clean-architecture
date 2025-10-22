package br.com.alura.codechella.dontuse.controller;

import br.com.alura.codechella.infra.persistence.UserEntity;
import br.com.alura.codechella.dontuse.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid UserEntity userEntity, UriComponentsBuilder uriBuilder) {
        service.cadastrarUsuario(userEntity);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(userEntity.getId()).toUri();

        return ResponseEntity.created(uri).body(userEntity);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

}
