package com.donus.donusbackendchallenge.controller;

import com.donus.donusbackendchallenge.config.validation.ValidationException;
import com.donus.donusbackendchallenge.controller.dto.ContaDetalhadaDto;
import com.donus.donusbackendchallenge.controller.dto.ContaDto;
import com.donus.donusbackendchallenge.controller.form.ContaForm;
import com.donus.donusbackendchallenge.model.Conta;
import com.donus.donusbackendchallenge.repository.ContaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaRepository contaRepository;

    public ContaController(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @GetMapping
    public List<ContaDto> contas() {
        return ContaDto.converter(contaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<ContaDto> cadastrar(@RequestBody @Valid ContaForm contaForm, UriComponentsBuilder uriBuilder) throws ValidationException {
        var conta = contaForm.converter(contaRepository);

        if (contaRepository.existsByCpf(conta.getCpf())) {
            throw new ValidationException("cpf", "O CPF informado já cadastrado no sistema.");
        }

        contaRepository.save(conta);

        URI uri = uriBuilder.path("/conta/{id}").buildAndExpand(conta.getId()).toUri();
        return ResponseEntity.created(uri).body(new ContaDto(conta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDetalhadaDto> detalhes(@PathVariable("id") Long id) {
        Optional<Conta> conta = contaRepository.findById(id);

        return conta.map(value -> ResponseEntity.ok(new ContaDetalhadaDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ContaDto> apagarConta(@PathVariable("id") Long id) {
        var contaBanco = contaRepository.findById(id);

        if (contaBanco.isPresent()) {
            contaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
