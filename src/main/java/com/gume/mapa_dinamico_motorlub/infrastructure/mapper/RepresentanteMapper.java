package com.gume.mapa_dinamico_motorlub.infrastructure.mapper;

import com.gume.mapa_dinamico_motorlub.domain.Representante;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.RepresentanteEntity;

public class RepresentanteMapper {
    public static Representante paraDomain(RepresentanteEntity entity) {
        return Representante.builder()
                .id(entity.getId())
                .telefone(entity.getTelefone())
                .nome(entity.getNome())
                .regiao(entity.getRegiao())
                .senha(entity.getSenha())
                .build();
    }

    public static RepresentanteEntity paraEntity(Representante domain) {
        return RepresentanteEntity.builder()
                .id(domain.getId())
                .telefone(domain.getTelefone())
                .nome(domain.getNome())
                .regiao(domain.getRegiao())
                .senha(domain.getSenha())
                .build();
    }
}
