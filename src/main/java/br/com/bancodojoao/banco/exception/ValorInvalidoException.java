package br.com.bancodojoao.banco.exception;

public class ValorInvalidoException extends RuntimeException {
    public ValorInvalidoException(String s) {
        super((s));
    }
}
