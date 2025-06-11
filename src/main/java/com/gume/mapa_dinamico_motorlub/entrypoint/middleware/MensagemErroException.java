package com.gume.mapa_dinamico_motorlub.entrypoint.middleware;

import org.springframework.http.HttpStatus;

public record MensagemErroException(HttpStatus status, String message) {}
