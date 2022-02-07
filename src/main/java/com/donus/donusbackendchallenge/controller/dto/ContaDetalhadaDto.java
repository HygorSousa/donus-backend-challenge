package com.donus.donusbackendchallenge.controller.dto;

import com.donus.donusbackendchallenge.model.Conta;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ContaDetalhadaDto {
    private Long id;

    private String cpf;

    private String numeroConta;

    private String nomeTitular;

    private BigDecimal saldoConta;

    public ContaDetalhadaDto(Conta conta) {
        this.id = conta.getId();
        this.cpf = conta.getCpf();
        this.numeroConta = conta.getNumeroConta();
        this.nomeTitular = conta.getNomeTitular();
        this.saldoConta = conta.getSaldoConta();
    }
}
