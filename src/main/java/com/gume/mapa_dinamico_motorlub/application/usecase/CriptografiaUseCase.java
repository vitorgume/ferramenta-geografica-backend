package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.gateways.CriptografiaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriptografiaUseCase {

    private final CriptografiaGateway gateway;

    public String criptografar(String senha) {
        return gateway.criptografar(senha);
    }

    public boolean validaSenha(String senha, String senhaRepresentante) {
        return gateway.validarSenha(senha, senhaRepresentante);
    }
}
