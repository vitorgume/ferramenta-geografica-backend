package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.exceptions.RepresentanteJaExistenteException;
import com.gume.mapa_dinamico_motorlub.application.exceptions.RepresentanteNaoEncontradoException;
import com.gume.mapa_dinamico_motorlub.application.gateways.RepresentanteGateway;
import com.gume.mapa_dinamico_motorlub.domain.Representante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RepresentanteUseCase {

    private final RepresentanteGateway gateway;
    private final CriptografiaUseCase criptografiaUseCase;


    public Representante consultarPorEmail(String email) {
        Optional<Representante> representante = gateway.consultarPorEmail(email);

        if(representante.isEmpty()) {
            throw new RepresentanteNaoEncontradoException();
        }

        return representante.get();
    }

    public Representante cadastrar(Representante representante) {
        String senhaCriptografada = criptografiaUseCase.criptografar(representante.getSenha());
        representante.setSenha(senhaCriptografada);

        Optional<Representante> representanteExistente = gateway.consultarPorEmail(representante.getEmail());

        representanteExistente.ifPresent(rep -> {
            throw new RepresentanteJaExistenteException();
        });

        return gateway.salvar(representante);
    }
}
