package com.gume.mapa_dinamico_motorlub.entrypoint.mapper;

import com.gume.mapa_dinamico_motorlub.domain.Rota;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.RotaDto;

public class RotaMapper {

    public static Rota paraDomain(RotaDto dto) {
        return Rota.builder()
                .id(dto.getId())
                .dataCriacao(dto.getDataCriacao())
                .enderecos(dto.getEnderecos().stream().map(EnderecoMapper::paraDomain).toList())
                .concluida(dto.getConcluida())
                .build();
    }

    public static RotaDto paraDto(Rota domain) {
        return RotaDto.builder()
                .id(domain.getId())
                .dataCriacao(domain.getDataCriacao())
                .enderecos(domain.getEnderecos().stream().map(EnderecoMapper::paraDto).toList())
                .concluida(domain.getConcluida())
                .build();
    }
}
