package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.gateways.CordenadasGateway;
import com.gume.mapa_dinamico_motorlub.domain.Cordenadas;
import com.gume.mapa_dinamico_motorlub.domain.Endereco;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CordenadasUseCase {

    private final CordenadasGateway gateway;

    public Cordenadas buscarCordenadas(Endereco endereco) {
        log.info("Buscando cordenadas para o endereco. Endereco: {}", endereco);
        Cordenadas cordenadas = gateway.buscarCordenadas(endereco);

        if(cordenadas == null) {
            log.warn("Cordenadas não encontrada para o CEP: {}, Numero: {}",
                    endereco.getCep(), endereco.getNumero());
        }

        log.info("Cordenadas buscadas com sucesso. Cordenadas: {}", cordenadas);
        return cordenadas;
    }

}
