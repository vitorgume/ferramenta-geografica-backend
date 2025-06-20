package com.gume.mapa_dinamico_motorlub.application.gateways;

import com.gume.mapa_dinamico_motorlub.domain.empresa.Cordenadas;
import com.gume.mapa_dinamico_motorlub.domain.empresa.Endereco;

public interface CordenadasGateway {
    Cordenadas buscarCordenadas(Endereco endereco);
}
