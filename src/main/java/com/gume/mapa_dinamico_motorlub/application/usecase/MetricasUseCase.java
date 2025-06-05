package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.gateways.EmpresaGateway;
import com.gume.mapa_dinamico_motorlub.domain.Empresa;
import com.gume.mapa_dinamico_motorlub.domain.Metrica;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricasUseCase {

    private final EmpresaUseCase empresaUseCase;

    public Metrica quantidadeVisitados(Long id) {
        List<Empresa> empresas = empresaUseCase.listarPorRepresentante(id);

        List<Empresa> empresasVisitadas = empresas.stream().filter(Empresa::getVisitado).toList();
        List<Empresa> empresasNaoVisitadas = empresas.stream().filter(empresa -> !empresa.getVisitado()).toList();

        return Metrica.builder()
                .quantidadeVisitado(empresasVisitadas.size())
                .quantidadeNaoVisitado(empresasNaoVisitadas.size())
                .build();
    }
}
