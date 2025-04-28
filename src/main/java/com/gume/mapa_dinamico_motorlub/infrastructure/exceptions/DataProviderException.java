package com.gume.mapa_dinamico_motorlub.infrastructure.exceptions;

public class DataProviderException extends RuntimeException {
    public DataProviderException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
