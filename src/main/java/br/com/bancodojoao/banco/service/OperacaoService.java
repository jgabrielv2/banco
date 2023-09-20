package br.com.bancodojoao.banco.service;

import br.com.bancodojoao.banco.exception.ClienteInativoException;
import br.com.bancodojoao.banco.exception.ContaInativaException;
import br.com.bancodojoao.banco.exception.DadosInvalidosException;
import br.com.bancodojoao.banco.exception.ValorInvalidoException;
import br.com.bancodojoao.banco.model.Cliente;
import br.com.bancodojoao.banco.model.Conta;
import br.com.bancodojoao.banco.operacao.*;
import br.com.bancodojoao.banco.repository.ClienteRepository;
import br.com.bancodojoao.banco.repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OperacaoService {


    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;

    public OperacaoService(ClienteRepository clienteRepository,
                           ContaRepository contaRepository) {
        this.clienteRepository = clienteRepository;
        this.contaRepository = contaRepository;
    }

    @Transactional
    public ComprovanteDeposito depositar(DadosDeposito dadosDeposito) {

        Cliente cliente = clienteRepository.findByCpf(dadosDeposito.cpfTitular()).orElseThrow(EntityNotFoundException::new);
        Conta conta = contaRepository.findByAgenciaAndNumero
                        (Integer.parseInt(dadosDeposito.agencia()), Integer.parseInt(dadosDeposito.numeroConta()))
                .orElseThrow(EntityNotFoundException::new);

        var valor = dadosDeposito.valor();
        depositar(cliente, conta, valor);
        contaRepository.save(conta);
        return new ComprovanteDeposito(dadosDeposito);
    }

    @Transactional
    public void sacar(DadosSaque dadosSaque) {
        Cliente cliente = clienteRepository.findByCpf(dadosSaque.cpfTitular()).orElseThrow(EntityNotFoundException::new);
        Conta conta = contaRepository.findByAgenciaAndNumero
                        (Integer.parseInt(dadosSaque.agencia()), Integer.parseInt(dadosSaque.numeroConta()))
                .orElseThrow(EntityNotFoundException::new);

        var valor = dadosSaque.valor();

        sacar(cliente, conta, valor);
    }

    @Transactional
    public ComprovanteTransferencia transferir(DadosTransferencia dadosTransferencia) {
        Cliente clienteOrigem = clienteRepository.findByCpf(dadosTransferencia.cpfTitularOrigem()).orElseThrow(EntityNotFoundException::new);
        Conta contaOrigem = contaRepository.findByAgenciaAndNumero
                        (Integer.parseInt(dadosTransferencia.numeroContaOrigem()), Integer.parseInt(dadosTransferencia.agenciaOrigem()))
                .orElseThrow(EntityNotFoundException::new);

        Cliente clienteDestino = clienteRepository.findByCpf(dadosTransferencia.cpfTitularDestino()).orElseThrow(EntityNotFoundException::new);
        Conta contaDestino = contaRepository.findByAgenciaAndNumero
                        (Integer.parseInt(dadosTransferencia.numeroContaDestino()), Integer.parseInt(dadosTransferencia.agenciaDestino()))
                .orElseThrow(EntityNotFoundException::new);

        var valor = dadosTransferencia.valor();

        sacar(clienteOrigem, contaOrigem, valor);
        depositar(clienteDestino, contaDestino, valor);

        return new ComprovanteTransferencia(valor, contaOrigem, contaDestino);
    }

    private void depositar(Cliente cliente, Conta conta, BigDecimal valor) {
        validaPreenchimento(cliente, conta, valor);

        conta.depositar(valor);

    }

    private void sacar(Cliente cliente, Conta conta, BigDecimal valor) {
        validaPreenchimento(cliente, conta, valor);

        conta.saca(valor);

    }

    private void validaPreenchimento(Cliente cliente, Conta conta, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("Valor inválido!");
        }

        if (!cliente.getCpf().equals(conta.getCliente().getCpf())) {
            throw new DadosInvalidosException("Dados inválidos!");
        }

        if (!conta.isAtivo()) {
            throw new ContaInativaException("Conta inativa!");
        }

        if (!cliente.isAtivo()) {
            throw new ClienteInativoException("Cliente inativo!");
        }
    }

}
