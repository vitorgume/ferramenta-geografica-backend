package com.gume.mapa_dinamico_motorlub.application.gateways;

import com.gume.mapa_dinamico_motorlub.domain.Endereco;

import java.util.Optional;
import java.util.UUID;

public interface EnderecoGateway {
    Optional<Endereco> consultarPorId(UUID id);
}
