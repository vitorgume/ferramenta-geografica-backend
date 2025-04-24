package com.gume.mapa_dinamico_motorlub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Segmento {
    OFICINA_MECANICA(0, "Oficina Mecânica"),
    TROCA_OLEO(1, "Troca de oléo");

    private final Integer codigo;
    private final String descricao;
}
