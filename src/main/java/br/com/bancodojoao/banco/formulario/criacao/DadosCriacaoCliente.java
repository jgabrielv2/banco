package br.com.bancodojoao.banco.formulario.criacao;

public record DadosCriacaoCliente(
        String nome,
        String cpf,
        DadosCriacaoEndereco dadosCriacaoEndereco,
        DadosCriacaoConta dadosCriacaoConta) {
}
