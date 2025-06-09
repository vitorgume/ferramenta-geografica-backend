package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.dto.DirectionsResponseDto;
import com.gume.mapa_dinamico_motorlub.application.gateways.RotaGateway;
import com.gume.mapa_dinamico_motorlub.application.gateways.RotaMapaGateway;
import com.gume.mapa_dinamico_motorlub.domain.Endereco;
import com.gume.mapa_dinamico_motorlub.domain.Rota;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RotaUseCase {

    private final RotaGateway rotaGateway;
    private final EnderecoUseCase enderecoUseCase;
    private final RotaMapaGateway rotaMapaGateway;

    public Rota criar(Rota novaRota) {
        log.info("Criando rota. Nova rota: {}", novaRota);

        List<Endereco> enderecos = novaRota.getEnderecos()
                .stream()
                .map(endereco -> enderecoUseCase.consultarPorId(endereco.getId()))
                .toList();

        novaRota.setEnderecos(enderecos);
        novaRota.setDataCriacao(LocalDate.now());
        novaRota.setConcluida(false);
        Rota rota = rotaGateway.salvar(novaRota);

        log.info("Nova rota salva com sucesso. Rota: {}", rota);

        return rota;
    }

    public Rota adicionarEndereco(UUID idRota, UUID idEndereco) {
        log.info("Adicionando endereço a rota. Id da rota: {}, Id do endereço: {}", idRota, idEndereco);
        Rota rota = this.consultarPorId(idRota);
        Endereco endereco = enderecoUseCase.consultarPorId(idEndereco);

        List<Endereco> enderecos = new ArrayList<>(rota.getEnderecos());
        enderecos.add(endereco);
        rota.setEnderecos(enderecos);

        Rota rotaSalva = rotaGateway.salvar(rota);
        log.info("Endereço adicionado com sucesso. Rota: {}", rotaSalva);

        return rotaSalva;
    }

    public List<Rota> listar() {
        log.info("Listando todas as rotas.");
        List<Rota> rotas = rotaGateway.listar();
        log.info("Rotas listadas com sucesso. Rotas: {}", rotas);
        return rotas;
    }

    private Rota consultarPorId(UUID id) {
        log.info("Consultando rota pelo seu id. Id: {}", id);
        Optional<Rota> rotaOptional = rotaGateway.consultarPorId(id);

        if(rotaOptional.isEmpty()) {
            throw new RuntimeException("Rota não encontrada pelo seu id.");
        }

        log.info("Rota consultada pelo seu id com sucesso. Rota: {}", rotaOptional.get());

        return rotaOptional.get();
    }

    public Rota concluir(UUID id) {
        log.info("Concluido uma rota. Id da rota: {}", id);

        Rota rota = this.consultarPorId(id);

        rota.setConcluida(true);

        Rota rotaSalva = rotaGateway.salvar(rota);

        log.info("Rota concluida com sucesso. Rota: {}", rotaSalva);

        return rotaSalva;
    }

    public DirectionsResponseDto calcularRota(UUID id) {
        log.info("Calculando uma rota. Id da rota: {}", id);

        List<Endereco> enderecos = this.consultarPorId(id).getEnderecos();
        List<Endereco> paradas = enderecos.subList(1, enderecos.size() - 1);

        DirectionsResponseDto rotaCalculada = rotaMapaGateway.calcular(enderecos.get(0), enderecos.get(enderecos.size() - 1), paradas);

        log.info("Rota calculada com sucesso. Cálculo: {}", rotaCalculada);

        return rotaCalculada;
    }
}
