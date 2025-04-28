package com.gume.mapa_dinamico_motorlub.infrastructure.dataprovider;

import com.gume.mapa_dinamico_motorlub.application.gateways.EmpresaGateway;
import com.gume.mapa_dinamico_motorlub.domain.Empresa;
import com.gume.mapa_dinamico_motorlub.infrastructure.exceptions.DataProviderException;
import com.gume.mapa_dinamico_motorlub.infrastructure.mapper.EmpresaMapper;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.EmpresaRepository;
import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.EmpresaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmpresaDataProvider implements EmpresaGateway {

    private final EmpresaRepository repository;

    private final String MENSAGEM_ERRO_LISTAR = "Erro ao listar empresas.";
    private final String MENSAGEM_ERRO_SALVAR_EMPRESAS = "Erro ao salvar empresas.";
    private final String MENSAGEM_ERRO_SALVAR = "Erro ao salvar empresa.";
    private final String MENSAGEM_ERRO_CONSULTAR_PELO_ID = "Erro ao consultar empresa pelo id.";

    @Override
    public List<Empresa> listar() {
        List<EmpresaEntity> empresasEntities;

        try {
            empresasEntities = repository.findAll();
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_LISTAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_LISTAR, ex.getCause());
        }

        return empresasEntities.stream().map(EmpresaMapper::paraDomain).toList();
    }

    @Override
    public List<Empresa> salvarEmpresas(List<Empresa> empresa) {
        List<EmpresaEntity> empresaEntities = empresa.stream().map(EmpresaMapper::paraEntity).toList();

        try {
            empresaEntities = repository.saveAll(empresaEntities);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_SALVAR_EMPRESAS, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause());
        }

        return empresaEntities.stream().map(EmpresaMapper::paraDomain).toList();
    }

    @Override
    public Empresa salvar(Empresa empresa) {
        EmpresaEntity empresaEntity = EmpresaMapper.paraEntity(empresa);

        try {
            empresaEntity = repository.save(empresaEntity);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_SALVAR, ex);
            throw new DataProviderException(MENSAGEM_ERRO_SALVAR, ex.getCause());
        }

        return EmpresaMapper.paraDomain(empresaEntity);
    }

    @Override
    public Optional<Empresa> consultarPorId(UUID id) {
        Optional<EmpresaEntity> empresaEntity;

        try {
            empresaEntity = repository.findById(id);
        } catch (Exception ex) {
            log.error(MENSAGEM_ERRO_CONSULTAR_PELO_ID, ex);
            throw new DataProviderException(MENSAGEM_ERRO_CONSULTAR_PELO_ID, ex.getCause());
        }

        return empresaEntity.map(EmpresaMapper::paraDomain);
    }
}
