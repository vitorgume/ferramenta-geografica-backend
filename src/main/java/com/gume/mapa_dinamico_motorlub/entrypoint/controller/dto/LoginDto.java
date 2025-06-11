package com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class LoginDto {
    private String telefone;
    private String senha;
    private String token;
    private Long idRepresentante;
}
