package br.com.bancodojoao.banco.service;

import br.com.bancodojoao.banco.detalhamento.DadosDetalhamentoCliente;
import br.com.bancodojoao.banco.formulario.atualizacao.DadosAtualizacaoCliente;
import br.com.bancodojoao.banco.formulario.atualizacao.DadosAtualizacaoEndereco;
import br.com.bancodojoao.banco.formulario.criacao.DadosCriacaoCliente;
import br.com.bancodojoao.banco.listagem.DadosListagemCliente;
import br.com.bancodojoao.banco.model.Cliente;
import br.com.bancodojoao.banco.model.Endereco;
import br.com.bancodojoao.banco.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public DadosDetalhamentoCliente salvar(DadosCriacaoCliente dadosCriacaoCliente) {
        Cliente cliente = new Cliente(dadosCriacaoCliente);
        return clienteRepository.save(cliente).toDadosDetalhamento();

    }

    public List<DadosListagemCliente> buscarTodos() {
        return clienteRepository.findAll().stream().map(DadosListagemCliente::new).toList();
    }

    public List<DadosListagemCliente> buscarTodosAtivos() {
        return clienteRepository.findAllByAtivoTrue().stream().map(DadosListagemCliente::new).toList();
    }

    public List<DadosListagemCliente> buscarTodosInativos() {
        return clienteRepository.findAllByAtivoFalse().stream().map(DadosListagemCliente::new).toList();
    }

    public DadosListagemCliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(EntityNotFoundException::new).toDadosListagem();
    }

    public DadosDetalhamentoCliente atualizar(Long id, DadosAtualizacaoCliente dadosAtualizacaoCliente) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (dadosAtualizacaoCliente.nome() != null) {
            cliente.setNome(dadosAtualizacaoCliente.nome());
        }

        if (dadosAtualizacaoCliente.dadosAtualizacaoEndereco() != null) {


            Endereco endereco = cliente.getEndereco();
            DadosAtualizacaoEndereco atualizacaoEndereco = dadosAtualizacaoCliente.dadosAtualizacaoEndereco();


            if (atualizacaoEndereco.cep() != null) {
                endereco.setCep(atualizacaoEndereco.cep());
            }
            if (atualizacaoEndereco.logradouro() != null) {
                endereco.setLogradouro(atualizacaoEndereco.logradouro());
            }
            if (atualizacaoEndereco.bairro() != null) {
                endereco.setBairro(atualizacaoEndereco.bairro());
            }
            if (atualizacaoEndereco.cidade() != null) {
                endereco.setCidade(atualizacaoEndereco.cidade());
            }
            if (atualizacaoEndereco.estado() != null) {
                endereco.setEstado(atualizacaoEndereco.estado());
            }
            if (atualizacaoEndereco.complemento() != null) {
                endereco.setComplemento(atualizacaoEndereco.complemento());
            }

            cliente.setEndereco(endereco);
        }
        return clienteRepository.save(cliente).toDadosDetalhamento();

    }

    public void excluir(Long id) {

       Cliente cliente = clienteRepository.findById(id).orElseThrow(EntityNotFoundException::new);
       cliente.setAtivo(false);
       cliente.getContas().forEach(conta -> conta.setAtivo(false));
    }


}
