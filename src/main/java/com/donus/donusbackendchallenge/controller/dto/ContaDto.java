package com.donus.donusbackendchallenge.controller.dto;

import com.donus.donusbackendchallenge.model.Conta;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ContaDto {
    private Long id;

    private String numeroConta;

    private String nomeTitular;

    public ContaDto(Conta conta) {
        this.id = conta.getId();
        this.numeroConta = conta.getNumeroConta();
        this.nomeTitular = conta.getNomeTitular();
    }

    public static List<ContaDto> converter(List<Conta> contas){
        return contas.stream().map(ContaDto::new).collect(Collectors.toList());
    }
}
