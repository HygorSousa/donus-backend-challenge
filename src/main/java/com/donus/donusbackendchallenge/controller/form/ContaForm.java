package com.donus.donusbackendchallenge.controller.form;

import com.donus.donusbackendchallenge.model.Conta;
import com.donus.donusbackendchallenge.repository.ContaRepository;
import com.google.common.base.Strings;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class ContaForm {

    @NotNull
    @NotEmpty
    public String nomeTitular;

    @NotNull
    @NotEmpty
    @Length(min = 11, max = 11)
    public String cpf;

    public Conta converter(ContaRepository repository){
        var conta = new Conta();
        conta.setCpf(this.cpf);
        conta.setNomeTitular(this.nomeTitular);
        conta.setSaldoConta(BigDecimal.ZERO);

        var ultimaConta = repository.gerarNovaConta();

        var novaConta = Long.parseLong(ultimaConta) + 1L;

        conta.setNumeroConta(Strings.padStart(String.valueOf(novaConta), 4, '0'));

        return conta;
    }
}
