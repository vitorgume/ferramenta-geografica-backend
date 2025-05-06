package com.gume.mapa_dinamico_motorlub.entrypoint.controller;

import com.gume.mapa_dinamico_motorlub.application.dto.DirectionsResponseDto;
import com.gume.mapa_dinamico_motorlub.application.usecase.RotaUseCase;
import com.gume.mapa_dinamico_motorlub.domain.Endereco;
import com.gume.mapa_dinamico_motorlub.domain.Rota;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.EnderecoDto;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.RotaDto;
import com.gume.mapa_dinamico_motorlub.entrypoint.mapper.EnderecoMapper;
import com.gume.mapa_dinamico_motorlub.entrypoint.mapper.RotaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("rotas")
@RequiredArgsConstructor
public class RotaController {

    private final RotaUseCase rotaUseCase;

    @PostMapping
    public ResponseEntity<RotaDto> criar(@RequestBody RotaDto novaRota) {
        RotaDto resultado = RotaMapper.paraDto(rotaUseCase.criar(RotaMapper.paraDomain(novaRota)));
        return ResponseEntity.created(
                UriComponentsBuilder.
                        newInstance()
                        .path("/rotas/{id}")
                        .buildAndExpand(resultado.getId())
                        .toUri()
        ).body(resultado);
    }

    @PutMapping("/{idRota}/{idEndereco}")
    public ResponseEntity<RotaDto> adicionarEndereco(@PathVariable("idRota") UUID idRota, @PathVariable("idEndereco") UUID idEndereco) {
        RotaDto resultado = RotaMapper.paraDto(rotaUseCase.adicionarEndereco(idRota, idEndereco));

        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<List<RotaDto>> listar() {
        return ResponseEntity.ok(rotaUseCase.listar().stream().map(RotaMapper::paraDto).toList());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RotaDto> concluir(@PathVariable UUID id) {
        RotaDto resultado = RotaMapper.paraDto(rotaUseCase.concluir(id));

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectionsResponseDto> calcularRota(@PathVariable UUID id) {
        return ResponseEntity.ok(rotaUseCase.calcularRota(id));
    }
}
