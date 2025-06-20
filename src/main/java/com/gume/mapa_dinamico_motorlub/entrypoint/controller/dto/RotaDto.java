package com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class RotaDto {
    private UUID id;
    private LocalDate dataCriacao;
    private List<EnderecoDto> enderecos;
    private Boolean concluida;
}
