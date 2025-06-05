package com.gume.mapa_dinamico_motorlub.infrastructure.mapper;

import com.gume.mapa_dinamico_motorlub.domain.Representante;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.RepresentanteEntity;

public class RepresentanteMapper {
    public static Representante paraDomain(RepresentanteEntity entity) {
        return Representante.builder()
                .id(entity.getId())
                .cpf(entity.getCpf())
                .nome(entity.getNome())
                .regiao(entity.getRegiao())
                .build();
    }

    public static RepresentanteEntity paraEntity(Representante domain) {
        return RepresentanteEntity.builder()
                .id(domain.getId())
                .cpf(domain.getCpf())
                .nome(domain.getNome())
                .regiao(domain.getRegiao())
                .build();
    }
}
