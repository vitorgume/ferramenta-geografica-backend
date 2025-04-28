package com.gume.mapa_dinamico_motorlub.infrastructure.mapper;

import com.gume.mapa_dinamico_motorlub.domain.Empresa;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.EmpresaEntity;

public class EmpresaMapper {

    public static Empresa paraDomain(EmpresaEntity entity) {
        return Empresa.builder()
                .id(entity.getId())
                .nomeFantasia(entity.getNomeFantasia())
                .razaoSocial(entity.getRazaoSocial())
                .cnpj(entity.getCnpj())
                .telefone(entity.getTelefone())
                .email(entity.getEmail())
                .endereco(entity.getEndereco())
                .segmento(entity.getSegmento())
                .visatado(entity.getVisatado())
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
                .endereco(domain.getEndereco())
                .segmento(domain.getSegmento())
                .visatado(domain.getVisatado())
                .build();
    }
}
