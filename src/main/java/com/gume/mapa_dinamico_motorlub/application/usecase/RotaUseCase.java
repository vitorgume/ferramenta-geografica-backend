package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.dto.DirectionsResponseDto;
import com.gume.mapa_dinamico_motorlub.application.gateways.RotaGateway;
import com.gume.mapa_dinamico_motorlub.application.gateways.RotaMapaGateway;
import com.gume.mapa_dinamico_motorlub.domain.Endereco;
import com.gume.mapa_dinamico_motorlub.domain.Rota;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RotaUseCase {

    private final RotaGateway rotaGateway;
    private final EnderecoUseCase enderecoUseCase;
    private final RotaMapaGateway rotaMapaGateway;

    public Rota criar(Rota novaRota) {
        List<Endereco> enderecos = novaRota.getEnderecos()
                .stream()
                .map(endereco -> enderecoUseCase.consultarPorId(endereco.getId()))
                .toList();

        novaRota.setEnderecos(enderecos);
        novaRota.setDataCriacao(LocalDate.now());
        novaRota.setConcluida(false);
        return rotaGateway.salvar(novaRota);
    }

    public Rota adicionarEndereco(UUID idRota, UUID idEndereco) {
        Rota rota = this.consultarPorId(idRota);
        Endereco endereco = enderecoUseCase.consultarPorId(idEndereco);

        List<Endereco> enderecos = new ArrayList<>(rota.getEnderecos());
        enderecos.add(endereco);
        rota.setEnderecos(enderecos);

        return rotaGateway.salvar(rota);
    }

    public List<Rota> listar() {
        return rotaGateway.listar();
    }

    private Rota consultarPorId(UUID id) {
        Optional<Rota> rotaOptional = rotaGateway.consultarPorId(id);

        if(rotaOptional.isEmpty()) {
            throw new RuntimeException("Rota não encontrada pelo seu id.");
        }

        return rotaOptional.get();
    }

    public Rota concluir(UUID id) {
        Rota rota = this.consultarPorId(id);

        rota.setConcluida(true);

        return rotaGateway.salvar(rota);
    }

    public DirectionsResponseDto calcularRota(UUID id) {
        List<Endereco> enderecos = this.consultarPorId(id).getEnderecos();
        List<Endereco> paradas = enderecos.subList(1, enderecos.size() - 1);

        return rotaMapaGateway.calcular(enderecos.get(0), enderecos.get(enderecos.size() - 1), paradas);
    }
}
