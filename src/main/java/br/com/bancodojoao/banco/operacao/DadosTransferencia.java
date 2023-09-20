package br.com.bancodojoao.banco.operacao;

import java.math.BigDecimal;

public record DadosTransferencia(String cpfTitularOrigem, String agenciaOrigem, String numeroContaOrigem,
                                 String cpfTitularDestino, String agenciaDestino, String numeroContaDestino,
                                 BigDecimal valor) {

}
