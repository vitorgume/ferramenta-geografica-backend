package com.gume.mapa_dinamico_motorlub.application.exceptions;

public class QuadroNaoEncontradoException extends RuntimeException {

    public QuadroNaoEncontradoException() {
        super("Quadro não encontrado pelo seu id.");
    }
}
