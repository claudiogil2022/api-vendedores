package com.desafio.application.usecases;

import com.desafio.application.dtos.request.CriarVendedorRequest;
import com.desafio.application.mappers.VendedorMapper;
import com.desafio.domain.entities.ProcessamentoVendedor;
import com.desafio.domain.entities.Vendedor;
import com.desafio.domain.enums.TipoContratacao;
import com.desafio.domain.repositories.ProcessamentoVendedorRepository;
import com.desafio.domain.repositories.VendedorRepository;
import com.desafio.domain.services.FilialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessarCriacaoVendedorUseCase {

    private final VendedorRepository vendedorRepository;
    private final ProcessamentoVendedorRepository processamentoRepository;
    private final VendedorMapper vendedorMapper;
    private final FilialService filialService;

    @Transactional
    public void execute(String processamentoId, CriarVendedorRequest request) {
        ProcessamentoVendedor processamento = processamentoRepository.findById(processamentoId)
                .orElseThrow(() -> new RuntimeException("Processamento não encontrado"));

        try {
            processamento.marcarComoProcessando();
            processamentoRepository.save(processamento);

            // Validações de negócio
            validarRegrasNegocio(request);

            // Criar vendedor
            Vendedor vendedor = criarVendedor(request);
            vendedor = vendedorRepository.save(vendedor);

            // Marcar processamento como concluído
            processamento.marcarComoConcluido(vendedor.getId());
            processamentoRepository.save(processamento);

            log.info("Vendedor criado com sucesso: {}", vendedor.getId());

        } catch (Exception e) {
            log.error("Erro ao processar criação do vendedor", e);
            processamento.marcarComoErro(e.getMessage());
            processamentoRepository.save(processamento);
        }
    }

    private void validarRegrasNegocio(CriarVendedorRequest request) {
        // Validar se filial está ativa
        if (!filialService.isFilialAtiva(request.getFilialId())) {
            throw new IllegalArgumentException("Filial não encontrada ou inativa");
        }

        // Validar unicidade do documento
        if (vendedorRepository.existsByDocumento(request.getDocumento())) {
            throw new IllegalArgumentException("Já existe um vendedor com este documento");
        }

        // Validar unicidade do email
        if (vendedorRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Já existe um vendedor com este email");
        }

        // Validar documento conforme tipo de contratação
        validarDocumento(request.getDocumento(), request.getTipoContratacao());
    }

    private void validarDocumento(String documento, TipoContratacao tipoContratacao) {
        String documentoLimpo = documento.replaceAll("\\D", "");
        
        if (tipoContratacao == TipoContratacao.PESSOA_JURIDICA) {
            if (documentoLimpo.length() != 14) {
                throw new IllegalArgumentException("Pessoa Jurídica deve ter CNPJ (14 dígitos)");
            }
        } else {
            if (documentoLimpo.length() != 11) {
                throw new IllegalArgumentException("Pessoa Física deve ter CPF (11 dígitos)");
            }
        }
    }

    private Vendedor criarVendedor(CriarVendedorRequest request) {
        Vendedor vendedor = vendedorMapper.toEntity(request);
        
        // Gerar ID e matrícula
        vendedor.setId(UUID.randomUUID().toString());
        vendedor.setMatricula(gerarMatricula(request.getTipoContratacao()));
        
        // Validar documento
        if (!vendedor.isDocumentoValido()) {
            throw new IllegalArgumentException("Documento inválido");
        }
        
        // Buscar filial
        vendedor.setFilial(filialService.findById(request.getFilialId())
                .orElseThrow(() -> new IllegalArgumentException("Filial não encontrada")));
        
        // Definir timestamps
        LocalDateTime now = LocalDateTime.now();
        vendedor.setCreatedAt(now);
        vendedor.setUpdatedAt(now);
        
        return vendedor;
    }

    private String gerarMatricula(TipoContratacao tipoContratacao) {
        long sequential = vendedorRepository.getNextSequentialNumber();
        String sufixo = tipoContratacao.getSufixoMatricula();
        return String.format("%08d-%s", sequential, sufixo);
    }
}
