package br.com.bancodojoao.banco.listagem;

import br.com.bancodojoao.banco.model.Conta;

public record DadosListagemConta(String cpfCliente, Integer agenciaConta, Integer numeroConta) {


    public DadosListagemConta(Conta conta) {
        this(conta.getCliente().getCpf(),
                conta.getAgencia(),
                conta.getNumero());
    }

}
