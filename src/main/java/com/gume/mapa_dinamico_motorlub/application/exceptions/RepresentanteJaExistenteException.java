package com.gume.mapa_dinamico_motorlub.application.exceptions;

public class RepresentanteJaExistenteException extends RuntimeException {
    public RepresentanteJaExistenteException() {
        super("Representatnte já cadastrado com o email solicitado.");
    }
}
