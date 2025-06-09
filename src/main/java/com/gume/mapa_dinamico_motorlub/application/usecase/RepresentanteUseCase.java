package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.exceptions.RepresentanteJaExistenteException;
import com.gume.mapa_dinamico_motorlub.application.exceptions.RepresentanteNaoEncontradoException;
import com.gume.mapa_dinamico_motorlub.application.gateways.RepresentanteGateway;
import com.gume.mapa_dinamico_motorlub.domain.Representante;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RepresentanteUseCase {

    private final RepresentanteGateway gateway;
    private final CriptografiaUseCase criptografiaUseCase;


    public Representante consultarPorEmail(String email) {
        log.info("Consultando representante pelo seu email. Email: {}", email);

        Optional<Representante> representante = gateway.consultarPorEmail(email);

        if(representante.isEmpty()) {
            throw new RepresentanteNaoEncontradoException();
        }

        log.info("Representante consultado pelo seu email com sucesso. Email: {}", email);

        return representante.get();
    }

    public Representante cadastrar(Representante representante) {
        log.info("Cadastrando novo representante. Representante: {}", representante);

        String senhaCriptografada = criptografiaUseCase.criptografar(representante.getSenha());
        representante.setSenha(senhaCriptografada);

        Optional<Representante> representanteExistente = gateway.consultarPorEmail(representante.getEmail());

        representanteExistente.ifPresent(rep -> {
            throw new RepresentanteJaExistenteException();
        });

        representante = gateway.salvar(representante);

        log.info("Representante cadastrado com sucesso. Representante: {}", representante);

        return representante;
    }
}
