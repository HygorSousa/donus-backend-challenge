package com.donus.donusbackendchallenge.controller.dto;

import com.donus.donusbackendchallenge.model.Transacao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositoDto {

    private String numeroConta;

    private String nomeTitular;

    private BigDecimal valor;

    public DepositoDto(Transacao transacao) {
        this.numeroConta = transacao.getContaEntrada().getNumeroConta();
        this.nomeTitular = transacao.getContaEntrada().getNomeTitular();
        this.valor = transacao.getValor();
    }
}
