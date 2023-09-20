package br.com.bancodojoao.banco.detalhamento;

import br.com.bancodojoao.banco.model.Cliente;

public record DadosDetalhamentoCliente(
        Long id,
        String nome, String cpf,
        DadosDetalhamentoEndereco dadosDetalhamentoEndereco) {

    public DadosDetalhamentoCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getCpf(), new DadosDetalhamentoEndereco(cliente.getEndereco()));
    }
}
