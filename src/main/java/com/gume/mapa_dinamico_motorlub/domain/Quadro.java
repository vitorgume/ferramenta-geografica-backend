package com.gume.mapa_dinamico_motorlub.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Quadro {
    private UUID id;
    private String titulo;
    private Representante representante;

    public void setDados(Quadro novosDados) {
    }
}
