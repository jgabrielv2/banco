package br.com.bancodojoao.banco.controller;

import br.com.bancodojoao.banco.operacao.ComprovanteDeposito;
import br.com.bancodojoao.banco.operacao.DadosDeposito;
import br.com.bancodojoao.banco.service.OperacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("operacao")
public class OperacaoController {

    private final OperacaoService operacaoService;

    public OperacaoController(OperacaoService operacaoService) {
        this.operacaoService = operacaoService;
    }

    @PostMapping("deposito")
    public ResponseEntity<ComprovanteDeposito> depositar(@RequestBody DadosDeposito dadosDeposito) {

        ComprovanteDeposito comprovanteDeposito = operacaoService.depositar(dadosDeposito);

        return ResponseEntity.ok(comprovanteDeposito);
    }

}
