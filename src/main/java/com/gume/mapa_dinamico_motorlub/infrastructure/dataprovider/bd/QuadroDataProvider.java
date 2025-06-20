package com.gume.mapa_dinamico_motorlub.infrastructure.dataprovider.bd;

import com.gume.mapa_dinamico_motorlub.application.gateways.QuadroGateway;
import com.gume.mapa_dinamico_motorlub.domain.Quadro;
import com.gume.mapa_dinamico_motorlub.infrastructure.exceptions.DataProviderException;
import com.gume.mapa_dinamico_motorlub.infrastructure.mapper.QuadroMapper;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.QuadroRepository;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.QuadroEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuadroDataProvider implements QuadroGateway {

    private final QuadroRepository repository;
    private final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar quadro.";
    private final String MENSAGEM_ERRO_LISTAR_POR_REPRESENTANTE = "Erro ao listar quadros pelo representante.";
    private final String MENSAGEM_ERRO_DELETAR = "Erro ao deletar quadro.";
    private final String MENSAGEM_ERRO_CONSULTAR_ID = "Erro ao consultar quadro pelo seu id.";

    @Override
    public Quadro salvar(Quadro quadro) {
        QuadroEntity quadroEntity = QuadroMapper.paraEntity(quadro);

        try {
            quadroEntity = repository.save(quadroEntity);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_SALVAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause());
        }

        return QuadroMapper.paraDomain(quadroEntity);
    }

    @Override
    public List<Quadro> listarPorRepresentante(Long idRepresentante) {
        List<QuadroEntity> quadroEntities;

        try {
            quadroEntities = repository.findByRepresentante(idRepresentante);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_LISTAR_POR_REPRESENTANTE, ex);
            throw new DataProviderException(MENSAGEM_ERRO_LISTAR_POR_REPRESENTANTE, ex.getCause());
        }

        return quadroEntities.stream().map(QuadroMapper::paraDomain).toList();
    }

    @Override
    public void deletar(UUID id) {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_DELETAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_DELETAR, ex.getCause());
        }
    }

    @Override
    public Optional<Quadro> consultarPorId(UUID id) {
        Optional<QuadroEntity> quadroEntity;

        try {
            quadroEntity = repository.findById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_ID, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_ID, ex.getCause());
        }


        return quadroEntity.map(QuadroMapper::paraDomain);
    }
}
