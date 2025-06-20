package com.gume.mapa_dinamico_motorlub.infrastructure.dataprovider.bd;

import com.gume.mapa_dinamico_motorlub.application.gateways.RotaGateway;
import com.gume.mapa_dinamico_motorlub.domain.empresa.Rota;
import com.gume.mapa_dinamico_motorlub.infrastructure.exceptions.DataProviderException;
import com.gume.mapa_dinamico_motorlub.infrastructure.mapper.RotaMapper;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.RotaRepository;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.RotaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class RotaDataProvider implements RotaGateway {

    private final RotaRepository repository;
    private final String MENSAGEM_ERRO_SALVAR_ROTA = "Erro ao salvar rota.";
    private final String MENSAGEM_ERRO_LISTAR_ROTAS = "Erro ao listar rotas.";
    private final String MENSAGEM_ERRO_CONSULTAR_ROTA_PELO_ID = "Erro ao consultar rota pelo id.";

    @Override
    public Rota salvar(Rota novaRota) {
        RotaEntity rotaEntity;

        try {
            rotaEntity = repository.save(RotaMapper.paraEntity(novaRota));
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_SALVAR_ROTA, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR_ROTA, ex.getCause());
        }
        return RotaMapper.paraDomain(rotaEntity);
    }

    @Override
    public List<Rota> listar() {
        List<RotaEntity> rotaEntities;

        try {
            rotaEntities = repository.findAll();
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_LISTAR_ROTAS, ex);
            throw new DataProviderException(MENSAGEM_ERRO_LISTAR_ROTAS, ex.getCause());
        }

        return rotaEntities.stream().map(RotaMapper::paraDomain).toList();
    }

    @Override
    public Optional<Rota> consultarPorId(UUID id) {
        Optional<RotaEntity> rotaEntity;

        try {
            rotaEntity = repository.findById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_ROTA_PELO_ID, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_ROTA_PELO_ID, ex.getCause());
        }

        return rotaEntity.map(RotaMapper::paraDomain);
    }
}
