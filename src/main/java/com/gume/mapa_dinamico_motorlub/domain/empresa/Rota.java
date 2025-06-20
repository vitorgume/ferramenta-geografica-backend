package com.gume.mapa_dinamico_motorlub.domain.empresa;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Rota {
    private UUID id;
    private LocalDate dataCriacao;
    private List<Endereco> enderecos;
    private Boolean concluida;
}
