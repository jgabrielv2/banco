package br.com.bancodojoao.banco.exception;

public class DadosInvalidosException extends RuntimeException {
    public DadosInvalidosException(String s) {
        super((s));
    }
}
