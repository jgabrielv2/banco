package br.com.bancodojoao.banco.operacao;

import java.math.BigDecimal;

public record DadosSaque(String cpfTitular, String agencia, String numeroConta, BigDecimal valor) {


}
