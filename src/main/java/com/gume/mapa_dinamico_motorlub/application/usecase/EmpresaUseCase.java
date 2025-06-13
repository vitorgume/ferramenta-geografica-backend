package com.gume.mapa_dinamico_motorlub.application.usecase;

import com.gume.mapa_dinamico_motorlub.application.gateways.EmpresaGateway;
import com.gume.mapa_dinamico_motorlub.domain.*;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.EmpresaDto;
import com.gume.mapa_dinamico_motorlub.entrypoint.mapper.EmpresaMapper;
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

    public List<Empresa> cadastrarEmpresas(MultipartFile arquivo) {
        log.info("Cadastrando empresas. Arquivo: {}", arquivo);
        List<Empresa> empresas = new ArrayList<>();

        Representante representantePadrao = representanteUseCase.consultarPorId(13L);

        if (arquivo.getOriginalFilename().endsWith(".csv")) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream()))) {
                String linha;
                boolean primeiraLinha = true;
                while ((linha = reader.readLine()) != null) {
                    if (linha.isBlank()) {
                        continue;                     }

                    String[] colunas = linha.split(",");

                    if (colunas.length < 21) {
                        throw new RuntimeException("Linha inválida: " + linha);
                    }

                    if (primeiraLinha) {
                        primeiraLinha = false;
                        continue;
                    }

                    String cnpj = colunas[0];
                    String razaoSocial = colunas[3];
                    String nomeFantasia = colunas[7];
                    String logradouro = colunas[11];
                    String numero = colunas[12];
                    String complemento = colunas[13];
                    String cep = colunas[14];
                    String bairro = colunas[15];
                    String municipio = colunas[16];
                    String uf = colunas[17];
                    String telefones = colunas[19];
                    String email = colunas[20];

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

                    Empresa empresa = Empresa.builder()
                            .cnpj(cnpj)
                            .razaoSocial(razaoSocial)
                            .nomeFantasia(nomeFantasia)
                            .telefone(telefones)
                            .email(email)
                            .endereco(endereco)
                            .segmento(Segmento.OFICINA_MECANICA)
                            .visitado(false)
                            .representante(representantePadrao)
                            .comentario("")
                            .nivelIcp(NivelIcp.A)
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

    private Empresa consultarPorId(UUID id) {
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
        log.info("Empresas listadas com sucesso. Empresas: {}", empresas);
        return empresas;
    }

    public Empresa adicionarComentario(UUID idEmpresa, String comentario) {
        log.info("Adicionando novo comentário a empresa. Id da empresa: {}, Comentário: {}", idEmpresa, comentario);

        Empresa empresa = consultarPorId(idEmpresa);
        empresa.setComentario(comentario);
        empresa = gateway.salvar(empresa);

        log.info("Comentário adicionado a empresa com sucesso. Empresa salva: {}", empresa);

        return empresa;
    }
}
