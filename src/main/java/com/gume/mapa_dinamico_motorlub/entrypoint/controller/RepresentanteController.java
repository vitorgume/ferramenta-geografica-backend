package com.gume.mapa_dinamico_motorlub.entrypoint.controller;

import com.gume.mapa_dinamico_motorlub.application.usecase.RepresentanteUseCase;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.RepresentanteDto;
import com.gume.mapa_dinamico_motorlub.entrypoint.mapper.RepresentanteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("representantes")
@RequiredArgsConstructor
public class RepresentanteController {

    private final RepresentanteUseCase useCase;

    @PostMapping("cadastrar")
    public ResponseEntity<RepresentanteDto> cadastrar(@RequestBody RepresentanteDto novoRepresetante) {
        RepresentanteDto response = RepresentanteMapper.paraDto(useCase.cadastrar(RepresentanteMapper.paraDomain(novoRepresetante)));
        return ResponseEntity.created(
                UriComponentsBuilder
                        .newInstance()
                        .path("/representantes/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
    }
}
