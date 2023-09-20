package br.com.bancodojoao.banco.detalhamento;

import br.com.bancodojoao.banco.model.Conta;

public record DadosDetalhamentoConta(Long id, Integer agencia, Integer numero, String cpfTitular) {

    public DadosDetalhamentoConta(Conta conta) {
        this(conta.getId(), conta.getAgencia(), conta.getNumero(), conta.getCliente().getCpf());
    }
}
