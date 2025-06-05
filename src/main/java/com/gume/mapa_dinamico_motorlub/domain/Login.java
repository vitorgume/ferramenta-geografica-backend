package com.gume.mapa_dinamico_motorlub.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Login {
    private String cpf;
    private String senha;
    private String token;
}
