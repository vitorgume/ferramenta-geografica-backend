package com.gume.mapa_dinamico_motorlub.application.gateways;

import com.gume.mapa_dinamico_motorlub.domain.Quadro;
import com.gume.mapa_dinamico_motorlub.domain.Representante;

import java.util.List;
import java.util.UUID;

public interface QuadroGateway {
    Quadro salvar(Quadro quadro);

    List<Quadro> listarPorRepresentante(Long idRepresentante);

    void deletar(UUID id);
}
