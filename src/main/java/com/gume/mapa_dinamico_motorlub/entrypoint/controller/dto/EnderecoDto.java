package com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto;

import com.gume.mapa_dinamico_motorlub.domain.empresa.Cordenadas;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class EnderecoDto {
    private UUID id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String municipio;
    private String uf;
    private String cep;
    private String complemento;
    private Cordenadas cordenadas;
}
