package br.com.bancodojoao.banco.repository;

import br.com.bancodojoao.banco.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findAllByAtivoTrue();

    List<Cliente> findAllByAtivoFalse();

    Optional<Cliente> findByCpf(String cpf);

//    Cliente findByCpf();


}
