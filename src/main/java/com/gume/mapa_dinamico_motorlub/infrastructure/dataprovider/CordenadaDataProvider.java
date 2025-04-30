package com.gume.mapa_dinamico_motorlub.infrastructure.dataprovider;

import com.gume.mapa_dinamico_motorlub.application.dto.GeocodingResponseDto;
import com.gume.mapa_dinamico_motorlub.application.gateways.CordenadasGateway;
import com.gume.mapa_dinamico_motorlub.domain.Cordenadas;
import com.gume.mapa_dinamico_motorlub.domain.Endereco;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class CordenadaDataProvider implements CordenadasGateway {

    private final WebClient webClient;

    @Value("${google.maps.geocode.api.key}")
    private final String apiKey;

    private final String baseUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=";

    public CordenadaDataProvider(
            WebClient webClient,
            @Value("${google.maps.geocode.api.key}") String apiKey
    ){
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    @Override
    public Cordenadas buscarCordenadas(Endereco endereco) {
        String enderecoUri = URLEncoder.encode(endereco.getLogradouro() + ", " + endereco.getNumero() + ", "
        + endereco.getMunicipio() + ", " + endereco.getUf(), StandardCharsets.UTF_8);

        String uri = baseUrl + enderecoUri + "&key=" + apiKey;

        GeocodingResponseDto response = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(GeocodingResponseDto.class)
                .block();

        if (!"OK".equals(response.getStatus()) || response.getResults().isEmpty()) {
            throw new RuntimeException("Endereço não encontrado");
        }

        GeocodingResponseDto.Location location = response.getResults()
                .get(0)
                .getGeometry()
                .getLocation();

        String longitude = Double.valueOf(location.getLng()).toString();
        String latitude = Double.valueOf(location.getLat()).toString();

        return Cordenadas.builder().longitude(longitude).latitude(latitude).build();
    }



}
