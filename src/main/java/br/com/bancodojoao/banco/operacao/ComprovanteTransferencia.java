package br.com.bancodojoao.banco.operacao;

import br.com.bancodojoao.banco.model.Conta;

import java.math.BigDecimal;

public record ComprovanteTransferencia(String cpfOrigem, String agenciaOrigem,
                                       String contaOrigem, BigDecimal valor,
                                       String cpfDestino, String agenciaDestino,
                                       String contaDestino) {

    public ComprovanteTransferencia(BigDecimal valor, Conta origem, Conta destino) {
        this(origem.getCliente().getCpf(), origem.getAgencia().toString(), origem.getNumero().toString(), valor, destino.getCliente().getCpf(), destino.getAgencia().toString(), destino.getNumero().toString());
    }
}
