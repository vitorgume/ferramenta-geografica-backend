package com.gume.mapa_dinamico_motorlub.application.gateways;

import com.gume.mapa_dinamico_motorlub.domain.Cordenadas;
import com.gume.mapa_dinamico_motorlub.domain.Endereco;

public interface CordenadasGateway {
    Cordenadas buscarCordenadas(Endereco endereco);
}
