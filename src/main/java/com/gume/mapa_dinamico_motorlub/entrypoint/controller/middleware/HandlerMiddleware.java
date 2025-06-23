package com.gume.mapa_dinamico_motorlub.entrypoint.controller.middleware;

import com.gume.mapa_dinamico_motorlub.application.exceptions.*;
import com.gume.mapa_dinamico_motorlub.infrastructure.exceptions.DataProviderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerMiddleware {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensagemErroException> exceptionInesperada(Exception exception) {
        exception.printStackTrace();
        MensagemErroException mensagem = new MensagemErroException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(CredenciasIncorretasException.class)
    public ResponseEntity<MensagemErroException> credenciasErradas(CredenciasIncorretasException exception) {
        MensagemErroException mensagem = new MensagemErroException(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(RepresentanteJaExistenteException.class)
    public ResponseEntity<MensagemErroException> representanteJaCadastrado(RepresentanteJaExistenteException exception) {
        MensagemErroException mensagem = new MensagemErroException(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(RepresentanteNaoEncontradoException.class)
    public ResponseEntity<MensagemErroException> representanteNaoEncontrado(RepresentanteNaoEncontradoException exception) {
        MensagemErroException mensagem = new MensagemErroException(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(DataProviderException.class)
    public ResponseEntity<MensagemErroException> erroDataProvider(DataProviderException exception) {
        MensagemErroException mensagem = new MensagemErroException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(ComentarioNaoEncontrado.class)
    public ResponseEntity<MensagemErroException> comentarioNaoEncontrado(ComentarioNaoEncontrado exception) {
        MensagemErroException mensagem = new MensagemErroException(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(QuadroNaoEncontradoException.class)
    public ResponseEntity<MensagemErroException> quadroNaoEncontrado(QuadroNaoEncontradoException exception) {
        MensagemErroException mensagem = new MensagemErroException(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(QuadroPadraoNaoEncontradoException.class)
    public ResponseEntity<MensagemErroException> quadroPadraoNaoEncontrado(QuadroPadraoNaoEncontradoException exception) {
        MensagemErroException mensagem = new MensagemErroException(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }
}
