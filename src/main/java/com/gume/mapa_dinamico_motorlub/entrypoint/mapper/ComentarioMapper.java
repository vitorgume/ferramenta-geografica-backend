package com.gume.mapa_dinamico_motorlub.entrypoint.mapper;

import com.gume.mapa_dinamico_motorlub.domain.empresa.Comentario;
import com.gume.mapa_dinamico_motorlub.domain.empresa.Empresa;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.ComentarioDto;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.EmpresaDto;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.ComentarioEntity;

import java.util.List;

public class ComentarioMapper {

    public static Comentario paraDomain(ComentarioDto dto) {
        return Comentario.builder()
                .id(dto.getId())
                .conteudo(dto.getConteudo())
                .dataCriacao(dto.getDataCriacao())
                .empresa(Empresa.builder()
                            .id(dto.getEmpresaDto().getId())
                            .build()
                )
                .build();
    }

    public static ComentarioDto paraDto(Comentario domain) {
        return ComentarioDto.builder()
                .id(domain.getId())
                .conteudo(domain.getConteudo())
                .dataCriacao(domain.getDataCriacao())
                .empresaDto(EmpresaMapper.paraDto(domain.getEmpresa()))
                .build();
    }

    public static List<Comentario> paraDomains(List<ComentarioDto> dtos) {
        return dtos.stream().map(ComentarioMapper::paraDomain).toList();
    }

    public static List<ComentarioDto> paraDtos(List<Comentario> domains) {
        return domains.stream().map(ComentarioMapper::paraDto).toList();
    }
}
