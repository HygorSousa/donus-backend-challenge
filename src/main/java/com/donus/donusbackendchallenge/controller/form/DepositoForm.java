package com.donus.donusbackendchallenge.controller.form;

import com.donus.donusbackendchallenge.config.validation.ValidationException;
import com.donus.donusbackendchallenge.model.TipoTransacao;
import com.donus.donusbackendchallenge.model.Transacao;
import com.donus.donusbackendchallenge.repository.ContaRepository;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class DepositoForm {
    @NotNull
    @NotEmpty
    private String numeroConta;

    @NotNull
    @Positive
    private BigDecimal valor;

    public Transacao converter(ContaRepository contaRepository) throws ValidationException {
        var transacao = new Transacao();

        transacao.setTipoTransacao(TipoTransacao.DEPOSITO);
        transacao.setData(LocalDateTime.now());

        if (this.valor.compareTo(BigDecimal.valueOf(2000)) > 0)
            throw new ValidationException("valor", "Por questão de segurança cada transação de depósito não pode ser maior do que R$2.000.");
        
        transacao.setValor(this.valor);

        var conta = contaRepository.findByNumeroConta(this.numeroConta);

        if (conta.isPresent()) {
            conta.get().setSaldoConta(conta.get().getSaldoConta().add(valor));
            transacao.setContaEntrada(conta.get());
            return transacao;
        }

        throw new ValidationException("numeroConta", "O número da conta informada não está presente no sistema.");
    }
}
