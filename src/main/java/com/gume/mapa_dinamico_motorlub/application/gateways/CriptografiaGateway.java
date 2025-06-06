package com.gume.mapa_dinamico_motorlub.application.gateways;

public interface CriptografiaGateway {
    String criptografar(String senha);

    boolean validarSenha(String senha, String senhaRepresentante);
}
