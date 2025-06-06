package com.gume.mapa_dinamico_motorlub.application.exceptions;

public class CredenciasIncorretasException extends RuntimeException {
    public CredenciasIncorretasException() {
        super("Credencias de representante incorretas.");
    }
}
