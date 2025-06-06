package com.gume.mapa_dinamico_motorlub.entrypoint.mapper;

import com.gume.mapa_dinamico_motorlub.domain.Representante;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.RepresentanteDto;

public class RepresentanteMapper {

    public static RepresentanteDto paraDto(Representante domain) {
        return RepresentanteDto.builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .nome(domain.getNome())
                .senha(domain.getSenha())
                .regiao(domain.getRegiao())
                .build();
    }

    public static Representante paraDomain(RepresentanteDto dto) {
        return Representante.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .nome(dto.getNome())
                .regiao(dto.getRegiao())
                .build();
    }
}
