package com.gume.mapa_dinamico_motorlub.entrypoint.controller;

import com.gume.mapa_dinamico_motorlub.application.usecase.RepresentanteUseCase;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.RepresentanteDto;
import com.gume.mapa_dinamico_motorlub.entrypoint.mapper.RepresentanteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("representantes")
@RequiredArgsConstructor
public class RepresentanteController {

    private final RepresentanteUseCase useCase;

    @PostMapping("/cadastrar")
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

    @PatchMapping("/senha/{id}")
    public ResponseEntity<RepresentanteDto> alterarSenha(@PathVariable("id") Long idRepresentante, @RequestBody RepresentanteDto representanteDto) {
        RepresentanteDto response =  RepresentanteMapper.paraDto(useCase.alterarSenha(idRepresentante, representanteDto));
        return ResponseEntity.ok(response);
    }
}
