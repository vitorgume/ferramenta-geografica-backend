package com.gume.mapa_dinamico_motorlub.entrypoint.mapper;

import com.gume.mapa_dinamico_motorlub.domain.empresa.Empresa;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.EmpresaDto;

public class EmpresaMapper {

    public static EmpresaDto paraDto(Empresa domain) {
        return EmpresaDto.builder()
                .id(domain.getId())
                .nomeFantasia(domain.getNomeFantasia())
                .razaoSocial(domain.getRazaoSocial())
                .cnpj(domain.getCnpj())
                .telefone(domain.getTelefone())
                .email(domain.getEmail())
                .endereco(EnderecoMapper.paraDto(domain.getEndereco()))
                .segmentoDescricao(domain.getSegmento().getDescricao())
                .visitado(domain.getVisitado())
                .descricaoNivelIcp(domain.getNivelIcp().getDescricao())
                .quadroDto(QuadroMapper.paraDto(domain.getQuadro()))
                .build();
    }
}
