package com.gume.mapa_dinamico_motorlub.entrypoint.controller;

import com.gume.mapa_dinamico_motorlub.domain.Metrica;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.MetricaDto;

public class MetricaMapper {

    public static MetricaDto paraDto(Metrica domain) {
        return MetricaDto.builder()
                .quantidadeNaoVisitados(domain.getQuantidadeNaoVisitado())
                .quantidadeVisitados(domain.getQuantidadeVisitado())
                .build();
    }
}
