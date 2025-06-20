package com.gume.mapa_dinamico_motorlub.application.gateways;

import com.gume.mapa_dinamico_motorlub.domain.empresa.Comentario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComentarioGateway {
    Comentario salvar(Comentario comentario);

    List<Comentario> listarPorEmpresa(UUID id);

    void deletar(UUID id);

    Optional<Comentario> consultarPorId(UUID id);
}
