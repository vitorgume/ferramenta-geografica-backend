package com.gume.mapa_dinamico_motorlub.entrypoint.mapper;

import com.gume.mapa_dinamico_motorlub.domain.Empresa;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.EmpresaDto;

public class EmpresaMapper {

    public static Empresa paraDomain(EmpresaDto dto) {
        return Empresa.builder()
                .id(dto.getId())
                .nomeFantasia(dto.getNomeFantasia())
                .razaoSocial(dto.getRazaoSocial())
                .cnpj(dto.getCnpj())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .endereco(dto.getEndereco())
                .segmento(dto.getSegmento())
                .visatado(dto.getVisatado())
                .build();
    }

    public static EmpresaDto paraDto(Empresa domain) {
        return EmpresaDto.builder()
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
