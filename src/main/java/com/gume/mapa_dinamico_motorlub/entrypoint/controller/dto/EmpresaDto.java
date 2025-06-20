package com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto;

import com.gume.mapa_dinamico_motorlub.domain.NivelIcp;
import com.gume.mapa_dinamico_motorlub.domain.empresa.Segmento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmpresaDto {
    private UUID id;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String telefone;
    private String email;
    private EnderecoDto endereco;
    private Segmento segmento;
    private Boolean visitado;
    private String segmentoDescricao;
    private NivelIcp nivelIcp;
    private String descricaoNivelIcp;
    private QuadroDto quadroDto;
}
