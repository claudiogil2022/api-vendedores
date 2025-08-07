# ARQUITETURA DO SISTEMA

## Visão Geral da Arquitetura (Clean Architecture)

```
┌─────────────────────────────────────────────────────────────────┐
│                        INFRASTRUCTURE                           │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐ │
│  │   Controllers   │  │   Repositories  │  │ External APIs   │ │
│  │   (REST APIs)   │  │   (JPA/H2)      │  │  (Filial Mock)  │ │
│  └─────────────────┘  └─────────────────┘  └─────────────────┘ │
└─────────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────────┐
│                        APPLICATION                              │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐ │
│  │   Use Cases     │  │      DTOs       │  │    Mappers      │ │
│  │ (Business Logic)│  │ (Data Transfer) │  │ (Conversions)   │ │
│  └─────────────────┘  └─────────────────┘  └─────────────────┘ │
└─────────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────────┐
│                          DOMAIN                                 │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐ │
│  │    Entities     │  │   Repositories  │  │    Services     │ │
│  │ (Business Rules)│  │   (Interfaces)  │  │  (Interfaces)   │ │
│  └─────────────────┘  └─────────────────┘  └─────────────────┘ │
└─────────────────────────────────────────────────────────────────┘
```

## Fluxo de Dados

### 1. Criação de Vendedor (Assíncrono)

```
Client Request
     │
     ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│ VendedorController │ → │ InitiarProcesso │ → │ ProcessamentoRepo│
│     (REST)        │   │    UseCase      │   │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘
     │                           │
     ▼                           ▼
┌─────────────────┐         ┌─────────────────┐
│   Immediate     │         │   Async Task    │
│   Response      │         │   (Background)  │
│ (ProcessingId)  │         │                 │
└─────────────────┘         └─────────────────┘
                                   │
                                   ▼
                            ┌─────────────────┐
                            │ ProcessarCriacao│
                            │    UseCase      │
                            └─────────────────┘
                                   │
                                   ▼
                            ┌─────────────────┐
                            │ VendedorRepo    │
                            │ FilialService   │
                            └─────────────────┘
```

### 2. Validações Aplicadas

```
Input Data
    │
    ▼
┌─────────────────┐
│ Bean Validation │ ← @Valid, @NotBlank, @Email, etc.
│ (Annotations)   │
└─────────────────┘
    │
    ▼
┌─────────────────┐
│ Business Rules  │ ← Filial ativa, documento único, etc.
│ (Use Cases)     │
└─────────────────┘
    │
    ▼
┌─────────────────┐
│ Domain Logic    │ ← CPF/CNPJ válido, tipo de contratação
│ (Entities)      │
└─────────────────┘
```

## Princípios SOLID Aplicados

### Single Responsibility Principle (SRP)
- `VendedorController`: Apenas controle HTTP
- `CriarVendedorUseCase`: Apenas lógica de criação
- `VendedorRepository`: Apenas persistência

### Open/Closed Principle (OCP)
- Interfaces permitem extensão sem modificação
- Novos tipos de validação podem ser adicionados

### Liskov Substitution Principle (LSP)
- Implementações podem ser substituídas transparentemente
- Mocks nos testes substituem implementações reais

### Interface Segregation Principle (ISP)
- Interfaces específicas e coesas
- `VendedorRepository` vs `FilialService`

### Dependency Inversion Principle (DIP)
- Camadas superiores dependem de abstrações
- Injeção de dependência via Spring

## Padrões de Design Utilizados

### Repository Pattern
```
Domain Layer: VendedorRepository (interface)
Infrastructure: VendedorRepositoryImpl (implementation)
```

### Use Case Pattern
```
Each business operation is encapsulated in a specific use case class
```

### Builder Pattern
```
Vendedor.builder()
    .nome("João")
    .email("joao@email.com")
    .build();
```

### Strategy Pattern
```
Different document validation strategies based on TipoContratacao
```

### Mapper Pattern
```
Domain ↔ Entity ↔ DTO conversions using MapStruct
```
