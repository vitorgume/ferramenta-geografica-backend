package com.gume.mapa_dinamico_motorlub.infrastructure.dataprovider.api;

import com.gume.mapa_dinamico_motorlub.application.dto.DirectionsResponseDto;
import com.gume.mapa_dinamico_motorlub.application.gateways.RotaMapaGateway;
import com.gume.mapa_dinamico_motorlub.domain.empresa.Endereco;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RotaMapaDataProvider implements RotaMapaGateway {

    private final WebClient webClient;

    @Value("${google.maps.geocode.api.key}")
    private final String apiKey;

    private final String baseUrl = "https://maps.googleapis.com/maps/api/directions/json";

    public RotaMapaDataProvider(
            WebClient webClient,
            @Value("${google.maps.geocode.api.key}") String apiKey
    ){
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    @Override
    public DirectionsResponseDto calcular(Endereco primeiro, Endereco ultimo, List<Endereco> paradas) {
        String uri;

        String origem = formatarEndereco(primeiro);
        String destino = formatarEndereco(ultimo);

        if(paradas.isEmpty()) {
            uri = UriComponentsBuilder
                    .fromHttpUrl(baseUrl)
                    .queryParam("origin", origem)
                    .queryParam("destination", destino)
                    .queryParam("key", apiKey)
                    .build()
                    .toUriString();

            return webClient.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(DirectionsResponseDto.class)
                    .block();
        } else {
            List<String> paradasString = paradas.stream()
                    .map(this::formatarEndereco)
                    .toList();

            String paradasFormatadas = String.join("|", paradasString);

            uri = UriComponentsBuilder
                    .fromHttpUrl(baseUrl)
                    .queryParam("origin", origem)
                    .queryParam("destination", destino)
                    .queryParam("waypoints", paradasFormatadas)
                    .queryParam("key", apiKey)
                    .build()
                    .toUriString();

            return webClient.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(DirectionsResponseDto.class)
                    .block();
        }
    }

    private String formatarEndereco(Endereco endereco) {
        return String.format(
                "%s, %s - %s, %s - %s, %s, Brasil",
                formatarTexto(endereco.getLogradouro()),
                endereco.getNumero(),
                formatarTexto(endereco.getBairro()),
                formatarTexto(endereco.getMunicipio()),
                endereco.getUf().toUpperCase(),
                endereco.getCep()
        ).replace(" ", "+");
    }

    private String formatarTexto(String texto) {
        if (texto == null || texto.isEmpty()) return "";
        return Arrays.stream(texto.toLowerCase().split(" "))
                .map(p -> p.isEmpty() ? "" : p.substring(0, 1).toUpperCase() + p.substring(1))
                .collect(Collectors.joining(" "));
    }
}
