package com.gume.mapa_dinamico_motorlub.entrypoint.controller;

import com.gume.mapa_dinamico_motorlub.application.usecase.ComentarioUseCase;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.ComentarioDto;
import com.gume.mapa_dinamico_motorlub.entrypoint.mapper.ComentarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final   ComentarioUseCase useCase;

    @PostMapping
    public ResponseEntity<ComentarioDto> cadastrar(@RequestBody ComentarioDto novoComentario) {
        ComentarioDto response = ComentarioMapper.paraDto(useCase.cadastrar(ComentarioMapper.paraDomain(novoComentario)));
        return ResponseEntity.created(
                UriComponentsBuilder.newInstance()
                        .path("/comentarios/{id}")
                        .buildAndExpand(response.getId())
                        .toUri()
        ).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDto> alterar(@RequestBody ComentarioDto novosDados, @PathVariable("id") UUID id) {
        ComentarioDto response = ComentarioMapper.paraDto(useCase.alterar(novosDados, id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idEmpresa}")
    public ResponseEntity<List<ComentarioDto>> listarPorEmpresa(@PathVariable("idEmpresa") UUID id) {
        List<ComentarioDto> response = ComentarioMapper.paraDtos(useCase.listarPorEmpresa(id));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") UUID id) {
        useCase.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
