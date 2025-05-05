package com.gume.mapa_dinamico_motorlub.entrypoint.controller;

import com.gume.mapa_dinamico_motorlub.application.usecase.EmpresaUseCase;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.EmpresaDto;
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
        List<EmpresaDto> resultado = useCase.cadastrarEmpresas(arquivo);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDto>> listar() {
        List<EmpresaDto> resultado = useCase.listar();
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDto> alteraStatus(@PathVariable UUID id) {
        EmpresaDto resultado = useCase.alterarStatus(id);
        return ResponseEntity.ok(resultado);
    }
}
