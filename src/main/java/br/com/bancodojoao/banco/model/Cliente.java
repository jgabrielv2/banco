package br.com.bancodojoao.banco.model;

import br.com.bancodojoao.banco.detalhamento.DadosDetalhamentoCliente;
import br.com.bancodojoao.banco.formulario.criacao.DadosCriacaoCliente;
import br.com.bancodojoao.banco.listagem.DadosListagemCliente;
import br.com.bancodojoao.banco.listagem.DadosListagemConta;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;

    private Boolean ativo;
    @OneToMany(cascade = CascadeType.ALL)
    private final List<Conta> contas = new ArrayList<>();

    @Embedded
    private Endereco endereco;

    public Cliente(DadosCriacaoCliente dadosCriacaoCliente) {
        setNome(dadosCriacaoCliente.nome())
                .setCpf(dadosCriacaoCliente.cpf())
                .setEndereco(new Endereco(dadosCriacaoCliente.dadosCriacaoEndereco()))
                .setAtivo(true);
        var conta = new Conta(dadosCriacaoCliente.dadosCriacaoConta());
        conta.setCliente(this);
        contas.add(conta);
    }

    public Cliente() {

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Cliente setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public Cliente setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public Boolean isAtivo() {
        return this.ativo;
    }

    public Cliente setAtivo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Cliente setEndereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }

    public DadosListagemCliente toDadosListagem() {
        return new DadosListagemCliente(this);
    }

    public DadosDetalhamentoCliente toDadosDetalhamento() {
        return new DadosDetalhamentoCliente(this);
    }

    public List<DadosListagemConta> contasToDadosListagemConta() {
        return this.getContas().stream().map(DadosListagemConta::new).toList();

    }
}
