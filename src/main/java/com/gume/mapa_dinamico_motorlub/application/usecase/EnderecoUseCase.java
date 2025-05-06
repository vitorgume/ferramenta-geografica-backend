package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.gateways.EnderecoGateway;
import com.gume.mapa_dinamico_motorlub.domain.Endereco;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoUseCase {

    private final EnderecoGateway gateway;

    public Endereco consultarPorId(UUID id) {
        Optional<Endereco> enderecoOptional = gateway.consultarPorId(id);

        if(enderecoOptional.isEmpty()) {
            throw new RuntimeException("Endereço não encontrado pelo seu id.");
        }

        return enderecoOptional.get();
    }
}
