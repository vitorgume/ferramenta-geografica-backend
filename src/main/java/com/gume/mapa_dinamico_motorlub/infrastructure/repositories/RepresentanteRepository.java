package com.gume.mapa_dinamico_motorlub.infrastructure.repositories;

import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.RepresentanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepresentanteRepository extends JpaRepository<RepresentanteEntity, Long> {
    Optional<RepresentanteEntity> findByEmail(String email);
}
