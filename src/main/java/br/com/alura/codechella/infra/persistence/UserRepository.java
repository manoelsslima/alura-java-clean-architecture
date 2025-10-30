package br.com.alura.codechella.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByCpf(String cpf);

    @Modifying
    @Query("update UserEntity u set u.nome = ?1, u.nascimento = ?2, u.email = ?3 where u.cpf = ?4")
    void updateUser(String nome, LocalDate nascimento, String email, String cpf);
}
