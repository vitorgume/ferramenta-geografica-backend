package com.gume.mapa_dinamico_motorlub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NivelIcp {
    A_MAIS(0, "A+"),
    B_MAIS(1, "B+"),
    C_MAIS(2, "C+"),
    A(3, "A"),
    B(4, "B"),
    C(5, "C"),
    A_MENOS(6, "A-"),
    B_MENOS(7, "B-"),
    C_MENOS(8, "C-");

    private final Integer codigo;
    private final String descricao;
}
