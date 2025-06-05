package com.gume.mapa_dinamico_motorlub.domain;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Empresa {

    private UUID id;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String telefone;
    private String email;
    private Endereco endereco;
    private Segmento segmento;
    private Boolean visitado;
    private Representante representante;
}
