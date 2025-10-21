package br.com.alura.codechella.dontuse.repository;

import br.com.alura.codechella.dontuse.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
