package com.gume.mapa_dinamico_motorlub.entrypoint.mapper;

import com.gume.mapa_dinamico_motorlub.domain.Empresa;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.EmpresaDto;

public class EmpresaMapper {

    public static Empresa paraDomain(EmpresaDto dto) {
        return Empresa.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cnpj(dto.getCnpj())
                .endereco(dto.getEndereco())
                .segmento(dto.getSegmento())
                .visatado(dto.getVisatado())
                .build();
    }

    public static EmpresaDto paraDto(Empresa domain) {
        return EmpresaDto.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .cnpj(domain.getCnpj())
                .endereco(domain.getEndereco())
                .segmento(domain.getSegmento())
                .visatado(domain.getVisatado())
                .build();
    }
}
