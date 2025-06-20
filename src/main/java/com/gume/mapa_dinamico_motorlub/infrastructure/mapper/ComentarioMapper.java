package com.gume.mapa_dinamico_motorlub.infrastructure.mapper;

import com.gume.mapa_dinamico_motorlub.domain.empresa.Comentario;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.ComentarioEntity;

import java.util.List;

public class ComentarioMapper {

    public static Comentario paraDomain(ComentarioEntity entity) {
        return Comentario.builder()
                .id(entity.getId())
                .conteudo(entity.getConteudo())
                .dataCriacao(entity.getDataCriacao())
                .empresa(EmpresaMapper.paraDomain(entity.getEmpresa()))
                .build();
    }

    public static ComentarioEntity paraEntity(Comentario domain) {
        return ComentarioEntity.builder()
                .id(domain.getId())
                .conteudo(domain.getConteudo())
                .dataCriacao(domain.getDataCriacao())
                .empresa(EmpresaMapper.paraEntity(domain.getEmpresa()))
                .build();
    }

    public static List<Comentario> paraDomains(List<ComentarioEntity> entities) {
        return entities.stream().map(ComentarioMapper::paraDomain).toList();
    }

    public static List<ComentarioEntity> paraEntities(List<Comentario> domains) {
        return domains.stream().map(ComentarioMapper::paraEntity).toList();
    }
}
