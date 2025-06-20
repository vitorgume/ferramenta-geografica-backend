package com.gume.mapa_dinamico_motorlub.application.exceptions;

public class QuadroPadraoNaoEncontradoException extends RuntimeException {

    public QuadroPadraoNaoEncontradoException() {
        super("Quadro padrão de representante não encontrado.");
    }
}
