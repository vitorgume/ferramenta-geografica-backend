package com.gume.mapa_dinamico_motorlub.infrastructure.dataprovider.bd;

import com.gume.mapa_dinamico_motorlub.application.gateways.EnderecoGateway;
import com.gume.mapa_dinamico_motorlub.domain.Endereco;
import com.gume.mapa_dinamico_motorlub.infrastructure.exceptions.DataProviderException;
import com.gume.mapa_dinamico_motorlub.infrastructure.mapper.EnderecoMapper;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.EnderecoRepository;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.EnderecoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnderecoDataProvider implements EnderecoGateway {

    private final EnderecoRepository repository;
    private final String MENSAGEM_ENDERECO_NAO_ENCONTRADO = "Endereço não encontrado com o id.";


    @Override
    public Optional<Endereco> consultarPorId(UUID id) {
        Optional<EnderecoEntity> enderecoEntity;

        try {
            enderecoEntity = repository.findById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ENDERECO_NAO_ENCONTRADO, ex);
            throw new DataProviderException(MENSAGEM_ENDERECO_NAO_ENCONTRADO, ex.getCause());
        }

        return enderecoEntity.map(EnderecoMapper::paraDomain);
    }
}
