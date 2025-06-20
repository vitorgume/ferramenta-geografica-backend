package com.gume.mapa_dinamico_motorlub.infrastructure.repositories;

import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.QuadroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuadroRepository extends JpaRepository<QuadroEntity, UUID> {

    @Query("SELECT q FROM Quadro q WHERE q.representante.id = :idRepresentante")
    List<QuadroEntity> findByRepresentante(Long idRepresentante);
}
