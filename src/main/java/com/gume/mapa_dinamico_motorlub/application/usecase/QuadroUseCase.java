package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.gateways.QuadroGateway;
import com.gume.mapa_dinamico_motorlub.domain.Quadro;
import com.gume.mapa_dinamico_motorlub.domain.Representante;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuadroUseCase {

    private final RepresentanteUseCase representanteUseCase;
    private final QuadroGateway gateway;

    public Quadro cadastrar(Quadro quadro) {
        Representante representante = representanteUseCase.consultarPorId(quadro.getRepresentante().getId());
        quadro.setRepresentante(representante);
        return gateway.salvar(quadro);
    }

    public List<Quadro> listarPorRepresentante(Long idRepresentante) {
        return gateway.listarPorRepresentante(idRepresentante);
    }

    public Quadro alterar(UUID id, Quadro novosDados) {
        Quadro quadro = this.consultarPorId(id);
        quadro.setDados(novosDados);
        return gateway.salvar(quadro);
    }

    private Quadro consultarPorId(UUID id) {
        Optional<Quadro>

        return null;
    }

    public void deletar(UUID id) {
        this.consultarPorId(id);
        gateway.deletar(id);
    }
}
