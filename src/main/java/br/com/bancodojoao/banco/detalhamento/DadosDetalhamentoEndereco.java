package br.com.bancodojoao.banco.detalhamento;

import br.com.bancodojoao.banco.model.Endereco;

public record DadosDetalhamentoEndereco(String cep, String logradouro, String bairro, String cidade, String estado,
                                        String complemento) {
    public DadosDetalhamentoEndereco(Endereco endereco) {
        this(
                endereco.getCep(), endereco.getLogradouro(),
                endereco.getBairro(), endereco.getCidade(),
                endereco.getEstado(), endereco.getComplemento());
    }
}
