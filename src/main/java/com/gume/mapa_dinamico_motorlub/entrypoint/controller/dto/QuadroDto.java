package com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class QuadroDto {
    private UUID id;
    private String titulo;
    private RepresentanteDto representante;
}
