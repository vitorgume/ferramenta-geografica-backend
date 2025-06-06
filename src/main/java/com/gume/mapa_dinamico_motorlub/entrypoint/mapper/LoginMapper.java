package com.gume.mapa_dinamico_motorlub.entrypoint.mapper;

import com.gume.mapa_dinamico_motorlub.domain.Login;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.LoginDto;

public class LoginMapper {

    public static Login paraDomain(LoginDto dto) {
        return Login.builder()
                .email(dto.getEmail())
                .token(dto.getToken())
                .senha(dto.getSenha())
                .build();
    }

    public static LoginDto paraDto(Login domain) {
        return LoginDto.builder()
                .email(domain.getEmail())
                .token(domain.getToken())
                .senha(domain.getSenha())
                .build();
    }
}
