package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.exceptions.QuadroPadraoNaoEncontradoException;
import com.gume.mapa_dinamico_motorlub.application.gateways.EmpresaGateway;
import com.gume.mapa_dinamico_motorlub.application.usecase.mapper.EnumMapper;
import com.gume.mapa_dinamico_motorlub.domain.*;
import com.gume.mapa_dinamico_motorlub.domain.empresa.Cordenadas;
import com.gume.mapa_dinamico_motorlub.domain.empresa.Empresa;
import com.gume.mapa_dinamico_motorlub.domain.empresa.Endereco;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpresaUseCase {

    private final EmpresaGateway gateway;
    private final CordenadasUseCase cordenadasUseCase;
    private final RepresentanteUseCase representanteUseCase;
    private final QuadroUseCase quadroUseCase;

    public List<Empresa> cadastrarEmpresas(MultipartFile arquivo) {
        log.info("Cadastrando empresas. Arquivo: {}", arquivo);
        List<Empresa> empresas = new ArrayList<>();

        if (arquivo.getOriginalFilename().endsWith(".csv")) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream()))) {
                String linha;
                boolean primeiraLinha = true;
                while ((linha = reader.readLine()) != null) {
                    if (linha.isBlank()) {
                        continue;                     }

                    String[] colunas = linha.split(",");

                    if (colunas.length < 15) {
                        throw new RuntimeException("Linha inválida: " + linha);
                    }

                    if (primeiraLinha) {
                        primeiraLinha = false;
                        continue;
                    }

                    String cnpj = colunas[0];
                    String razaoSocial = colunas[1];
                    String nomeFantasia = colunas[2];
                    String logradouro = colunas[3];
                    String numero = colunas[4];
                    String complemento = colunas[5];
                    String cep = colunas[6];
                    String bairro = colunas[7];
                    String municipio = colunas[8];
                    String uf = colunas[9];
                    String telefones = colunas[10];
                    String email = colunas[11];
                    String segmento = colunas[12];
                    String nivelIcp = colunas[13];
                    String representante = colunas[14];

                    Representante representanteEmpresa = representanteUseCase.consultarPorId(Long.valueOf(representante));

                    Endereco endereco = Endereco.builder()
                            .logradouro(logradouro)
                            .numero(numero)
                            .complemento(complemento)
                            .cep(cep)
                            .bairro(bairro)
                            .municipio(municipio)
                            .uf(uf)
                            .build();

                    Cordenadas cordenadas = cordenadasUseCase.buscarCordenadas(endereco);
                    endereco.setCordenadas(cordenadas);

                    List<Quadro> quadros = quadroUseCase.listarPorRepresentante(representanteEmpresa.getId());

                    Quadro quadro = quadros.stream()
                            .filter(q -> q.getTitulo().equals("Cadastro"))
                            .findFirst()
                            .orElseThrow(QuadroPadraoNaoEncontradoException::new);

                    Empresa empresa = Empresa.builder()
                            .cnpj(cnpj)
                            .razaoSocial(razaoSocial)
                            .nomeFantasia(nomeFantasia)
                            .telefone(telefones)
                            .email(email)
                            .endereco(endereco)
                            .segmento(EnumMapper.mapperSegmento(segmento))
                            .visitado(false)
                            .representante(representanteEmpresa)
                            .nivelIcp(EnumMapper.mapperNivelIcp(nivelIcp))
                            .quadro(quadro)
                            .build();

                    empresas.add(empresa);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(empresas.isEmpty()) {
            throw new RuntimeException("Nenhuma empresa importada.");
        }


        empresas = gateway.salvarEmpresas(empresas);

        log.info("Empresas cadastradas com sucesso. Empresas {}", empresas);

        return empresas;
    }

    public List<Empresa> listar() {
        log.info("Listando empresas.");
        List<Empresa> empresaList = gateway.listar();
        log.info("Empresas listadas com sucesso. Empresas: {}", empresaList);
        return empresaList;
    }

    public Empresa alterarStatus(UUID id) {
        Empresa empresa = this.consultarPorId(id);
        log.info("Marcando como visatada a empresa... Cnpj: {}", empresa.getCnpj());

        empresa.setVisitado(!empresa.getVisitado());

        empresa = gateway.salvar(empresa);

        log.info("Empresa marcada como visitada com sucesso. Empresa: {}", empresa);

        return empresa;
    }

    public Empresa consultarPorId(UUID id) {
        log.info("Consultando empresa pelo seu id. Id: {}", id);
        Optional<Empresa> empresaOptional = gateway.consultarPorId(id);

        if(empresaOptional.isEmpty()) {
            throw new RuntimeException("Empresa não encontrada com id: " + id);
        }

        log.info("Empresa consultada com sucesso. Empresa: {}", empresaOptional.get());

        return empresaOptional.get();
    }

    public List<Empresa> listarPorRepresentante(Long id) {
        log.info("Listando empresas pelo representante. Id representante: {}", id);
        List<Empresa> empresas = gateway.listarPorRepresentante(id);
        log.info("Empresas listadas com sucesso.");
        return empresas;
    }

    public Empresa alterarQuadro(UUID id, Quadro novoQuadro) {
        log.info("Alterando quadro da empresa. Id: {}, Novo quadro: {}", id, novoQuadro);

        Empresa empresa = this.consultarPorId(id);
        Quadro quadro = quadroUseCase.consultarPorId(novoQuadro.getId());

        empresa.setQuadro(quadro);

        empresa = gateway.salvar(empresa);

        log.info("Quadro da empresa alterado com sucesso. Empresa: {}", empresa);

        return empresa;
    }
}
