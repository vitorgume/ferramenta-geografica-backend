package com.gume.mapa_dinamico_motorlub.application.gateways;

import com.gume.mapa_dinamico_motorlub.domain.Representante;

import java.util.Optional;

public interface RepresentanteGateway {
    Optional<Representante> consultarPorTelefone(String email);

    Representante salvar(Representante representante);

    Optional<Representante> consultarPorId(Long id);
}
