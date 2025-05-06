package com.gume.mapa_dinamico_motorlub.infrastructure.repositories;

import com.gume.mapa_dinamico_motorlub.infrastructure.repositories.entities.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, UUID> {
}
