package com.gume.mapa_dinamico_motorlub.entrypoint.mapper;

import com.gume.mapa_dinamico_motorlub.domain.Login;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.LoginDto;

public class LoginMapper {

    public static Login paraDomain(LoginDto dto) {
        return Login.builder()
                .telefone(dto.getTelefone())
                .token(dto.getToken())
                .senha(dto.getSenha())
                .build();
    }

    public static LoginDto paraDto(Login domain) {
        return LoginDto.builder()
                .telefone(domain.getTelefone())
                .token(domain.getToken())
                .senha(domain.getSenha())
                .idRepresentante(domain.getIdRepresentante())
                .build();
    }
}
