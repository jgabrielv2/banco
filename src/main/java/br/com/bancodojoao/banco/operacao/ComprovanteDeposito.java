package br.com.bancodojoao.banco.operacao;

import java.math.BigDecimal;

public record ComprovanteDeposito(BigDecimal valor, String cpfTitular, String agencia, String conta) {

    public ComprovanteDeposito(DadosDeposito dados){
        this(dados.valor(), dados.cpfTitular(), dados.agencia(), dados.numeroConta());
    }
}
