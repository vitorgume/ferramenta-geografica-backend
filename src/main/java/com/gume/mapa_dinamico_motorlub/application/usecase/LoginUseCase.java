package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.exceptions.CredenciasIncorretasException;
import com.gume.mapa_dinamico_motorlub.application.gateways.LoginGateway;
import com.gume.mapa_dinamico_motorlub.domain.Login;
import com.gume.mapa_dinamico_motorlub.domain.Representante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase {

    private final RepresentanteUseCase representanteUseCase;
    private final LoginGateway gateway;
    private final CriptografiaUseCase criptografiaUseCase;

    public Login autenticar(Login login) {
        Representante representante = representanteUseCase.consultarPorEmail(login.getEmail());
        validaCredencias(representante, login.getEmail(), login.getSenha());
        String token = gateway.generateToken(login.getEmail());

        return Login.builder()
                .token(token)
                .idRepresentante(representante.getId())
                .build();
    }


    private void validaCredencias(Representante representante, String email, String senha) {
        if(!representante.getEmail().equals(email) || !criptografiaUseCase.validaSenha(senha, representante.getSenha())) {
            throw new CredenciasIncorretasException();
        }
    }
}
