package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.gateways.QuadroGateway;
import com.gume.mapa_dinamico_motorlub.domain.Quadro;
import com.gume.mapa_dinamico_motorlub.domain.Representante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuadroPadraoUseCase {

    private final QuadroGateway gateway;

    public Quadro cadastrarPadrao(Representante representante) {
        Quadro quadro = Quadro.builder()
                .titulo("Cadastro")
                .representante(representante)
                .build();

        return gateway.salvar(quadro);
    }
}
