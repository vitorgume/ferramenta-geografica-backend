package com.gume.mapa_dinamico_motorlub.infrastructure.dataprovider.api;

import com.gume.mapa_dinamico_motorlub.application.dto.GeocodingResponseDto;
import com.gume.mapa_dinamico_motorlub.application.gateways.CordenadasGateway;
import com.gume.mapa_dinamico_motorlub.domain.Cordenadas;
import com.gume.mapa_dinamico_motorlub.domain.Endereco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
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
        String enderecoFormatado = formatarEndereco(endereco);

        log.info("Endereço formatado: {}", enderecoFormatado);

        String enderecoUri = enderecoFormatado.replace(" ", "+");
        log.info("Endereço URI: {}", enderecoUri);

        String uri = baseUrl + enderecoUri + "&components=country:BR|administrative_area:PR&region=br&key=" + apiKey;

        GeocodingResponseDto response = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(GeocodingResponseDto.class)
                .block();

        if (!"OK".equals(response.getStatus()) || response.getResults().isEmpty()) {
            log.info("Endereço não encontrado: {}", enderecoFormatado);
        } else {
            Optional<GeocodingResponseDto.Result> melhorResultado = response.getResults().stream()
                    .filter(r -> "ROOFTOP".equalsIgnoreCase(r.getGeometry().getLocationType()))
                    .findFirst();

            GeocodingResponseDto.Result resultado = melhorResultado.orElse(response.getResults().get(0));

            double lat = resultado.getGeometry().getLocation().getLat();
            double lng = resultado.getGeometry().getLocation().getLng();

            log.info("Coordenadas encontradas: Latitude={}, Longitude={}", lat, lng);

            return Cordenadas.builder()
                    .latitude(String.valueOf(lat))
                    .longitude(String.valueOf(lng))
                    .build();
        }

        return Cordenadas.builder().build();
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
        );
    }

    private String formatarTexto(String texto) {
        if (texto == null || texto.isEmpty()) return "";
        return Arrays.stream(texto.toLowerCase().split(" "))
                .map(p -> p.isEmpty() ? "" : p.substring(0, 1).toUpperCase() + p.substring(1))
                .collect(Collectors.joining(" "));
    }





}
