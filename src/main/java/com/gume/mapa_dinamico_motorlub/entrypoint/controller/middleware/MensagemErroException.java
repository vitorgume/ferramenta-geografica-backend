package com.gume.mapa_dinamico_motorlub.entrypoint.controller.middleware;

import org.springframework.http.HttpStatus;

public record MensagemErroException(HttpStatus status, String message) {}
