package br.com.bancodojoao.banco.service;

import br.com.bancodojoao.banco.detalhamento.DadosDetalhamentoConta;
import br.com.bancodojoao.banco.formulario.criacao.DadosCriacaoConta;
import br.com.bancodojoao.banco.listagem.DadosListagemConta;
import br.com.bancodojoao.banco.model.Conta;
import br.com.bancodojoao.banco.repository.ClienteRepository;
import br.com.bancodojoao.banco.repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    public DadosDetalhamentoConta salvar(DadosCriacaoConta dadosCriacaoConta) {
        Conta conta = new Conta(dadosCriacaoConta);
        var cliente = clienteRepository.findByCpf(dadosCriacaoConta.cpfCliente()).orElseThrow(EntityNotFoundException::new);
        conta.setCliente(cliente);
        cliente.getContas().add(conta);
        contaRepository.save(conta);
        return new DadosDetalhamentoConta(conta);
    }

    public List<DadosListagemConta> listar() {
        return contaRepository.findAll().stream().map(DadosListagemConta::new).toList();
    }

    public DadosListagemConta buscarPorId(Long id) {
        Conta conta = contaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new DadosListagemConta(conta);
    }

    public void excluir(Long id) {
        Conta conta = contaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        conta.excluir();
    }

}
