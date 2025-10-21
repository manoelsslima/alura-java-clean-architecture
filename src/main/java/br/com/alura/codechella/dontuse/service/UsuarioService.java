package br.com.alura.codechella.dontuse.service;

import br.com.alura.codechella.dontuse.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario cadastrarUsuario(Usuario usuario);

    List<Usuario> listarTodos();
}
