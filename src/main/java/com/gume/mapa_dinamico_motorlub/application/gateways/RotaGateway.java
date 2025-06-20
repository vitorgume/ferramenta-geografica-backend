package com.gume.mapa_dinamico_motorlub.application.gateways;

import com.gume.mapa_dinamico_motorlub.domain.empresa.Rota;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RotaGateway {
    Rota salvar(Rota novaRota);

    List<Rota> listar();

    Optional<Rota> consultarPorId(UUID id);
}
