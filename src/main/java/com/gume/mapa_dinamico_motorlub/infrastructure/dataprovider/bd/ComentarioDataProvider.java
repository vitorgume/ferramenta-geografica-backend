package com.gume.mapa_dinamico_motorlub.infrastructure.dataprovider.bd;

import com.gume.mapa_dinamico_motorlub.application.gateways.ComentarioGateway;
import com.gume.mapa_dinamico_motorlub.domain.empresa.Comentario;
import com.gume.mapa_dinamico_motorlub.infrastructure.exceptions.DataProviderException;
import com.gume.mapa_dinamico_motorlub.infrastructure.mapper.ComentarioMapper;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.ComentarioRepository;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.ComentarioEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ComentarioDataProvider implements ComentarioGateway {

    private final ComentarioRepository repository;
    private final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar comentário.";
    private final String MENSAGEM_ERRO_LISTAR_POR_EMPRESA = "Erro ao listar comentários pela empresa.";
    private final String MENSAGEM_ERRO_DELETAR_PELO_ID = "Erro ao deletar cometário pelo seu id.";
    private final String MENSAGEM_ERRO_CONSULTAR_POR_ID = "Erro ao consultar comentário pelo seu id.";

    @Override
    public Comentario salvar(Comentario comentario) {
        ComentarioEntity comentarioEntity = ComentarioMapper.paraEntity(comentario);

        try {
            comentarioEntity = repository.save(comentarioEntity);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_SALVAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause());
        }

        return ComentarioMapper.paraDomain(comentarioEntity);
    }

    @Override
    public List<Comentario> listarPorEmpresa(UUID id) {
        List<ComentarioEntity> comentarioEntities;

        try {
            comentarioEntities = repository.findByEmpresa(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_LISTAR_POR_EMPRESA, ex);
            throw new DataProviderException(MENSAGEM_ERRO_LISTAR_POR_EMPRESA, ex.getCause());
        }

        return ComentarioMapper.paraDomains(comentarioEntities);
    }

    @Override
    public void deletar(UUID id) {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_DELETAR_PELO_ID, ex);
            throw new DataProviderException(MENSAGEM_ERRO_DELETAR_PELO_ID, ex.getCause());
        }
    }

    @Override
    public Optional<Comentario> consultarPorId(UUID id) {
        Optional<ComentarioEntity> comentarioEntity;

        try {
            comentarioEntity = repository.findById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_POR_ID, ex.getCause());
        }

        return comentarioEntity.map(ComentarioMapper::paraDomain);
    }
}
