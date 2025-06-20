package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.exceptions.RepresentanteJaExistenteException;
import com.gume.mapa_dinamico_motorlub.application.exceptions.RepresentanteNaoEncontradoException;
import com.gume.mapa_dinamico_motorlub.application.gateways.RepresentanteGateway;
import com.gume.mapa_dinamico_motorlub.domain.Quadro;
import com.gume.mapa_dinamico_motorlub.domain.Representante;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.RepresentanteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RepresentanteUseCase {

    private final RepresentanteGateway gateway;
    private final CriptografiaUseCase criptografiaUseCase;
    private final QuadroPadraoUseCase quadroPadraoUseCase;


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

        quadroPadraoUseCase.cadastrarPadrao(representante);

        log.info("Representante cadastrado com sucesso. Representante: {}", representante);

        return representante;
    }

    public Representante alterarSenha(Long idRepresentante, RepresentanteDto novoRepresentante) {

        log.info("Alterando senha do representante. Id do representante: {}, Representante: {}", idRepresentante, novoRepresentante);

        Representante representante = this.consultarPorId(idRepresentante);
        String novaSenha = criptografiaUseCase.criptografar(novoRepresentante.getSenha());
        representante.setSenha(novaSenha);
        representante = gateway.salvar(representante);

        log.info("Senha alterada com sucesso. Representante: {}", representante);

        return representante;

    }

    public Representante consultarPorId(Long id) {
        log.info("Consultando representante pelo seu id. Id representante: {}", id);

        Optional<Representante> representanteOptional = gateway.consultarPorId(id);

        if(representanteOptional.isEmpty()) {
            throw new RepresentanteNaoEncontradoException();
        }

        log.info("Representante consultado com sucesso. Representante: {}", representanteOptional.get());

        return representanteOptional.get();
    }
}
