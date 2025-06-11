package com.gume.mapa_dinamico_motorlub.entrypoint.mapper;

import com.gume.mapa_dinamico_motorlub.domain.Representante;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.RepresentanteDto;

public class RepresentanteMapper {

    public static RepresentanteDto paraDto(Representante domain) {
        return RepresentanteDto.builder()
                .id(domain.getId())
                .telefone(domain.getTelefone())
                .nome(domain.getNome())
                .regiao(domain.getRegiao())
                .build();
    }

    public static Representante paraDomain(RepresentanteDto dto) {
        return Representante.builder()
                .id(dto.getId())
                .telefone(dto.getTelefone())
                .nome(dto.getNome())
                .regiao(dto.getRegiao())
                .senha(dto.getSenha())
                .build();
    }
}
