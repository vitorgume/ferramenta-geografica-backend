package com.gume.mapa_dinamico_motorlub.entrypoint.middleware;

import com.gume.mapa_dinamico_motorlub.application.exceptions.CredenciasIncorretasException;
import com.gume.mapa_dinamico_motorlub.application.exceptions.RepresentanteJaExistenteException;
import com.gume.mapa_dinamico_motorlub.application.exceptions.RepresentanteNaoEncontradoException;
import com.gume.mapa_dinamico_motorlub.infrastructure.exceptions.DataProviderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerMiddleware {

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
}
