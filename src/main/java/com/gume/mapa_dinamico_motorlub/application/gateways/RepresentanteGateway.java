package com.gume.mapa_dinamico_motorlub.application.gateways;

import com.gume.mapa_dinamico_motorlub.domain.Representante;

import java.util.Optional;
import java.util.UUID;

public interface RepresentanteGateway {
    Optional<Representante> consultarPorEmail(String email);

    Representante salvar(Representante representante);

    Optional<Representante> consultarPorId(Long id);
}
