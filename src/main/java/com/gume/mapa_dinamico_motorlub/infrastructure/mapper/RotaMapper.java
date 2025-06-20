package com.gume.mapa_dinamico_motorlub.infrastructure.mapper;

import com.gume.mapa_dinamico_motorlub.domain.empresa.Rota;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.RotaEntity;

public class RotaMapper {

    public static Rota paraDomain(RotaEntity entity) {
        return Rota.builder()
                .id(entity.getId())
                .dataCriacao(entity.getDataCriacao())
                .enderecos(entity.getEnderecos().stream().map(EnderecoMapper::paraDomain).toList())
                .concluida(entity.getConcluida())
                .build();
    }

    public static RotaEntity paraEntity(Rota domain) {
        return RotaEntity.builder()
                .id(domain.getId())
                .dataCriacao(domain.getDataCriacao())
                .enderecos(domain.getEnderecos().stream().map(EnderecoMapper::paraEntity).toList())
                .concluida(domain.getConcluida())
                .build();
    }
}
