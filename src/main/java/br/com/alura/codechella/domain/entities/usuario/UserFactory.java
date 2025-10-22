package br.com.alura.codechella.domain.entities.usuario;

import br.com.alura.codechella.domain.Address;

import java.time.LocalDate;

public class UserFactory {
    private User user;

    public User comNomeCpfNascimento(String nome, String cpf, LocalDate nascimento) {
        this.user = new User(cpf, nome, nascimento, "");
        return this.user;
    }

    public User incluirEndereco(String cep, Integer numero, String complemento) {
        this.user.setEndereco(new Address(cep, numero, complemento));
        return this.user;
    }
}
