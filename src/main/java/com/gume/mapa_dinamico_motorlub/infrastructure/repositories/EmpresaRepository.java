package com.gume.mapa_dinamico_motorlub.infrastructure.repositories;

import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, UUID> {

    @Query("SELECT e FROM Empresa e WHERE e.representante.id = :id")
    List<EmpresaEntity> listarPorRepresentante(Long id);
}
