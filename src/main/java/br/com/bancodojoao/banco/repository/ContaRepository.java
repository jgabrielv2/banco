package br.com.bancodojoao.banco.repository;

import br.com.bancodojoao.banco.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByAgenciaAndNumero(Integer agencia, Integer numero);


}
