package com.gume.mapa_dinamico_motorlub.infrastructure.repositories;

import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.RotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RotaRepository extends JpaRepository<RotaEntity, UUID> {
}
