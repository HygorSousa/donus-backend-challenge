package com.donus.donusbackendchallenge.repository;

import com.donus.donusbackendchallenge.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
