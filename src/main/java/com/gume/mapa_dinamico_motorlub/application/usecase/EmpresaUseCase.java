package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.gateways.EmpresaGateway;
import com.gume.mapa_dinamico_motorlub.domain.Empresa;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.EmpresaDto;
import com.gume.mapa_dinamico_motorlub.entrypoint.mapper.EmpresaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmpresaUseCase {

    private final EmpresaGateway gateway;

    public List<EmpresaDto> cadastrarEmpresas(MultipartFile arquivo) {
        if (arquivo.getOriginalFilename().endsWith(".csv")) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream()))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] colunas = linha.split(";");
                    String nomeEmpresa = colunas[0];
                    String bairro = colunas[1];
                    // ...
                    // salvar no banco
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<EmpresaDto> listar() {
        List<Empresa> empresaList = gateway.listar();
        return empresaList.stream().map(EmpresaMapper::paraDto).toList();
    }

    public EmpresaDto marcarVisitada(UUID id) {
        Empresa empresa = this.consultarPorId(id);

        empresa.setVisatado(true);

        empresa = gateway.salvar(empresa);

        return EmpresaMapper.paraDto(empresa);
    }

    private Empresa consultarPorId(UUID id) {
        Optional<Empresa> empresaOptional = gateway.consultarPorId(id);

        if(empresaOptional.isEmpty()) {
            throw new RuntimeException("Empresa não encontrada com id: " + id);
        }

        return empresaOptional.get();
    }
}
