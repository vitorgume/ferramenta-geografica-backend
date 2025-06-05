package com.gume.mapa_dinamico_motorlub.application.gateways;

import com.gume.mapa_dinamico_motorlub.domain.Empresa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmpresaGateway {
    List<Empresa> listar();

    List<Empresa> salvarEmpresas(List<Empresa> empresa);

    Empresa salvar(Empresa empresa);

    Optional<Empresa> consultarPorId(UUID id);

    List<Empresa> listarPorRepresentante(Long id);
}
