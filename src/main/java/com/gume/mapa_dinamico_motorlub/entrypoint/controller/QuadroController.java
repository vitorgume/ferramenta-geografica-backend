package com.gume.mapa_dinamico_motorlub.entrypoint.controller;

import com.gume.mapa_dinamico_motorlub.application.usecase.QuadroUseCase;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.QuadroDto;
import com.gume.mapa_dinamico_motorlub.entrypoint.mapper.QuadroMapper;
import com.gume.mapa_dinamico_motorlub.entrypoint.mapper.RepresentanteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("quadros")
@RequiredArgsConstructor
public class QuadroController {

    private final QuadroUseCase quadroUseCase;

    @PostMapping
    public ResponseEntity<QuadroDto> cadastrar(@RequestBody QuadroDto novoQuadro) {
        QuadroDto response = QuadroMapper.paraDto(quadroUseCase.cadastrar(QuadroMapper.paraDomain(novoQuadro)));
        return ResponseEntity.created(UriComponentsBuilder
                .newInstance()
                .path("/quadros/{id}")
                .buildAndExpand(response.getId())
                .toUri()
        ).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuadroDto>> listarPorRepresentante(@PathVariable("id") Long idRepresentante) {
        List<QuadroDto> response = quadroUseCase.listarPorRepresentante(idRepresentante).stream().map(QuadroMapper::paraDto).toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuadroDto> alterar(@PathVariable("id") UUID id, @RequestBody QuadroDto novosDados) {
        QuadroDto response = QuadroMapper.paraDto(quadroUseCase.alterar(id, novosDados.getTitulo()));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") UUID id) {
        quadroUseCase.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
