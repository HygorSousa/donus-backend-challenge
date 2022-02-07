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
public class TransferenciaForm {
    @NotNull
    @NotEmpty
    private String numeroContaOrigem;

    @NotNull
    @NotEmpty
    private String numeroContaDestino;

    @NotNull
    @Positive
    private BigDecimal valor;

    public Transacao converter(ContaRepository contaRepository) throws ValidationException {
        var transacao = new Transacao();
        BigDecimal saldoConta;

        transacao.setTipoTransacao(TipoTransacao.TRANSFERENCIA);
        transacao.setData(LocalDateTime.now());
        transacao.setValor(this.valor);

        var contaOrigem = contaRepository.findByNumeroConta(this.numeroContaOrigem);
        var contaDestino = contaRepository.findByNumeroConta(this.numeroContaDestino);

        if (contaOrigem.isPresent()) {
            saldoConta = contaOrigem.get().getSaldoConta().subtract(valor);

            if (saldoConta.compareTo(BigDecimal.ZERO) < 0)
                throw new ValidationException("valor", "O valor da transferencia é inválido, pois a conta de Origem ficará com saldo negativo.");

            contaOrigem.get().setSaldoConta(saldoConta);
            transacao.setContaSaida(contaOrigem.get());
        } else {
            throw new ValidationException("numeroContaOrigem", "O número da conta de Origem informada não está presente no sistema.");
        }

        if (contaDestino.isPresent()) {
            contaDestino.get().setSaldoConta(contaDestino.get().getSaldoConta().add(valor));
            transacao.setContaEntrada(contaDestino.get());
        } else {
            throw new ValidationException("numeroContaDestino", "O número da conta de Destino informada não está presente no sistema.");
        }

        return transacao;
    }
}
