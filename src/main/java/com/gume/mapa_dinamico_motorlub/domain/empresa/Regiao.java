package com.gume.mapa_dinamico_motorlub.domain.empresa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Regiao {
    NENHUMA(0, "Nenhuma região");

    private final Integer codigo;
    private final String descricao;
}
