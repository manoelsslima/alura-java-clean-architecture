package br.com.alura.codechella.domain.entities.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UsuarioTest {
    @Test
    public void naoDeveCadastrarUsuarioComCpfInvalido() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("12345678900", "Manoel", LocalDate.parse("1990-01-01"), "manoel@mail.com");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("123.45678900", "Manoel", LocalDate.parse("1990-01-01"), "manoel@mail.com");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("123.456.78900", "Manoel", LocalDate.parse("1990-01-01"), "manoel@mail.com");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("", "Manoel", LocalDate.parse("1990-01-01"), "manoel@mail.com");
        });
    }
}
