package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.gateways.EnderecoGateway;
import com.gume.mapa_dinamico_motorlub.domain.empresa.Endereco;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnderecoUseCase {

    private final EnderecoGateway gateway;

    public Endereco consultarPorId(UUID id) {
        log.info("Consultando endereço pelo seu id. Id: {}", id);
        Optional<Endereco> enderecoOptional = gateway.consultarPorId(id);

        if(enderecoOptional.isEmpty()) {
            throw new RuntimeException("Endereço não encontrado pelo seu id.");
        }

        log.info("Endereço consultada pelo seu id. Empresa: {}", enderecoOptional.get());

        return enderecoOptional.get();
    }
}
