package com.gume.mapa_dinamico_motorlub.entrypoint.mapper;

import com.gume.mapa_dinamico_motorlub.domain.Login;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.LoginDto;

public class LoginMapper {

    public static Login paraDomain(LoginDto dto) {
        return Login.builder()
                .cpf(dto.getCpf())
                .token(dto.getToken())
                .senha(dto.getSenha())
                .build();
    }

    public static LoginDto paraDto(LoginDto domain) {
        return LoginDto.builder()
                .cpf(domain.getCpf())
                .token(domain.getToken())
                .senha(domain.getSenha())
                .build();
    }
}
