package com.gume.mapa_dinamico_motorlub.application.exceptions;

public class ComentarioNaoEncontrado extends RuntimeException {
    public ComentarioNaoEncontrado() {
        super("Comentário não encontrado.");
    }
}
