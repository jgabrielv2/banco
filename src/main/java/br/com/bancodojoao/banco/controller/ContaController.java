package br.com.bancodojoao.banco.controller;


import br.com.bancodojoao.banco.detalhamento.DadosDetalhamentoConta;
import br.com.bancodojoao.banco.formulario.criacao.DadosCriacaoConta;
import br.com.bancodojoao.banco.listagem.DadosListagemConta;
import br.com.bancodojoao.banco.service.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("conta")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService){
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoConta> salvar(@RequestBody DadosCriacaoConta dados, UriComponentsBuilder uriComponentsBuilder){
        var dadosDetalhamentoConta = contaService.salvar(dados);
        var uri =uriComponentsBuilder.path("/conta/{id}")
                .buildAndExpand(dadosDetalhamentoConta.id()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamentoConta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemConta> buscarPorId(@PathVariable Long id){
        DadosListagemConta dadosListagemConta = contaService.buscarPorId(id);
        return ResponseEntity.ok(dadosListagemConta);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemConta>> buscarTodos(){
        List<DadosListagemConta> listagemContas = contaService.listar();
        return ResponseEntity.ok(listagemContas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        contaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
