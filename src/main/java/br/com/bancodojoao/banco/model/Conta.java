package br.com.bancodojoao.banco.model;

import br.com.bancodojoao.banco.formulario.criacao.DadosCriacaoConta;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer agencia;
    private Integer numero;
    private BigDecimal saldo;

    private Boolean ativo;
    @ManyToOne
    private Cliente cliente;

    public Conta() {

    }

    public Conta(DadosCriacaoConta dadosCriacaoConta) {
        this.setAgencia(Integer.parseInt(dadosCriacaoConta.agencia()))
                .setNumero(Integer.parseInt(dadosCriacaoConta.numero()))
                .setAtivo(true);
    }

    public Long getId() {
        return id;
    }

    public Conta setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public Conta setAgencia(Integer agencia) {
        this.agencia = agencia;
        return this;
    }

    public Integer getNumero() {
        return numero;
    }

    public Conta setNumero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Conta setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
        return this;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Conta setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public void depositar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean isAtivo() {
        return this.ativo;
    }

    public void excluir() {
        this.ativo = false;
    }

    public void saca(BigDecimal valor) {
        this.saldo = this.saldo.subtract(valor);
    }
}
