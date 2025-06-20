package com.gume.mapa_dinamico_motorlub.infrastructure.repositories;

import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioEntity, UUID> {

    @Query("SELECT c FROM Comentario c WHERE c.empresa.id = :id")
    List<ComentarioEntity> findByEmpresa(UUID id);
}
