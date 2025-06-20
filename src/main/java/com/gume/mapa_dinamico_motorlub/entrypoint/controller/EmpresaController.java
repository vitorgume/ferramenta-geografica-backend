package com.gume.mapa_dinamico_motorlub.entrypoint.controller;

import com.gume.mapa_dinamico_motorlub.application.usecase.EmpresaUseCase;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.ComentarioDto;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.EmpresaDto;
import com.gume.mapa_dinamico_motorlub.entrypoint.mapper.EmpresaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("empresas")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaUseCase useCase;

    @PostMapping
    public ResponseEntity<List<EmpresaDto>> carregarEmpresas(@RequestParam("arquivo") MultipartFile arquivo) {
        List<EmpresaDto> resultado = useCase.cadastrarEmpresas(arquivo).stream().map(EmpresaMapper::paraDto).toList();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDto>> listar() {
        List<EmpresaDto> resultado = useCase.listar().stream().map(EmpresaMapper::paraDto).toList();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<EmpresaDto>> listarPorRepresentante(@PathVariable("id") Long idRepresentante) {
        List<EmpresaDto> resultado = useCase.listarPorRepresentante(idRepresentante).stream().map(EmpresaMapper::paraDto).toList();
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDto> alteraStatus(@PathVariable UUID id) {
        EmpresaDto resultado = EmpresaMapper.paraDto(useCase.alterarStatus(id));
        return ResponseEntity.ok(resultado);
    }
}
