package com.gume.mapa_dinamico_motorlub.infrastructure.mapper;

import com.gume.mapa_dinamico_motorlub.domain.Empresa;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.EmpresaEntity;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.RepresentanteEntity;

public class EmpresaMapper {

    public static Empresa paraDomain(EmpresaEntity entity) {
        return Empresa.builder()
                .id(entity.getId())
                .nomeFantasia(entity.getNomeFantasia())
                .razaoSocial(entity.getRazaoSocial())
                .cnpj(entity.getCnpj())
                .telefone(entity.getTelefone())
                .email(entity.getEmail())
                .endereco(EnderecoMapper.paraDomain(entity.getEndereco()))
                .segmento(entity.getSegmento())
                .visitado(entity.getVisitado())
                .representante(RepresentanteMapper.paraDomain(entity.getRepresentante()))
                .comentario(entity.getComentario())
                .build();
    }

    public static EmpresaEntity paraEntity(Empresa domain) {
        return EmpresaEntity.builder()
                .id(domain.getId())
                .nomeFantasia(domain.getNomeFantasia())
                .razaoSocial(domain.getRazaoSocial())
                .cnpj(domain.getCnpj())
                .telefone(domain.getTelefone())
                .email(domain.getEmail())
                .endereco(EnderecoMapper.paraEntity(domain.getEndereco()))
                .segmento(domain.getSegmento())
                .visitado(domain.getVisitado())
                .representante(RepresentanteMapper.paraEntity(domain.getRepresentante()))
                .comentario(domain.getComentario())
                .build();
    }
}
