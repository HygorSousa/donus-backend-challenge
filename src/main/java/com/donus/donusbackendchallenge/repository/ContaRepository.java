package com.donus.donusbackendchallenge.repository;

import com.donus.donusbackendchallenge.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query("select MAX(COALESCE(numeroConta, '0')) from Conta")
    String gerarNovaConta();

    boolean existsByCpf(String cpf);

    Optional<Conta> findByNumeroConta(String numeroConta);
}
