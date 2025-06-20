package com.gume.mapa_dinamico_motorlub.infrastructure.mapper;

import com.gume.mapa_dinamico_motorlub.domain.Quadro;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.QuadroEntity;

public class QuadroMapper {

    public static Quadro paraDomain(QuadroEntity entity) {
        return Quadro.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .representante(RepresentanteMapper.paraDomain(entity.getRepresentante()))
                .build();
    }

    public static QuadroEntity paraEntity(Quadro domain) {
        return QuadroEntity.builder()
                .id(domain.getId())
                .titulo(domain.getTitulo())
                .representante(RepresentanteMapper.paraEntity(domain.getRepresentante()))
                .build();
    }
}
