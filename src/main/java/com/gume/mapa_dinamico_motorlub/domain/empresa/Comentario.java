package com.gume.mapa_dinamico_motorlub.domain.empresa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Comentario {
    private UUID id;
    private String conteudo;
    private LocalDateTime dataCriacao;
    private Empresa empresa;
}
