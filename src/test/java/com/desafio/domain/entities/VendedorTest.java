package com.desafio.domain.entities;

import com.desafio.domain.enums.TipoContratacao;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VendedorTest {

    @Test
    void deveValidarCPFCorreto() {
        // Arrange
        Vendedor vendedor = Vendedor.builder()
                .id("1")
                .matricula("00000001-CLT")
                .nome("João Silva")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .documento("11144477735") // CPF válido
                .email("joao@email.com")
                .tipoContratacao(TipoContratacao.CLT)
                .filialId("1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Act & Assert
        assertTrue(vendedor.isDocumentoValido());
    }

    @Test
    void deveValidarCNPJCorreto() {
        // Arrange
        Vendedor vendedor = Vendedor.builder()
                .id("1")
                .matricula("00000001-PJ")
                .nome("Empresa LTDA")
                .documento("11222333000181") // CNPJ válido
                .email("empresa@email.com")
                .tipoContratacao(TipoContratacao.PESSOA_JURIDICA)
                .filialId("1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Act & Assert
        assertTrue(vendedor.isDocumentoValido());
    }

    @Test
    void deveRejeitarCPFInvalido() {
        // Arrange
        Vendedor vendedor = Vendedor.builder()
                .id("1")
                .matricula("00000001-CLT")
                .nome("João Silva")
                .documento("11111111111") // CPF inválido (todos iguais)
                .email("joao@email.com")
                .tipoContratacao(TipoContratacao.CLT)
                .filialId("1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Act & Assert
        assertFalse(vendedor.isDocumentoValido());
    }

    @Test
    void deveRejeitarCNPJInvalido() {
        // Arrange
        Vendedor vendedor = Vendedor.builder()
                .id("1")
                .matricula("00000001-PJ")
                .nome("Empresa LTDA")
                .documento("11111111111111") // CNPJ inválido (todos iguais)
                .email("empresa@email.com")
                .tipoContratacao(TipoContratacao.PESSOA_JURIDICA)
                .filialId("1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Act & Assert
        assertFalse(vendedor.isDocumentoValido());
    }

    @Test
    void deveIdentificarPessoaJuridica() {
        // Arrange
        Vendedor vendedor = Vendedor.builder()
                .tipoContratacao(TipoContratacao.PESSOA_JURIDICA)
                .build();

        // Act & Assert
        assertTrue(vendedor.isPessoaJuridica());
    }

    @Test
    void deveIdentificarPessoaFisica() {
        // Arrange
        Vendedor vendedor = Vendedor.builder()
                .tipoContratacao(TipoContratacao.CLT)
                .build();

        // Act & Assert
        assertFalse(vendedor.isPessoaJuridica());
    }
}
