package com.gume.mapa_dinamico_motorlub.application.gateways;

import com.gume.mapa_dinamico_motorlub.application.dto.DirectionsResponseDto;
import com.gume.mapa_dinamico_motorlub.domain.empresa.Endereco;

import java.util.List;

public interface RotaMapaGateway {
    DirectionsResponseDto calcular(Endereco first, Endereco last, List<Endereco> paradas);
}
