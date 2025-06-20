package com.gume.mapa_dinamico_motorlub.infrastructure.mapper;

import com.gume.mapa_dinamico_motorlub.domain.empresa.Endereco;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.EnderecoEntity;

public class EnderecoMapper {

    public static Endereco paraDomain(EnderecoEntity entity) {
        return Endereco.builder()
                .id(entity.getId())
                .logradouro(entity.getLogradouro())
                .numero(entity.getNumero())
                .bairro(entity.getBairro())
                .municipio(entity.getMunicipio())
                .uf(entity.getUf())
                .cep(entity.getCep())
                .complemento(entity.getComplemento())
                .cordenadas(entity.getCordenadas())
                .build();
    }

    public static EnderecoEntity paraEntity(Endereco domain) {
        return EnderecoEntity.builder()
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
