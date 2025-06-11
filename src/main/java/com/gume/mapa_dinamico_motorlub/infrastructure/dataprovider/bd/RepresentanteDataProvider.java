package com.gume.mapa_dinamico_motorlub.infrastructure.dataprovider.bd;

import com.gume.mapa_dinamico_motorlub.application.gateways.RepresentanteGateway;
import com.gume.mapa_dinamico_motorlub.domain.Representante;
import com.gume.mapa_dinamico_motorlub.infrastructure.exceptions.DataProviderException;
import com.gume.mapa_dinamico_motorlub.infrastructure.mapper.RepresentanteMapper;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.RepresentanteRepository;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.RepresentanteEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class RepresentanteDataProvider implements RepresentanteGateway {

    private final RepresentanteRepository repository;

    private final String MENSAGEM_ERRO_CONSULTAR_POR_TELEFONE = "Erro ao consultar representante pelo seu telefone.";
    private final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar representante.";
    private final String MENSAGEM_ERRO_CONSULTAR_POR_ID = "Erro ao consultar represetanten pelo seu id.";

    @Override
    public Optional<Representante> consultarPorTelefone(String telefone) {
        Optional<RepresentanteEntity> representanteEntity;

        try {
            representanteEntity = repository.findByTelefone(telefone);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_TELEFONE, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_TELEFONE, ex.getCause());
        }

        return representanteEntity.map(RepresentanteMapper::paraDomain);
    }

    @Override
    public Representante salvar(Representante representante) {
        RepresentanteEntity representanteEntity;

        try {
            representanteEntity = repository.save(RepresentanteMapper.paraEntity(representante));
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_SALVAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause());
        }

        return RepresentanteMapper.paraDomain(representanteEntity);
    }

    @Override
    public Optional<Representante> consultarPorId(Long id) {
        Optional<RepresentanteEntity> representanteEntity;

        try {
            representanteEntity = repository.findById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex.getCause());
        }

        return representanteEntity.map(RepresentanteMapper::paraDomain);
    }
}
