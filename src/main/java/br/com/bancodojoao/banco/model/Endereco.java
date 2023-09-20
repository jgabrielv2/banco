package br.com.bancodojoao.banco.model;

import br.com.bancodojoao.banco.formulario.criacao.DadosCriacaoEndereco;
import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {

    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;

    public Endereco(){

    }
    public Endereco(DadosCriacaoEndereco dadosCriacaoEndereco){
        this.setCep(dadosCriacaoEndereco.cep())
                .setLogradouro(dadosCriacaoEndereco.logradouro())
                .setBairro(dadosCriacaoEndereco.bairro())
                .setCidade(dadosCriacaoEndereco.cidade())
                .setEstado(dadosCriacaoEndereco.estado())
                .setComplemento(dadosCriacaoEndereco.complemento());
    }
    public String getCep() {
        return cep;
    }

    public Endereco setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Endereco setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public Endereco setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public Endereco setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getEstado() {
        return estado;
    }

    public Endereco setEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public String getComplemento() {
        return complemento;
    }

    public Endereco setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }
}
