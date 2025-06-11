package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.exceptions.CredenciasIncorretasException;
import com.gume.mapa_dinamico_motorlub.application.gateways.LoginGateway;
import com.gume.mapa_dinamico_motorlub.domain.Login;
import com.gume.mapa_dinamico_motorlub.domain.Representante;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginUseCase {

    private final RepresentanteUseCase representanteUseCase;
    private final LoginGateway gateway;
    private final CriptografiaUseCase criptografiaUseCase;

    public Login autenticar(Login login) {
        log.info("Autenticando representante. Dados login: {}", login);
        Representante representante = representanteUseCase.consultarPorTelefone(login.getTelefone());
        validaCredencias(representante, login.getTelefone(), login.getSenha());
        String token = gateway.generateToken(login.getTelefone());

        log.info("Representante autenticado com sucesso. Representante: {}", representante);

        return Login.builder()
                .token(token)
                .idRepresentante(representante.getId())
                .build();
    }


    private void validaCredencias(Representante representante, String email, String senha) {
        if(!representante.getTelefone().equals(email) || !criptografiaUseCase.validaSenha(senha, representante.getSenha())) {
            throw new CredenciasIncorretasException();
        }
    }
}
