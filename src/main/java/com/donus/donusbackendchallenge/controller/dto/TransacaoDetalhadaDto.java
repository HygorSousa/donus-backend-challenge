package com.donus.donusbackendchallenge.controller.dto;

import com.donus.donusbackendchallenge.model.Transacao;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class TransacaoDetalhadaDto {
    private Long id;

    private LocalDateTime data;

    private BigDecimal valor;

    private String numeroContaEntrada;

    private String numeroContaSaida;

    public TransacaoDetalhadaDto(Transacao transacao) {
        this.id = transacao.getId();
        this.data = transacao.getData();
        this.valor = transacao.getValor();
        this.numeroContaEntrada = transacao.getContaEntrada().getNumeroConta();
        this.numeroContaSaida = transacao.getContaSaida().getNumeroConta();
    }
}
