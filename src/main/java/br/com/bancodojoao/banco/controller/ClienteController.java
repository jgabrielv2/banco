package br.com.bancodojoao.banco.controller;

import br.com.bancodojoao.banco.detalhamento.DadosDetalhamentoCliente;
import br.com.bancodojoao.banco.formulario.atualizacao.DadosAtualizacaoCliente;
import br.com.bancodojoao.banco.formulario.criacao.DadosCriacaoCliente;
import br.com.bancodojoao.banco.listagem.DadosListagemCliente;
import br.com.bancodojoao.banco.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoCliente> salvar(@RequestBody DadosCriacaoCliente dadosCriacaoCliente, UriComponentsBuilder uriComponentsBuilder) {
        var cliente = clienteService.salvar(dadosCriacaoCliente);
        var uri = uriComponentsBuilder.path("/clientes/{id}")
                .buildAndExpand(cliente.id()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosListagemCliente> buscarPorId(@PathVariable Long id) {
        var cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemCliente>> buscarTodosAtivos() {
        return ResponseEntity.ok(clienteService.buscarTodosAtivos());
    }

    @GetMapping("inativos")
    public ResponseEntity<List<DadosListagemCliente>> buscarTodosInativos() {
        return ResponseEntity.ok(clienteService.buscarTodosInativos());
    }

    @GetMapping("todos")
    public ResponseEntity<List<DadosListagemCliente>> buscarTodos() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @PutMapping("{id}")
    public ResponseEntity<DadosDetalhamentoCliente> atualizarCliente(@PathVariable Long id, @RequestBody DadosAtualizacaoCliente dadosAtualizacaoCliente) {
        return ResponseEntity.ok(clienteService.atualizar(id, dadosAtualizacaoCliente));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
