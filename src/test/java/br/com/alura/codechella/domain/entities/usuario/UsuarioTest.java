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

    @Test
    public void deveCadastrarUsuarioUsandoFabricaDeUsuario() {
        FabricaDeUsuario fabricaDeUsuario = new FabricaDeUsuario();
        Usuario usuario = fabricaDeUsuario.comNomeCpfNascimento("Ana", "987.654.321-00", LocalDate.parse("1985-05-15"));

        Assertions.assertEquals("Ana", usuario.getNome());

        usuario = fabricaDeUsuario.incluirEndereco("12345-678", 100, "Apto 101");

        Assertions.assertEquals("12345-678", usuario.getEndereco().getCep());
    }

    @Test
    public void deveSerMaiorDeIdade() {
        LocalDate dataNascimento = LocalDate.now().minusYears(17);
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("111.222.333-44", "João", dataNascimento, "joao@teste.com");
        });
        Assertions.assertEquals("Usuário deve ser maior de idade", ex.getMessage());
    }
}
