package com.gume.mapa_dinamico_motorlub.entrypoint.mapper;

import com.gume.mapa_dinamico_motorlub.domain.Quadro;
import com.gume.mapa_dinamico_motorlub.domain.Representante;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.QuadroDto;

public class QuadroMapper {

    public static Quadro paraDomain(QuadroDto dto) {
        return Quadro.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .representante(Representante.builder()
                        .id(dto.getRepresentante().getId())
                        .build()
                )
                .build();
    }

    public static QuadroDto paraDto(Quadro domain) {
        return QuadroDto.builder()
                .id(domain.getId())
                .titulo(domain.getTitulo())
                .representante(RepresentanteMapper.paraDto(domain.getRepresentante()))
                .build();
    }
}
