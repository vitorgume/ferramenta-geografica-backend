package com.gume.mapa_dinamico_motorlub.application.exceptions;

public class RepresentanteNaoEncontradoException extends RuntimeException {
    public RepresentanteNaoEncontradoException() {
        super("Representante não encontrado.");
    }
}
