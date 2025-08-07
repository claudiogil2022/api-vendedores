# 🚀 API Vendedores - Sistema de Gestão com Processamento Assíncrono

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![Maven](https://img.shields.io/badge/Maven-4.0.0-blue)
![H2](https://img.shields.io/badge/Database-H2-lightblue)
![MapStruct](https://img.shields.io/badge/MapStruct-1.5.5-purple)
![License](https://img.shields.io/badge/License-MIT-green)

Sistema completo para gestão de vendedores com processamento assíncrono, validações robustas e múltiplos padrões de acesso.

## 📋 Funcionalidades

- ✅ **CRUD Completo** - Criar, consultar, atualizar e deletar vendedores
- ✅ **Processamento Assíncrono** - Criação em background com monitoramento
- ✅ **Validações Robustas** - CPF/CNPJ, unicidade, tipos de contratação
- ✅ **Múltiplos Padrões de Acesso** - Por ID, matrícula ou processamento
- ✅ **Geração Automática de Matrícula** - Sequencial com sufixos por tipo
- ✅ **Timestamps Preservados** - Manutenção correta de datas
- ✅ **Clean Architecture** - Separação clara de responsabilidades
- ✅ **Exception Handling Global** - Tratamento centralizado de erros

## 🛠️ Tecnologias

- **Java 17** - LTS com recursos modernos
- **Spring Boot 3.2.0** - Framework principal
- **Spring Data JPA** - Persistência
- **MapStruct 1.5.5** - Mapeamento objeto-objeto
- **H2 Database** - Banco em memória para desenvolvimento
- **Swagger/OpenAPI 3** - Documentação da API
- **JUnit 5 + Mockito** - Testes unitários
- **Maven** - Gerenciamento de dependências

## 🚀 Como Executar

### ⚡ Opção 1: Execução Automática (Windows)
```bash
# Execute na pasta do projeto:
start.bat
```

### 🔧 Opção 2: Execução Manual
```bash
# Clonar o repositório
git clone https://github.com/claudiogil2022/api-vendedores.git
cd api-vendedores

# Executar a aplicação
./mvnw spring-boot:run
```

### 📋 Pré-requisitos
- **Java 17+** (obrigatório para Spring Boot 3)
- **Maven 3.6+** ou usar o wrapper incluído

## 🌐 URLs da Aplicação

Após iniciar, acesse:

- **API Base**: `http://localhost:8080`
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **API Docs**: `http://localhost:8080/api-docs`
- **H2 Console**: `http://localhost:8080/h2-console`
- **Health Check**: `http://localhost:8080/actuator/health`
- **Metrics**: `http://localhost:8080/actuator/metrics`

### 🔑 Configuração H2 Console
- **JDBC URL**: `jdbc:h2:mem:vendedores`
- **Username**: `sa`
- **Password**: (deixar vazio)

## 📖 Endpoints Principais

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/vendedores` | Criar vendedor (assíncrono) |
| GET | `/api/vendedores/{id}` | Buscar por ID |
| GET | `/api/vendedores/matricula/{matricula}` | Buscar por matrícula |
| GET | `/api/vendedores/processamento/{processamentoId}` | Buscar por processamento |
| PUT | `/api/vendedores/{id}` | Atualizar vendedor |
| DELETE | `/api/vendedores/{id}` | Deletar vendedor |
| GET | `/api/vendedores` | Listar todos |
| GET | `/api/processamentos/{id}` | Status do processamento |
| GET | `/api/filiais` | Listar filiais |

## 📬 Collection Postman

O projeto inclui uma collection completa do Postman:
- **Arquivo**: `postman-collection.json`
- **Environment**: `postman-environment.json`

### 🔧 Como importar:
1. Abra o Postman
2. Clique em **Import**
3. Selecione `postman-collection.json`
4. Import o environment `postman-environment.json`
5. Selecione o environment "API Vendedores Local"

### 📋 Collection inclui:
- ✅ Todos os endpoints da API
- ✅ Variáveis de ambiente configuradas
- ✅ Exemplos de requests e responses
- ✅ Testes automatizados
- ✅ Validações de status

## 🏗️ Arquitetura

O projeto segue os princípios da **Clean Architecture**:

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
│  │    Entities     │  │   Repositories  │  │     Services    │ │
│  │ (Core Models)   │  │  (Contracts)    │  │ (Domain Logic)  │ │
│  └─────────────────┘  └─────────────────┘  └─────────────────┘ │
└─────────────────────────────────────────────────────────────────┘
```

## 🧪 Testes

### Execução dos Testes Unitários
```bash
./mvnw test
```

### Script de Teste Completo
Para testar todo o fluxo da aplicação:
```powershell
# Execute o script de teste automatizado
./testar-cadastro.ps1
```

Este script testa:
- ✅ Conexão com a aplicação
- ✅ Criação de vendedor (POST)
- ✅ Acompanhamento do processamento assíncrono
- ✅ Consulta do vendedor criado
- ✅ Listagem de vendedores

## 🔍 Monitoramento

A aplicação inclui endpoints de monitoramento:
- **Health Check**: `/actuator/health`
- **Metrics**: `/actuator/metrics`
- **Info**: `/actuator/info`

## � Exemplo de Uso

### 1. Criar Vendedor
```json
POST /api/vendedores
{
  "nome": "João Silva",
  "dataNascimento": "1990-05-15",
  "documento": "11144477735",
  "email": "joao.silva@empresa.com",
  "tipoContratacao": "CLT",
  "filialId": "1"
}
```

**Response:**
```json
{
  "success": true,
  "data": {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "status": "PENDENTE"
  }
}
```

### 2. Consultar Status do Processamento
```bash
GET /api/processamentos/550e8400-e29b-41d4-a716-446655440000
```

**Response:**
```json
{
  "success": true,
  "data": {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "status": "CONCLUIDO",
    "vendedorId": 1,
    "dataProcessamento": "2024-01-15T10:30:00"
  }
}
```

### 3. Consultar Vendedor Criado
```bash
GET /api/vendedores/1
```

**Response:**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "nome": "João Silva",
    "matricula": "CLT001",
    "documento": "11144477735",
    "email": "joao.silva@empresa.com",
    "tipoContratacao": "CLT",
    "filialId": "1",
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T10:30:00"
  }
}
```

## 📁 Estrutura do Projeto

```
src/
├── main/java/com/desafio/
│   ├── application/     # Casos de uso, DTOs e Mappers
│   ├── domain/         # Entidades e regras de negócio
│   ├── infrastructure/ # Configurações e adaptadores
│   └── ApiVendedoresApplication.java
├── main/resources/
│   └── application.yml
└── test/java/         # Testes unitários
```

## 🎯 Recursos Especiais

- **Preservação de Timestamps** durante operações de UPDATE
- **Validação CPF/CNPJ** com algoritmos de verificação
- **Geração Automática** de matrículas sequenciais por tipo
- **Múltiplos Padrões de Acesso** aos mesmos dados
- **Collection Postman** completa com testes automatizados
- **Exception Handling Global** com respostas padronizadas

## 🔧 Configurações

- **Pool Assíncrono**: 5-10 threads configuráveis
- **Banco H2**: Em memória para desenvolvimento
- **Logs**: Estruturados para facilitar debugging
- **CORS**: Configurado para desenvolvimento local

## 🤝 Contribuindo

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

**🚀 Desenvolvido com Spring Boot 3 e Java 17**
