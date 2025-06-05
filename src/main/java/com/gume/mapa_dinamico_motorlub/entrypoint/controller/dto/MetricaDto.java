package com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class MetricaDto {
    private Integer quantidadeVisitados;
    private Integer quantidadeNaoVisitados;
}
