package br.com.bancodojoao.banco.listagem;

import br.com.bancodojoao.banco.model.Cliente;

import java.util.List;

public record DadosListagemCliente(String cpf, String nome, List<DadosListagemConta> contas) {

    public DadosListagemCliente(Cliente cliente) {
        this(cliente.getCpf(), cliente.getNome(), cliente.contasToDadosListagemConta());
    }
}
