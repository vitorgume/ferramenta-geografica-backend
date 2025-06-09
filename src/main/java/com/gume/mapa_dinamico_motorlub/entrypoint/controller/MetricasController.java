package com.gume.mapa_dinamico_motorlub.entrypoint.controller;

import com.gume.mapa_dinamico_motorlub.application.usecase.MetricasUseCase;
import com.gume.mapa_dinamico_motorlub.domain.Metrica;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.MetricaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("metricas")
@RequiredArgsConstructor
public class MetricasController {

    private final MetricasUseCase metricasUseCase;

    @GetMapping("/visitados/{id}")
    public ResponseEntity<MetricaDto> quantidadeVisitados(@PathVariable("id") Long id) {
        MetricaDto resultado = MetricaMapper.paraDto(metricasUseCase.quantidadeVisitados(id));
        return ResponseEntity.ok(resultado);
    }

}
