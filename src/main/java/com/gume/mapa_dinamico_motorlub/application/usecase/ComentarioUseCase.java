package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.exceptions.ComentarioNaoEncontrado;
import com.gume.mapa_dinamico_motorlub.application.gateways.ComentarioGateway;
import com.gume.mapa_dinamico_motorlub.domain.empresa.Comentario;
import com.gume.mapa_dinamico_motorlub.domain.empresa.Empresa;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.ComentarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComentarioUseCase {

    private final ComentarioGateway gateway;
    private final EmpresaUseCase empresaUseCase;

    public Comentario cadastrar(Comentario comentario) {
        Empresa empresa = empresaUseCase.consultarPorId(comentario.getEmpresa().getId());
        comentario.setEmpresa(empresa);
        comentario.setDataCriacao(LocalDateTime.now());
        return gateway.salvar(comentario);
    }

    public Comentario alterar(ComentarioDto novosDados, UUID id) {
        Comentario comentario = this.consultarPorId(id);
        comentario.setConteudo(novosDados.getConteudo());
        return gateway.salvar(comentario);
    }

    public List<Comentario> listarPorEmpresa(UUID id) {
        return gateway.listarPorEmpresa(id);
    }

    public void deletar(UUID id) {
        this.consultarPorId(id);
        gateway.deletar(id);
    }

    private Comentario consultarPorId(UUID id) {
        Optional<Comentario> comentario = gateway.consultarPorId(id);

        if(comentario.isEmpty()) {
            throw new ComentarioNaoEncontrado();
        }

        return comentario.get();
    }
}
