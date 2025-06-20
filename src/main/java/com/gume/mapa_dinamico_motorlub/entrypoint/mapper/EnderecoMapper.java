package com.gume.mapa_dinamico_motorlub.entrypoint.mapper;

import com.gume.mapa_dinamico_motorlub.domain.empresa.Endereco;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.EnderecoDto;

public class EnderecoMapper {

    public static Endereco paraDomain(EnderecoDto dto) {
        return Endereco.builder()
                .id(dto.getId())
                .logradouro(dto.getLogradouro())
                .numero(dto.getNumero())
                .bairro(dto.getBairro())
                .municipio(dto.getMunicipio())
                .uf(dto.getUf())
                .cep(dto.getCep())
                .complemento(dto.getComplemento())
                .cordenadas(dto.getCordenadas())
                .build();
    }

    public static EnderecoDto paraDto(Endereco domain) {
        return EnderecoDto.builder()
                .id(domain.getId())
                .logradouro(domain.getLogradouro())
                .numero(domain.getNumero())
                .bairro(domain.getBairro())
                .municipio(domain.getMunicipio())
                .uf(domain.getUf())
                .cep(domain.getCep())
                .complemento(domain.getComplemento())
                .cordenadas(domain.getCordenadas())
                .build();
    }
}
