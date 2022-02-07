package com.donus.donusbackendchallenge.controller;

import com.donus.donusbackendchallenge.config.validation.ValidationException;
import com.donus.donusbackendchallenge.controller.dto.DepositoDto;
import com.donus.donusbackendchallenge.controller.dto.TransacaoDetalhadaDto;
import com.donus.donusbackendchallenge.controller.dto.TransferenciaDto;
import com.donus.donusbackendchallenge.controller.form.DepositoForm;
import com.donus.donusbackendchallenge.controller.form.TransferenciaForm;
import com.donus.donusbackendchallenge.repository.ContaRepository;
import com.donus.donusbackendchallenge.repository.TransacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;

    public TransacaoController(ContaRepository contaRepository, TransacaoRepository transacaoRepository) {
        this.contaRepository = contaRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @PostMapping("/depositar")
    @Transactional
    public ResponseEntity<DepositoDto> depositar(@RequestBody @Valid DepositoForm form, UriComponentsBuilder uriBuilder) throws ValidationException {
        var transacao = form.converter(contaRepository);

        transacaoRepository.save(transacao);

        URI uri = uriBuilder.path("/transacao/{id}").buildAndExpand(transacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DepositoDto(transacao));
    }

    @PostMapping("/transferir")
    @Transactional
    public ResponseEntity<TransferenciaDto> depositar(@RequestBody @Valid TransferenciaForm form, UriComponentsBuilder uriBuilder) throws ValidationException {
        var transacao = form.converter(contaRepository);

        transacaoRepository.save(transacao);

        URI uri = uriBuilder.path("/transacao/{id}").buildAndExpand(transacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new TransferenciaDto(transacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoDetalhadaDto> detalhes(@PathVariable("id") Long id) {
       var transacao = transacaoRepository.findById(id);

        return transacao.map(value -> ResponseEntity.ok(new TransacaoDetalhadaDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
