package com.donus.donusbackendchallenge.controller.dto;

import com.donus.donusbackendchallenge.model.Transacao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferenciaDto {

    private String numeroContaOrigem;

    private String numeroContaDestino;

    private String nomeTitularDestino;

    private BigDecimal valor;

    public TransferenciaDto(Transacao transacao) {
        this.numeroContaOrigem = transacao.getContaSaida().getNumeroConta();
        this.numeroContaDestino = transacao.getContaEntrada().getNumeroConta();
        this.nomeTitularDestino = transacao.getContaEntrada().getNomeTitular();
        this.valor = transacao.getValor();
    }
}
