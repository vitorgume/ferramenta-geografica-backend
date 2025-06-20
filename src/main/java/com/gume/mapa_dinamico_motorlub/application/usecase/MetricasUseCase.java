package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.domain.empresa.Empresa;
import com.gume.mapa_dinamico_motorlub.domain.Metrica;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricasUseCase {

    private final EmpresaUseCase empresaUseCase;

    public Metrica quantidadeVisitados(Long id) {
        log.info("Consultando quantidade de métricas do representanten. Id representante: {}", id);
        List<Empresa> empresas = empresaUseCase.listarPorRepresentante(id);

        List<Empresa> empresasVisitadas = empresas.stream().filter(Empresa::getVisitado).toList();
        List<Empresa> empresasNaoVisitadas = empresas.stream().filter(empresa -> !empresa.getVisitado()).toList();



        Metrica metrica = Metrica.builder()
                .quantidadeVisitado(empresasVisitadas.size())
                .quantidadeNaoVisitado(empresasNaoVisitadas.size())
                .build();

        log.info("Métrica consultada com sucesso. Metrica: {}", metrica);
        return metrica;
    }
}
